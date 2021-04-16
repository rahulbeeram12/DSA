import java.util.Arrays;

import javax.crypto.spec.IvParameterSpec;

import java.util.ArrayList;


public class LIS {
    //leetcode 300 
    //using recursion
    //tc - {0,8,4,12,2,10,6,14,1,9,5,13,3,11,7,15,14}
    
    public static int LIS_rec(int idx,int arr[],int dp[]){

        if(dp[idx] != 0) return dp[idx];

        int maxlen = 0;
        for(int j = idx - 1; j >= 0; j--){
            if(arr[j] < arr[idx]){
                maxlen = Math.max(maxlen,LIS_rec(j, arr,dp));
            }
        }

        return dp[idx] = maxlen + 1;
    }

    public static int LIS_(int arr[]){
        int len = 0;

        int dp[] = new int[arr.length];
        
        for(int i = 0; i < arr.length; i++){
            len = Math.max(len,LIS_rec(i,arr,dp));
        }

        return len;
    }

    public int LIS_tabu(int idx, int nums[], int dp[]) {
        int maxlen = 0;
        for (int i = idx - 1; i >= 0; i--) {
            if (nums[i] < nums[idx]) {
                maxlen = Math.max(maxlen, dp[i]);
            }
        }

        return dp[idx] = maxlen + 1;
    }

    //minimum number of deletions to make array sorted 

    public static int minDeletions(int arr[],int dp[]){
        int len = 0;
        for(int i = 0; i < arr.length; i++){
            for(int j = i - 1; j >= 0 ; j--){
                int max = 1;
                if(arr[j] < arr[i]){
                    max = Math.max(max,dp[j]);
                }

                dp[i] = max + 1;
                len = Math.max(len,dp[i]);
            }
        }

        return arr.length - len;
    }

    // Maximum sum increasing subseuence
    public static int maxSumSequence(int arr[],int dp[]){
        int max = 0;

        for(int i = 0; i < arr.length; i++){
            for(int j = i - 1; j >= 0; j--){
                if(arr[j] < arr[i])dp[i] = Math.max(dp[j],dp[i]);                
            }

            dp[i] += arr[i];
            max = Math.max(dp[i],max);
        }

        return max;
    }

    // Maximum sum increasing subsequence of maximum length
    public static int maxSumWithIncSubSeq(int arr[],int dp[]){

    }

    // longest bitonic subsequence
    // LIS Set
    public static int[] LIS_LR(int arr[]){
        int n = arr.length;
        int dp[] = new int[n];
        
        for(int i = 0 ; i < arr.length; i++){
            for(int j = i - 1; j >= 0 ; j--){
                if(arr[j] < arr[i]) dp[i] = Math.max(dp[i],dp[j]);
            }

            dp[i] += 1;
        }

        return dp;
    }

