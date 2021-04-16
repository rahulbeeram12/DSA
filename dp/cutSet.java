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

    public static int printMCM(){
        return 0;
    }

    public static void main(String args[]){
        long start = System.nanoTime();

        System.out.println(mcm_tabu_followup(new int[] {40,20,30,10,30,40,50,60,70},0,8));

        long end = System.nanoTime();
        System.out.println("Execution Time : " + (end - start) / 1000000 + "ms");
    }
}
