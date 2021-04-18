public class cutSet {
    
    // matrix chain multiplication 
    // memoization
    public static int mcm_memo(int arr[],int si,int ei,int dp[][]){
        if(si + 1 == ei){
            return dp[si][ei] = 0;
        }

        if(dp[si][ei] != 0) return dp[si][ei];

        int min = (int)1e9;
        for(int cut = si + 1; cut < ei ; cut++){
            int lans = mcm_memo(arr, si, cut, dp);
            int rans = mcm_memo(arr, cut, ei, dp);
            int res = lans + arr[si] * arr[cut] * arr[ei] + rans;

            min = Math.min(min,res);
        }

        return dp[si][ei] = min;
    }

    public static int mcm_util() {
        int arr[] = { 40, 20, 30, 10, 30 };
        int n = arr.length;
        int dp[][] = new int[n][n];
        return mcm_memo(arr, 0, n - 1, dp);
    }

    // tabulation
    // here cost of 1 multiplication is 1$
    public static int mcm_tabu(int arr[],int SI,int EI){
        int n = arr.length;
        int dp[][] = new int[n][n];

        for(int gap = 1; gap < dp.length; gap++){
            for(int si = 0, ei = gap; ei < dp[0].length; ei++,si++){
                if(si + 1 == ei){
                    dp[si][ei] = 0;
                    continue;
                }

                int min = (int) 1e9;
                for (int cut = si + 1; cut < ei; cut++) {
                    int lans = dp[si][cut];
                    int rans = dp[cut][ei];
                    int res = lans + arr[si] * arr[cut] * arr[ei] + rans;

                    min = Math.min(min, res);
                }

                dp[si][ei] = min;
            }
        }

        return dp[SI][EI];
    }

    // here cost of 1 multiplication is 3$ & addition is 5$
    public static int mcm_tabu_followup(int arr[], int SI, int EI) {
        int n = arr.length;
        int dp[][] = new int[n][n];

        for (int gap = 1; gap < dp.length; gap++) {
            for (int si = 0, ei = gap; ei < dp[0].length; ei++, si++) {
                if (si + 1 == ei) {
                    dp[si][ei] = 0;
                    continue;
                }

                int min = (int) 1e9;
                for (int cut = si + 1; cut < ei; cut++) {
                    int lans = dp[si][cut];
                    int rans = dp[cut][ei];
                    int res = (lans + (arr[si] * arr[ei]) * ((3 * arr[cut]) + (arr[cut] - 1) * 5) + rans);

                    min = Math.min(min, res);
                }

                dp[si][ei] = min;
            }
        }

        return dp[SI][EI];
    }

    // public static int print_MCM(int arr[],int si,int ei){
        
    // }

    // min max evalution    
    public static class pair {
        int minValue = (int)1e9;
        int maxValue = -(int)1e9;

        String minExpression = "";
        String maxExpression = "";

        public pair (){
        }

        public pair (int minValue,int maxValue){
            this.minValue = minValue;
            this.maxValue = maxValue;
        }

        public pair(int minValue, int maxValue, String minExpression,String maxExpression) {
            this.minValue = minValue;
            this.maxValue = maxValue;
            this.minExpression = minExpression;
            this.maxExpression = maxExpression;
        }
    }

    public static int evaluate(int a,int b,char op){
        return op == '+' ? a + b : a * b;
    }

    public static pair minMaxEval(String s,int si,int ei,pair dp[][]){
        if(si == ei){
            int num = s.charAt(si) - '0';
            return dp[si][ei] = new pair(num,num,num + "",num + "");
        }

        if(dp[si][ei] != null) return dp[si][ei];
        
        pair res = new pair();
        for(int cut = si + 1; cut < ei; cut += 2){
            pair lans = minMaxEval(s, si, cut - 1, dp);
            pair rans = minMaxEval(s, cut + 1, ei, dp);

            int min = evaluate(lans.minValue,rans.minValue,s.charAt(cut));
            int max = evaluate(lans.maxValue,rans.maxValue,s.charAt(cut));    

            // res.minValue = Math.min(res.minValue,min);
            // res.maxValue = Math.max(res.maxValue,max);

            if(min < res.minValue){
                res.minValue = min;
                res.minExpression = "(" + lans.minExpression + " " + s.charAt(cut) + " " + rans.minExpression + ")";
            }

            if (max > res.maxValue) {
                res.maxValue = max;
                res.maxExpression = "(" + lans.maxExpression + " " + s.charAt(cut) + " " + rans.maxExpression + ")";
            }
        }

        return dp[si][ei] = res;
    }

    public static void minMaxUtil(String s){
        int n = s.length();
        pair dp[][] = new pair[n][n];
        pair res = minMaxEval(s, 0, n - 1, dp);

        System.out.println("Minimum value : " + res.minValue);
        System.out.println("Minimum Expression : " + res.minExpression);

        System.out.println("Maximum value : " + res.maxValue);
        System.out.println("Maximum Expression : " + res.maxExpression);
    }

    // palindromic partitioning 2
    public static int getAns(String s, int si, boolean[][] isPalindrome, int dp[]) {
        if (isPalindrome[si][s.length() - 1]) {
            return dp[si] = 0;
        }

        if (dp[si] != -1)
            return dp[si];

        int min = (int) 1e9;
        for (int cut = si; cut < s.length(); cut++) {
            if (isPalindrome[si][cut]) {
                min = Math.min(min, getAns(s, cut + 1, isPalindrome, dp) + 1);
            }
        }

        return dp[si] = min;
    }

    public int minCut(String s) {
        int n = s.length();
        boolean isPalindrome[][] = new boolean[n][n];

        for (int gap = 0; gap < s.length(); gap++) {
            for (int i = 0, j = gap; j < s.length(); i++, j++) {
                if (i == j)
                    isPalindrome[i][j] = true;
                else if (gap == 1)
                    isPalindrome[i][j] = (s.charAt(i) == s.charAt(j));
                else
                    isPalindrome[i][j] = (s.charAt(i) == s.charAt(j)) && isPalindrome[i + 1][j - 1];
            }
        }

        int dp[] = new int[n];
        Arrays.fill(dp, -1);

        return getAns(s, 0, isPalindrome, dp);
    }

    //using tabulation
    public static int palindromic_partitioning_2(String s,int [][]isPalindrome){
        int n = s.length();
        int dp[] = new int[n];
        Arrays.fill(dp,-1);
        for(int i = n - 1; i >= 0; i--){
            if(isPalindrome[i][n - 1]){
                dp[i] = 0;
                continue;
            }

            int min = (int)1e9;
            for(int cut = i ; cut < n; cut++){
                if(isPalindrome[i][cut]){
                    min = Math.min(min,dp[cut + 1] + 1);
                }
            }

            dp[i] = min;
        }

        return dp[0];
    }

    // burst ballons leetcode 312
    public int maxCoins(int arr[],int si,int ei,int dp[][]){
        if(dp[si][ei] != -1) return dp[si][ei];
        int lval = si - 1 == -1 ? 1 : nums[si - 1];
        int rval = ei + 1 == nums.length ? 1 : nums[ei + 1];

        int max = 0;
        for(int cut = si; cut <= ei; cut++){
            int lans = cut == si ? 0 : maxCoins(arr, si, cut - 1, dp);
            int rans = cut == ei ? 0 : maxCoins(arr, cut + 1, ei, dp);
            
            max = Math.max(max,lans + lval * arr[cut] * rval + rans);
        }

        return dp[si][ei] = max;
    }

    public int maxCoins(int nums[]){
        int n = nums.length;
        int dp[][] = new int[n][n];
        for(int d[] : dp) Arrays.fill(d,-1);
        return maxCoins(nums,0,n - 1,dp);
    }

    // 1039. Minimum Score Triangulation of Polygon
    public int minScoreTriangulation(int[] arr, int si, int ei, int dp[][]) {
        if (ei - si <= 1) {
            return dp[si][ei] = 0;
        }

        if (ei - si == 2) {
            return dp[si][ei] = arr[si] * arr[si + 1] * arr[ei];
        }

        if (dp[si][ei] != 0)
            return dp[si][ei];

        int min = (int) 1e9;
        for (int cut = si + 1; cut < ei; cut++) {
            int lans = minScoreTriangulation(arr, si, cut, dp);
            int rans = minScoreTriangulation(arr, cut, ei, dp);

            min = Math.min(min, lans + arr[si] * arr[cut] * arr[ei] + rans);
        }

        return dp[si][ei] = min;
    }

    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        int dp[][] = new int[n][n];
        // for(int d[] : dp) Arrays.fill(d,-1);
        int ans = minScoreTriangulation(values, 0, n - 1, dp);
        return ans;
    }

    // boolean parenthization
    public static class pairboolean {
        int trueways = 0;
        int falseways = 0;

        public pairboolean() {
        }

        public pairboolean(int trueways, int falseways) {
            this.trueways = trueways;
            this.falseways = falseways;
        }
    }

    static int mod = 1003;

    public static pairboolean evaluate_(pairboolean left, pairboolean right, char op) {
        int total = ((left.trueways + left.falseways) % mod * (right.trueways + right.falseways) % mod) % mod;

        pairboolean ans = new pairboolean();

        if (op == '|') {
            ans.falseways = (left.falseways * right.falseways) % mod;
            ans.trueways = (total - ans.falseways + mod) % mod;
        } else if (op == '&') {
            ans.trueways = (left.trueways * right.trueways) % mod;
            ans.falseways = (total - ans.trueways + mod) % mod;
        } else {
            ans.trueways = ((left.falseways * right.trueways) % mod + (left.trueways * right.falseways) % mod) % mod;
            ans.falseways = (total - ans.trueways + mod) % mod;
        }

        return ans;
    }

    public static pairboolean getWays(String s, int si, int ei, pairboolean dp[][]) {
        if (si == ei) {
            char ch = s.charAt(si);
            return dp[si][ei] = new pairboolean(ch == 'T' ? 1 : 0, ch == 'F' ? 1 : 0);
        }

        if (dp[si][ei] != null)
            return dp[si][ei];

        pairboolean ans = new pairboolean(0, 0);
        for (int cut = si + 1; cut < ei; cut += 2) {
            pairboolean lways = getWays(s, si, cut - 1, dp);
            pairboolean rways = getWays(s, cut + 1, ei, dp);

            pairboolean res = evaluate_(lways, rways, s.charAt(cut));
            ans.trueways = (ans.trueways + res.trueways) % mod;
            ans.falseways = (ans.falseways + res.falseways) % mod;
        }

        return dp[si][ei] = ans;
    }

    public static int countWays(int N, String S) {
        int n = S.length();
        pairboolean dp[][] = new pairboolean[n][n];
        return getWays(S, 0, n - 1, dp).trueways;
    }

    public static void main(String args[]){
        long start = System.nanoTime();

        minMaxUtil("1+2*3+4*5");

        long end = System.nanoTime();
        System.out.println("Execution Time : " + (end - start) / 1000000 + "ms");
    }
}
