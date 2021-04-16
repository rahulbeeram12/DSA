import java.util.Arrays;

public class stringSet {

    public static int longestPalindromeicSubsequence(String s, int i, int j,int dp[][]) {
        if (i >= j) {
            return dp[i][j] = (i == j) ? 1 : 0;
        }

        if(dp[i][j] != -1) return dp[i][j];

        int count = 0;
        if (s.charAt(i) == s.charAt(j)) {
            count = longestPalindromeicSubsequence(s, i + 1, j - 1,dp) + 2;
        } else {
            count = Math.max(longestPalindromeicSubsequence(s, i + 1, j,dp), longestPalindromeicSubsequence(s, i, j - 1,dp));
        }

        return dp[i][j] = count;
    }

    public static int lps_tab(String s,int I,int J,int dp[][]){
        for(int gap = 0; gap < dp[0].length; gap++){
            for(int i = 0,j = gap; j < dp[0].length; j++,i++){
                if(i >= j){
                    dp[i][j] = (i == j) ? 1 : 0;
                    continue;
                }

                if(s.charAt(i) == s.charAt(j)) dp[i][j] = dp[i + 1][j - 1] + 2;
                else dp[i][j] = Math.max(dp[i - 1][j],dp[i][j - 1]);
            }
        }

        return dp[I][J];
    }

    public static String lps_print(String s,String [][]dp){
        int n = dp.length;
        for(int gap = 0; gap < n; gap++){
            for(int i = 0,j = gap; j < n; i++,j++){
                if(i >= j){
                    dp[i][j] = (i == j) ? s.charAt(i) + "" : "";
                    continue;
                }

                if(s.charAt(i) == s.charAt(j)) dp[i][j] = s.charAt(i) + dp[i + 1][j - 1] + s.charAt(j);
                else dp[i][j] = (dp[i + 1][j].length() > dp[i][j - 1].length()) ? dp[i + 1][j] : dp[i][j - 1];
            }
        }

        return dp[0][n - 1];
    }

    // public static int backEngineering1(String s,int [][]dp){
    //     int n = s.length();
    //     for(int gap = 0; gap < n; gap++){
    //         for(int i = 0,j = gap;j < n; j++,i++){
    //             if(i >= j) dp[i][j] = (i == j) ? 1 : 0;
                
    //             if(s.charAt(i) == s.charAt(i)) dp[i][j] = dp[i + 1][j - 1] + 2;
    //             else dp[i][j] = Math.max(dp[i][j - 1],dp[i + 1][j]);
    //         }
    //     }
        

    // }

    public static void lps(){
        String s = "fadfadfadf";
        int n = s.length();
        // int dp[][] = new int[n][n];

        // for (int d[] : dp)
        //     Arrays.fill(d, -1);
        
        // System.out.println(longestPalindromeicSubsequence(s, 0, n - 1, dp));

        String dp[][] = new String[n][n];
        for(String d[] : dp) Arrays.fill(d,"");
        System.out.println(lps_print(s,dp));
    } 
    
    public int numDistinct(String s, String t, int n, int m, int dp[][]) {
        if (m == 0)
            return dp[n][m] = 1;
        if (n < m)
            return dp[n][m] = 0;

        if (dp[n][m] != -1)
            return dp[n][m];

        if (s.charAt(n - 1) == t.charAt(m - 1))
            return dp[n][m] = numDistinct(s, t, n - 1, m - 1, dp) + numDistinct(s, t, n - 1, m, dp);
        else
            return dp[n][m] = numDistinct(s, t, n - 1, m, dp);
    }