    public static int[] LIS_RL(int arr[]) {
        int n = arr.length;
        int dp[] = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[i])
                    dp[i] = Math.max(dp[i], dp[j]);
            }

            dp[i] += 1;
        }

        return dp;
    }

    public static int LBS(int arr[]){
        int ans = 0;

        int LR[] = LIS_LR(arr);
        int RL[] = LIS_RL(arr);

        for (int i = 0; i < LR.length; i++) {
            ans = Math.max(LR[i] + RL[i] - 1, ans);
        }

        return ans;
    }

    // follow up bitonic sequence
    // *     *
    //  *   *
    //   * *
    //    * -----> (LDS_LR,LDS_RL)

    public static int[] followUpLDS_LR(int arr[]){
        int n = arr.length; 
        int dp[] = new int[n];
        for(int i = 0; i < n; i++){
            for(int j = i - 1; j >= 0; j--){
                if(arr[j] > arr[i]) dp[i] = Math.max(dp[i],dp[j]);
            }

            dp[i]++;
        }

        return dp;
    } 
    
    public static int[] followUpLDS_RL(int arr[]) {
        int n = arr.length;
        int dp[] = new int[n];
        for(int i = n - 1; i >= 0; i--){
            for(int j = i + 1; j < n ; j++){
                if(arr[j] > arr[i]) dp[i] = Math.max(dp[i],dp[j]);
            }

            dp[i]++;
        }
        return dp;
    }

    public static int followUp_LDS(int arr[]){
        int n = arr.length;
        int Larr[] = followUpLDS_LR(arr);
        int Rarr[] = followUpLDS_RL(arr);
        
        int max = 0;
        for(int i = 0; i < n; i++){
            max = Math.max(max,Larr[i] + Rarr[i] - 1);
        }

        return max;
    }

    // leetcode 673
    // Number of LIS
    public void findNumberOfLIS(int arr[], int dp[], int count[]) {
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < arr[i]) {
                    if (dp[j] > dp[i]) {
                        dp[i] = dp[j];
                        count[i] = count[j];
                    } else if (dp[j] == dp[i]) {
                        count[i] += count[j];
                    }
                }
            }

            dp[i] += 1;
            if (count[i] == -(int) 1e7)
                count[i] = 1;
        }
    }

    public int findNumberOfLIS(int[] arr) {
        int n = arr.length;
        int dp[] = new int[n];
        int count[] = new int[n];
        Arrays.fill(count, -(int) 1e7);

        findNumberOfLIS(arr, dp, count);

        int max = 0;
        for (int i = 0; i < dp.length; i++) {
            max = Math.max(max, dp[i]);
        }

        int cnt = 0;
        for (int i = 0; i < count.length; i++) {
            if (dp[i] == max) {
                cnt += count[i];
            }
        }

        return cnt;
    }

    //follow up quesiton (print all possible numebr of LIS using back engineering)
    
    // here, first we need a dp of LIS
    public static int[] LIS_LR_BE(int arr[]){
        int n = arr.length;

        int dp[] = new int[n];
        
        for(int i = 0; i < n; i++){
            for(int j = i - 1; j >= 0; j--){
                if(arr[j] < arr[i]){
                    dp[i] = Math.max(dp[i],dp[j]);
                }

                dp[i] += 1;
            }
        }

        return dp;
    }

    public static void dfs(ArrayList<ArrayList<Integer>> map,int arr[],int idx,int len,String str){
        if(len == 0){
            System.out.println(str + " " + arr[idx] + ".");
            return;
        }

        for(Integer i : map.get(len - 1)){
            if(i < idx && arr[i] < arr[idx]){
                dfs(map,arr,i,len - 1,str + arr[idx]);
            }
        }
    }

    public static void LIS_all_paths_backEngineering(int arr[]){
        int dp[] = LIS_LR_BE(arr);

        int max = 0;
        for(int i : dp) max = Math.max(max,i);

        ArrayList<ArrayList<Integer>> map = new ArrayList<>();
        for(int i = 0; i <= max; i++) 
            map.add(new ArrayList<>());

        for(int i = 0; i < n; i++){
            map.get(dp[i]).add(i);
        }

        for(Integer i : map.get(max)){
            dfs(map,arr,i,max,"");
        }
    }

    // Building Bridges DP
    public static int buildingBridges(int arr[][]){
        Arrays.sort(arr,(a,b)->{
            return a[0] - b[0];  // default behaviour
        });

        // in c++ (sorting a 2d array/vector)
        // sort(arr.begin(),arr.end(), [](vector<int> &a,vector<int> &b){
        //     return a[0] < b[0];
        // });

        int n = arr.length;
        int dp[] = new int[n];
        int len = 0;
        for(int i = 0; i < n; i++){
            for(int j = i - 1; j >= 0; j--){
                if(arr[j][0] < arr[i][0] && arr[j][1] < arr[i][1]){
                    dp[i] = Math.max(dp[i],dp[j]);
                }
            }

            dp[i] += 1;
            len = Math.max(len,dp[i]);
        }

        return len;
    }

    //leetcode 354
    public int maxEnvelopes_(int[][] envelopes) {
        int n = envelopes.length;
        int dp[] = new int[n];
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            dp[i] = dp[i] + 1;
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (a, b) -> {
            return a[0] - b[0];
        });

        return maxEnvelopes_(envelopes);
    }


    public static void main(String args[]){
        int arr[] = {0,8,4,12,2,10,6,14,1,9,5,13,3,11,7,15,14};
        
        long start = System.nanoTime();
        System.out.println(LIS_(arr));
        long end = System.nanoTime();

        System.out.println("Execution Time : " + (end - start) / 1000000 + "ms");
    }    
}
