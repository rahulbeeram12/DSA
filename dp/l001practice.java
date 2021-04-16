import java.util.Arrays;
import java.util.LinkedList;

public class l001practice {

    public static void display1D(int dp[]) {
        int n = dp.length;
        for (int i = 0; i < n; i++) {
            System.out.print(dp[i] + " ");
        }
        System.out.println();
    }

    public static void display2D(int dp[][]) {
        for (int d[] : dp) {
            display1D(d);
        }
    }

    public static long fibonacci(int x) {
        if (x <= 1)
            return x;

        long ans = fibonacci(x - 1) + fibonacci(x - 2);

        return ans;
    }

    public static long fibonacci_memo(int x, long dp[]) {
        if (x <= 1)
            return dp[x] = x;

        if (dp[x] != -1)
            return dp[x];
        long ans = fibonacci_memo(x - 1, dp) + fibonacci_memo(x - 2, dp);

        return dp[x] = ans;
    }

    public static int fibonacci_tab(int x, int dp[]) {
        for (int i = 0; i <= x; i++) {
            if (i <= 1) {
                dp[i] = i;
                continue;
            }

            int ans = dp[i - 1] + dp[i - 2];
            dp[i] = ans;
        }

        return dp[x];
    }

    public static int fibonacci_opti(int N) {
        int a = 0;
        int b = 1;

        for (int n = 0; n < N; n++) {
            // System.out.print(a + " ");
            int sum = a + b;
            a = b;
            b = sum;
        }
        return a;
    }

    public static void fibo() {
        int n = 5;
        int dp[] = new int[n + 1];

        System.out.println(fibonacci_tab(n, dp));
        System.out.println(fibonacci_opti(n));
        display1D(dp);
    }

    // --------------------------------------------------------Maze_Path
    // Series--------------------------------------------------------------

    public static int mazePath_rec(int sr, int sc, int er, int ec) {
        if (sr == er && sc == ec) {
            return 1;
        }

        int count = 0;
        if (sr < er)
            count += mazePath_rec(sr + 1, sc, er, ec);
        if (sc < ec)
            count += mazePath_rec(sr, sc + 1, er, ec);
        if (sr < er && sc < ec)
            count += mazePath_rec(sr + 1, sc + 1, er, ec);

        return count;
    }

    public static int mazePath_memo(int sr, int sc, int er, int ec, int dp[][]) {
        if (sr == er && sc == ec) {
            return dp[sr][sc] = 1;
        }

        if (dp[sr][sc] != 0)
            return dp[sr][sc];

        int count = 0;
        if (sr < er)
            count += mazePath_memo(sr + 1, sc, er, ec, dp);
        if (sc < ec)
            count += mazePath_memo(sr, sc + 1, er, ec, dp);
        if (sr < er && sc < ec)
            count += mazePath_memo(sr + 1, sc + 1, er, ec, dp);

        return dp[sr][sc] = count;
    }

