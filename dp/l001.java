import java.util.*;

public class l001 {
    public static void display1D(int[] arr) {
        for (int ele : arr) {
            System.out.print(ele + " ");
        }

        System.out.println();
    }

    public static void display2D(int arr[][]) {
        for (int ele[] : arr) {
            display1D(ele);
        }
    }

    public static int fib_01(int n, int[] dp) {
        if (n <= 1) {
            return dp[n] = n;
        }

        if (dp[n] != -1)
            return dp[n];

        int ans = fib_01(n - 1, dp) + fib_01(n - 2, dp);
        return dp[n] = ans;
    }

    public static int fib_02(int N, int dp[]) {
        for (int i = 0; i <= N; i++) {
            if (i <= 1) {
                dp[i] = i;
                continue;
            }

            int ans = dp[i - 1] + dp[i - 2];
            dp[i] = ans;
        }

        return dp[N];
    }

    public static void fibo() {
        int n = 8;
        int dp[] = new int[n + 1];
        Arrays.fill(dp, -1);

        System.out.println(fib_01(n, dp));
        display1D(dp);

        System.out.println(fib_02(n, dp));
        display1D(dp);
    }

    public static int mazePath_01(int sr, int sc, int er, int ec, int dp[][]) {
        if (sr == er && sc == ec) {
            return dp[sr][sc] = 1;
        }

        if (dp[sr][sc] != 0)
            return dp[sr][sc];

        int count = 0;
        if (sr + 1 <= er)
            count += mazePath_01(sr + 1, sc, er, ec, dp);
        if (sc + 1 <= ec)
            count += mazePath_01(sr, sc + 1, er, ec, dp);
        if (sr + 1 <= er && sc + 1 <= ec)
            count += mazePath_01(sr + 1, sc + 1, er, ec, dp);

        return dp[sr][sc] = count;
    }

    public static int mazePath_02(int SR, int SC, int er, int ec, int dp[][]) {
        for (int sr = er; sr >= 0; sr--) {
            for (int sc = ec; sc >= 0; sc--) {
                if (sr == er && sc == ec) {
                    dp[sr][sc] = 1;
                    continue;
                }

                int count = 0;
                if (sr + 1 <= er)
                    count += dp[sr + 1][sc];
                if (sc + 1 <= ec)
                    count += dp[sr][sc + 1];
                if (sc + 1 <= ec && sr + 1 <= er)
                    count += dp[sr + 1][sc + 1];

                dp[sr][sc] = count;
            }
        }

        return dp[SR][SC];
    }

    public static void mazePath() {
        int n = 5;
        int m = 5;
        int dp[][] = new int[n][m];

        System.out.println(mazePath_01(0, 0, n - 1, m - 1, dp));
        display2D(dp);
        System.out.println();

        System.out.println(mazePath_02(0, 0, n - 1, m - 1, dp));
        display2D(dp);
        System.out.println();

        System.out.println(multiJump_01(0, 0, n - 1, m - 1, dp));
        display2D(dp);
        System.out.println();

        System.out.println(multiJump_02(0, 0, n - 1, m - 1, dp));
        display2D(dp);
    }

    public static int multiJump_01(int sr, int sc, int er, int ec, int[][] dp) {
        if (sr == er && sc == ec) {
            return dp[sr][sc] = 1;
        }

        if (dp[sr][sc] != 0)
            return dp[sr][sc];

        int count = 0;
        for (int jump = 1; sc + jump <= ec; jump++)
            count += multiJump_01(sr, sc + jump, er, ec, dp);
        for (int jump = 1; sr + jump <= er; jump++)
            count += multiJump_01(sr + jump, sc, er, ec, dp);
        for (int jump = 1; sc + jump <= ec && sr + jump <= er; jump++)
            count += multiJump_01(sr + jump, sc + jump, er, ec, dp);

        return dp[sr][sc] = count;
    }

    public static int multiJump_02(int SR, int SC, int er, int ec, int[][] dp) {
        for (int sr = er; sr >= SR; sr--) {
            for (int sc = ec; sc >= SC; sc--) {
                if (sr == er && sc == ec) {
                    dp[sr][sc] = 1;
                    continue;
                }

                int count = 0;
                for (int jump = 1; sr + jump <= er; jump++)
                    count += dp[sr + jump][sc]; // mazePath_01(sr + jump, sc, er, ec, dp);
                for (int jump = 1; sc + jump <= ec; jump++)
                    count += dp[sr][sc + jump]; // mazePath_01(sr, sc + jump, er, ec, dp);
                for (int jump = 1; sr + jump <= er && sc + jump <= ec; jump++)
                    count += dp[sr + jump][sc + jump]; // mazePath_01(sr + jump, sc + jump, er, ec, dp);

                dp[sr][sc] = count;
            }
        }

        return dp[SR][SC];
    }

    public static void main(String args[]) {
        long start = System.nanoTime();
        // fibo();
        mazePath();
        long end = System.nanoTime();
        System.out.println("Execution time: " + (end - start) / 1000000 + " Milliseconds");
    }
}