    public int numDistinct_tab(String s, String t, int N, int M, int dp[][]) {
        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (m == 0) {
                    dp[n][m] = 1;
                    continue;
                }
                if (n < m) {
                    dp[n][m] = 0;
                    continue;
                }

                if (s.charAt(n - 1) == t.charAt(m - 1))
                    dp[n][m] = dp[n - 1][m - 1] + dp[n - 1][m];
                else
                    dp[n][m] = dp[n - 1][m];
            }
        }
        return dp[N][M];
    }

    // count palindromic subsequences
    

    // longest common subsequecnes
    
    public int longestCommonSubsequence(String s, String t, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            return dp[n][m] = 0;
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        if (s.charAt(n - 1) == t.charAt(m - 1))
            dp[n][m] = longestCommonSubsequence(s, t, n - 1, m - 1, dp) + 1;
        else
            dp[n][m] = Math.max(longestCommonSubsequence(s, t, n - 1, m, dp),
                    longestCommonSubsequence(s, t, n, m - 1, dp));

        return dp[n][m];
    }

    public int lcs_tab(String s, String t, int N, int M, int[][] dp) {
        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (n == 0 || m == 0) {
                    dp[n][m] = 0;
                    continue;
                }

                if (s.charAt(n - 1) == t.charAt(m - 1))
                    dp[n][m] = dp[n - 1][m - 1] + 1;
                else
                    dp[n][m] = Math.max(dp[n - 1][m], dp[n][m - 1]);
            }
        }

        return dp[N][M];
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();

        int dp[][] = new int[n + 1][m + 1];
        // for(int d[] : dp) Arrays.fill(d,-1);
        // int ans = longestCommonSubsequence(text1,text2,n,m,dp);
        // for(int d[] : dp) {
        // for(int ele : d) {
        // System.out.print(ele + " ");
        // }
        // System.out.println();
        // }
        return lcs_tab(text1, text2, n, m, dp);
    }

    public int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();

        int dp[][] = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        return numDistinct(s, t, n, m, dp);
    }

    public int maxLines(int[] A, int B[], int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            return 0;
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        if (A[n - 1] == B[m - 1])
            dp[n][m] = maxLines(A, B, n - 1, m - 1, dp) + 1;
        else
            dp[n][m] = Math.max(maxLines(A, B, n - 1, m, dp), maxLines(A, B, n, m - 1, dp));

        return dp[n][m];
    }

    public int maxUncrossedLines(int[] A, int[] B) {
        int n = A.length;
        int m = B.length;

        int dp[][] = new int[n + 1][m + 1];
        for (int d[] : dp)
            Arrays.fill(d, -1);
        return maxLines(A, B, n, m, dp);
    }

    //-------------------------------------------------------

    //leetcode 1458
    public int function(int n1[], int n2[], int n, int m, int dp[][]) {
        if (n == 0 || m == 0) {
            return dp[n][m] = -(int) 1e9;
        }

        if (dp[n][m] != -(int) 1e8)
            return dp[n][m];

        int val = n1[n - 1] * n2[m - 1];
        int accept = function(n1, n2, n - 1, m - 1, dp) + val;
        int a = function(n1, n2, n - 1, m, dp);
        int b = function(n1, n2, n, m - 1, dp);

        return dp[n][m] = Math.max(Math.max(accept, val), Math.max(a, b));
    }

    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        int dp[][] = new int[n + 1][m + 1];
        for (int d[] : dp)
            Arrays.fill(d, -(int) 1e8);
        return function(nums1, nums2, n, m, dp);
    }

    //leetcode 72

    // converting word1 to word2,so changes will be affected in word1 only;

    // Memorisation

    public int minDistance(String s, String t, int n, int m, int dp[][]) {
        if (n == 0 || m == 0) {
            return dp[n][m] = (n != 0) ? n : m;
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        if (s.charAt(n - 1) == t.charAt(m - 1))
            return dp[n][m] = minDistance(s, t, n - 1, m - 1, dp);
        // insert -> {i,j + 1},delete -> {i + 1,j} , replace -> {i + 1,j + 1};
        return dp[n][m] = 1 + Math.min(Math.min(minDistance(s, t, n, m - 1, dp), minDistance(s, t, n - 1, m, dp)),
                minDistance(s, t, n - 1, m - 1, dp));
    }

    // tabulation

    public int minDistance_tab(String s, String t, int N, int M, int dp[][]) {
        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (n == 0 || m == 0) {
                    dp[n][m] = (n != 0) ? n : m;
                    continue;
                }

                if (s.charAt(n - 1) == t.charAt(m - 1))
                    dp[n][m] = dp[n - 1][m - 1];
                else
                    dp[n][m] = 1 + Math.min(Math.min(dp[n - 1][m], dp[n][m - 1]), dp[n - 1][m - 1]);
            }
        }

        return dp[N][M];
    }

    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int dp[][] = new int[n + 1][m + 1];
        // for(int d[] : dp) Arrays.fill(d,-1);
        // int ans = minDistance(word1,word2,n,m,dp);
        return minDistance_tab(word1, word2, n, m, dp);
    }

    //------------------------------------leetcode 44

     public String modifyP(String p){
        if(p.length() == 0) return "";
        StringBuilder sb = new StringBuilder();
        sb.append(p.charAt(0));
        for(int i = 1; i < p.length();){
            char ch = p.charAt(i);
            if(ch != '*'){
                sb.append(ch);
                i++;
            }else if(ch == '*' && p.charAt(i - 1) == '*'){
                i++;
            }else{
                sb.append(ch);
                i++;
            }
        }
        
        return sb.toString();
    }
    
    //-1 -> default, 0 -> false, 1 ->true  
    public int isMatch(String s, String p,int n,int m,int [][]dp) {
        if(n == 0 && m == 0){
            return dp[n][m] = 1;    
        }
        
        if(n == 0 || m == 0){
            return dp[n][m] = (m == 1 && p.charAt(m - 1) == '*') ? 1 : 0;
        }
        
        if(dp[n][m] != -1) return dp[n][m];
        
        if(s.charAt(n - 1) == p.charAt(m - 1) || p.charAt(m - 1) == '?') return dp[n][m] = isMatch(s,p,n - 1,m - 1,dp);
        else if(p.charAt(m - 1) == '*'){
            boolean res = false;
            res = res || (isMatch(s,p,n - 1,m,dp) == 1);
            res = res || (isMatch(s,p,n,m - 1,dp) == 1);
            return dp[n][m] = res == true ? 1 : 0;
        }else{
            return dp[n][m] = 0;
        }
    }
    
    public boolean isMatch(String s, String p) {
        p = modifyP(p);
        int n = s.length();
        int m = p.length();
        
        int dp[][] = new int[n + 1][m + 1];
        for(int d[] : dp) Arrays.fill(d,-1);
        
        return (isMatch(s,p,n,m,dp) == 0) ? false : true;
    }

    public static void countPalindromicSubsequences(){
        
    }

    public static void main(String args[]) {
        long start = System.nanoTime();
        
        lps();
        
        long end = System.nanoTime();
        
        System.out.println("Execution Time : " + (end - start) / 1000000 + "ms");
    }
}