    public static int mazePath_tab(int sr, int sc, int er, int ec, int dp[][]) {
        for (int i = er; i >= 0; i--) {
            for (int j = ec; j >= 0; j--) {
                if (i == er || j == ec) {
                    dp[i][j] = 1;
                    continue;
                }

                dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j] + dp[i][j + 1];
            }
        }
        return dp[0][0];
    }

    public static void mazePath() {
        int n = 3;
        int dp[][] = new int[n][n];
        // System.out.println(mazePath_rec(0,0,n - 1,n - 1));
        // System.out.println(mazePath_memo(0, 0, n - 1, n - 1,dp));
        System.out.println(mazePath_tab(0, 0, n - 1, n - 1, dp));
        display2D(dp);
    }

    // -------------------------------------------------------------------------------------------------------------------------------------------

    public static int mazePath_multi(int sr, int sc, int er, int ec) {
        if (sr == er && sc == ec) {
            return 1;
        }

        int count = 0;

        for (int jump = 1; jump + sr <= er; jump++)
            count += mazePath_multi(sr + jump, sc, er, ec);
        for (int jump = 1; jump + sc <= ec; jump++)
            count += mazePath_multi(sr, sc + jump, er, ec);
        for (int jump = 1; jump + sr <= er && jump + sc <= ec; jump++)
            count += mazePath_multi(sr + jump, sc + jump, er, ec);

        return count;
    }

    public static int mazePath_mem(int sr, int sc, int er, int ec, int dp[][]) {
        if (sr == er && sc == ec) {
            return dp[sr][sc] = 1;
        }

        int count = 0;
        for (int jump = 1; jump + sr <= er; jump++)
            count += mazePath_mem(sr + jump, sc, er, ec, dp);
        for (int jump = 1; jump + sc <= ec; jump++)
            count += mazePath_mem(sr, sc + jump, er, ec, dp);
        for (int jump = 1; jump + sr <= er && jump + sc <= ec; jump++)
            count += mazePath_mem(sr + jump, sc + jump, er, ec, dp);

        return dp[sr][sc] = count;
    }

    public static int mazePath_tabu(int sr, int sc, int er, int ec, int dp[][]) {
        for (int i = er; i >= 0; i--) {
            for (int j = ec; j >= 0; j--) {
                if (i == er && j == ec) {
                    dp[i][j] = 1;
                    continue;
                }

                int count = 0;
                for (int jump = 1; jump + i <= er; jump++)
                    count += dp[i + jump][j];
                for (int jump = 1; jump + j <= ec; jump++)
                    count += dp[i][j + jump];
                for (int jump = 1; jump + i <= er && jump + j <= ec; jump++)
                    count += dp[i + jump][j + jump];

                dp[i][j] = count;
            }
        }
        return dp[0][0];
    }

    public static void mazePath_multi() {
        int n = 5;
        int dp[][] = new int[n][n];
        // System.out.println(mazePath_multi(0,0,n - 1,n - 1));
        // System.out.println(mazePath_mem(0, 0, n - 1, n - 1,dp));
        System.out.println(mazePath_tabu(0, 0, n - 1, n - 1, dp));
        display2D(dp);
    }

    // -----------------------------------------------------------------------------------------------------------------------------------

    public static int cube(int sp, int ep, int[] dp) {
        if (sp == ep)
            return dp[sp] = 1;

        if (dp[sp] != 0)
            return dp[sp];

        int count = 0;
        for (int dice = 1; dice <= 6 && sp + dice <= ep; dice++) {
            count += cube(sp + dice, ep, dp);
        }

        return dp[sp] = count;
    }

    public static int cube_tab(int sp, int ep, int dp[]) {
        for (int i = ep; i >= 0; i--) {
            if (i == ep) {
                dp[i] = 1;
                continue;
            }

            int count = 0;
            for (int dice = 1; dice <= 6 && dice + i <= ep; dice++) {
                count += dp[i + dice];
            }
            dp[i] = count;
        }
        return dp[0];
    }

    public static int cube_opti(int sp, int ep) {
        LinkedList<Integer> list = new LinkedList<>();

        for (sp = ep; sp >= 0; sp--) {
            if (sp >= ep - 1)
                list.addFirst(1);
            else {
                if (list.size() <= 6) {
                    list.addFirst(list.getFirst() * 2);
                } else {
                    list.addFirst(list.getFirst() * 2 - list.removeLast());
                }
            }
        }

        return list.getFirst();
    }

    public static void cube() {
        int n = 23;
        int dp[] = new int[n + 1];
        // System.out.println(cube(0,n,dp));
        // System.out.println(cube_tab(0, n, dp));
        // display1D(dp);
        System.out.println(cube_opti(0, n));
    }

    // ------------------------------------------------------------

    public static int climbStairs_(int n) {
        if (n <= 1)
            return 1;
        return climbStairs_(n - 1) + climbStairs_(n - 2);
    }

    // tabulation
    public static int climbStairs(int n, int dp[]) {
        for (int i = 0; i <= n; i++) {
            if (i <= 1) {
                dp[i] = 1;
                continue;
            }

            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    // optimized
    public static int climbStairs_opti(int n) {
        int a = 0;
        int b = 1;

        for (int i = 0; i <= n; i++) {
            System.out.print(a + " ");
            int sum = a + b;
            a = b;
            b = sum;
        }
        return a;
    }

    public static void climbStairs(int n) {
        // climbStairs_(n);
        // int dp[] = new int[n + 1];
        // climbStairs(n,dp);
        // display1D(dp);
        System.out.println(climbStairs_opti(n));
    }

    // -------------------------------------------------------------------------------------------------------------------------

    // Memoization

    public int minCost(int n, int dp[], int[] cost) {
        if (n <= 1)
            return dp[n] = cost[n];
        if (dp[n] != -1)
            return dp[n];
        int minCost = Math.min(minCost(n - 1, dp, cost), minCost(n - 2, dp, cost));
        return dp[n] = minCost + (n == cost.length ? 0 : cost[n]);
    }

    // Tabulation

    public int minCost_dp(int n, int dp[], int[] cost) {
        for (int i = 0; i <= n; i++) {
            if (i <= 1) {
                dp[i] = cost[i];
                continue;
            }

            int minCost = Math.min(dp[i - 1], dp[i - 2]);
            dp[i] = minCost + (i == cost.length ? 0 : cost[i]);
        }
        return dp[n];
    }

    public int minclimbStairs_opti(int n, int cost[]) {
        int a = cost[0];
        int b = cost[1];

        for (int i = 2; i < n; i++) {
            int minCost = Math.min(a, b) + cost[i];
            a = b;
            b = minCost;
        }

        return Math.min(a, b);
    }

    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int dp[] = new int[n + 1];

        return minCost_dp(n, dp, cost);
    }

    // --------------------------------------------------------------------------------------------------------------------------

    long mod = 1000000007;

    public long count(int n, long dp[]) {
        for (int i = 0; i <= n; i++) {
            if (i <= 1) {
                dp[i] = 1;
                continue;
            }

            dp[i] = (dp[i - 1] + (i - 1) * dp[i - 2]) % mod;
        }

        return (long) dp[n];
    }

    public long countFriendsPairings(int n) {
        long dp[] = new long[n + 1];
        return count(n, dp);
    }

    // ---------------------------------print friends pairing-----------------------
    public static int printfP(String s,String ans){
        if(s.length() == 0){
            System.out.println(ans + " ");
            return 1;
        }

        int count = 0;
        char ch = s.charAt(0);
        
        count += printfP(s.substring(1),ans + ch + " ");
        
        for(int i = 1;i < s.length(); i++){
            String rstr = s.substring(1,i) + s.substring(i + 1); 
            count += printfP(rstr,ans + ch + s.charAt(i) + " ");
        }

        return count;
    }

    public static void friendsPairPrint(String s){
        System.out.println(printfP(s,""));
    }

    //------------------------------------GOLD MINE------------------------------------------
  
    public static int getGold(int M[][],int r,int c,int dir[][],int dp[][]){
        if(c == M[0].length - 1){
            return dp[r][c] = M[r][c];
        }

        if(dp[r][c] != 0) return dp[r][c];

        int gold = 0;

        for(int d = 0; d < dir.length; d++){
            int x = r + dir[d][0];
            int y = c + dir[d][1];

            if(x >= 0 && x < M.length){
                gold = Math.max(gold,getGold(M,x,y,dir,dp));
            }
        }

        return dp[r][c] = gold + M[r][c];
    }

    public static int goldMine_tab(int M[][],int dp[][],int dir[][]){
        
        for(int j = M[0].length - 1; j >= 0; j--){
            for(int i = M.length - 1; i >= 0; i--){
                if(j == dp[0].length - 1){
                    dp[i][j] = M[i][j];
                    continue;
                }

                int gold = 0;
                for (int d = 0; d < dir.length; d++) {
                    int x = i + dir[d][0];
                    int y = j + dir[d][1];

                    if (x >= 0 && x < M.length) {
                        gold = Math.max(gold, dp[x][y]);
                    }
                }

                dp[i][j] = gold + M[i][j];
            }
        }

        int max = 0;
        for(int i = 0; i < dp.length; i++){
            max = Math.max(max,dp[i][0]);
        }

        return max;
    }
    
    public static int goldMine(){
        int dir[][] = {{-1,1},{0,1},{1,1}};
        int M[][] = {{1,3,3},{2,1,4},{0,6,4}};;

        int n = M.length;
        int m = M[0].length;
        int dp[][] = new int[n][m];
        
        //int max = 0;
        //memo
        // int dp[][] = new int[n][m];
        // for(int i = 0; i < M.length; i++){
        //     max = Math.max(max,getGold(M,i,0,dir,dp));
        // }

        //tabulation
        return goldMine_tab(M,dp,dir);

        //return max;
    }

    //----------------------------------------------------numDecodings
    public int numDecodings(String s,int idx,int dp[]){
        if(idx == s.length()){
            return dp[idx] = 1;
        }
        
        if(dp[idx] != -1) return dp[idx];
        
        char ch1 = s.charAt(idx);
        if(ch1 == '0') return 0;
        
        int count = 0;
        count += numDecodings(s,idx + 1,dp);
        
        if(idx < s.length() - 1){
            char ch2 = s.charAt(idx + 1);
            int num = (ch1 - '0') * 10 + (ch2 - '0');
            if(num <= 26) count += numDecodings(s,idx + 2,dp); 
        }
        
        return dp[idx] = count;
    }

    //tabulation
    public static int numDecodings_tab(String s,int dp[]){
        for(int i = s.length() ; i >= 0; i--){
            if(i == s.length()){
                dp[i] = 1;
                continue;
            }

            char ch = s.charAt(i);
            if(ch == '0') {
                dp[i] = 0;
                continue;
            }
            int count = 0;
            count += dp[i + 1];

            if(i < s.length() - 1){
                char ch2 = s.charAt(i + 1);
                int num = (ch - '0') * 10 + (ch2 - '0');
                if(num <= 26) count += dp[i + 2]; 
            }

            dp[i] = count;
        }

        return dp[0];
    }
    
    public int numDecodings(String s) {
        int dp[] = new int[s.length() + 1];
        Arrays.fill(dp,-1);
        return numDecodings(s,0,dp);
    }

    //---------------------------------------------------------------------numDecodings 2------------------------------------------------
    

    public static void main(String args[]) {

        long start = System.nanoTime();

        
        
        long end = System.nanoTime();
        System.out.println("Execution Time : " + (end - start) / 1000000 + "ms");
    }
}
