public class lastclass{
    // applications of catalan number

    // 1216
    // length of string - longest palindromic subsequence <= k

    // 940
    int mod = (int) 1e9 + 7;

    public int distinctSubseqII(String s) {
        s = '$' + s;
        int dp[] = new int[s.length()];
        dp[0] = 1;

        int lastOcc[] = new int[26];
        Arrays.fill(lastOcc, -1);

        for (int i = 1; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (lastOcc[ch - 'a'] != -1) {
                dp[i] = (((2 * dp[i - 1]) % mod) - dp[lastOcc[ch - 'a'] - 1] + mod) % mod;
            } else {
                dp[i] = (2 * dp[i - 1]) % mod;
            }
            lastOcc[ch - 'a'] = i;
        }

        return dp[s.length() - 1] - 1;
    }

    // 1278
    public int palindromePartition(String s, int si, int k, int n, int dp[][], int minChange[][]) {
        if (s.length() - si <= k) {
            return dp[si][k] = (s.length() - si == k) ? 0 : (int) 1e9;
        }

        if (k == 1)
            dp[si][k] = minChange[si][n - 1];

        if (dp[si][k] != -1)
            return dp[si][k];

        int min = (int) 1e9;
        for (int i = si; i < s.length() - 1; i++) {
            int myres = minChange[si][i];
            int rans = palindromePartition(s, i + 1, k - 1, n, dp, minChange);
            min = Math.min(min, rans + myres);
        }

        return dp[si][k] = min;
    }

    public int palindromePartition(String s, int k) {
        int n = s.length();
        int minChange[][] = new int[n][n];

        for (int gap = 0; gap < minChange.length; gap++) {
            for (int i = 0, j = gap; j < minChange.length; i++, j++) {
                if (gap == 0)
                    minChange[i][j] = 0;
                else if (gap == 1)
                    minChange[i][j] = (s.charAt(i) == s.charAt(j)) ? 0 : 1;
                else
                    minChange[i][j] = (s.charAt(i) == s.charAt(j)) ? minChange[i + 1][j - 1]
                            : minChange[i + 1][j - 1] + 1;
            }
        }

        int dp[][] = new int[n][k + 1];
        for (int d[] : dp)
            Arrays.fill(d, -1);
        return palindromePartition(s, 0, k, n, dp, minChange);
    }

    // 10 leetcode 
    // do it by yourself

    public static void main(String args[]){

    }
}