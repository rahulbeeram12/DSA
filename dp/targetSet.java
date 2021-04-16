import java.util.Arrays;

public class targetSet extends utility {
    // Permutation infinite coins
    // using Recursion
    public static int targetInfiP(int[] arr, int target, String ans) {
        if (target == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (target - arr[i] >= 0) {
                count += targetInfiP(arr, target - arr[i], ans + arr[i] + " ");
            }
        }

        return count;
    }

    public static void permuTargetinfi() {
        int arr[] = { 2, 3, 5, 7 };
        int target = 10;

        System.out.println(targetInfiP(arr, target, ""));
    }

    // permutation single coin
    public static int targetSingleP(int arr[], boolean vis[], int target, String ans) {
        if (target == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            if (target - arr[i] >= 0) {
                if (!vis[i]) {
                    vis[i] = true;
                    count += targetSingleP(arr, vis, target - arr[i], ans + arr[i] + " ");
                    vis[i] = false;
                }
            }
        }

        return count;
    }

    public static void permuTargetSingle() {
        int arr[] = { 2, 3, 5, 7 };
        int target = 10;
        boolean vis[] = new boolean[arr.length];

        System.out.println(targetSingleP(arr, vis, target, ""));
    }

    // Combination Infinite coins
    public static int targetInfiC(int arr[], int idx, int target, String ans) {
        if (target == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = idx; i < arr.length; i++) {
            if (target - arr[i] >= 0) {
                count += targetInfiC(arr, i, target - arr[i], ans + arr[i] + " ");
            }
        }

        return count;
    }

    public static void combiTargetinfi() {
        int arr[] = { 2, 3, 5, 7 };
        int target = 10;

        System.out.println(targetInfiC(arr, 0, target, ""));
    }

    // combination single coin
    public static int targetSingleC(int arr[], int idx, int target, String ans) {
        if (target == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;

        for (int i = idx; i < arr.length; i++) {
            if (target - arr[i] >= 0) {
                count += targetSingleC(arr, i + 1, target - arr[i], ans + arr[i] + " ");
            }
        }

        return count;
    }

    public static void combiTargetSingle() {
        int arr[] = { 2, 3, 5, 7 };
        int target = 10;

        System.out.println(targetSingleC(arr, 0, target, ""));
    }

    // using Dynamic programming

    // leetcode 512
    public int change(int target, int[] coins) {
        int dp[] = new int[target + 1];
        dp[0] = 1;

        for (int coin : coins) {
            for (int tar = 0; tar <= target; tar++) {
                if (tar - coin >= 0) {
                    dp[tar] += dp[tar - coin];
                }
            }
        }

        return dp[target];
    }

    // permu infinite coins
    // leetcode 377

    public int permu(int arr[], int target, int dp[]) {
        if (target == 0) {
            return 1;
        }

        if (dp[target] != -1)
            return dp[target];

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (target - arr[i] >= 0) {
                count += permu(arr, target - arr[i], dp);
            }
        }

        return dp[target] = count;
    }

    // tabulaion
    public int permu_tab(int arr[], int target, int dp[]) {
        for (int tar = 0; tar <= target; tar++) {
            if (tar == 0) {
                dp[tar] = 1;
                continue;
            }

            int count = 0;
            for (int i = 0; i < arr.length; i++) {
                if (tar - arr[i] >= 0) {
                    count += dp[tar - arr[i]];
                }
            }

            dp[tar] = count;
        }

        return dp[target];
    }

    public int combinationSum4(int[] nums, int target) {
        int dp[] = new int[target + 1];
        // Arrays.fill(dp,-1);

        // int ans = permu(nums,target,dp);
        // for(int ele : dp) System.out.print(ele + " ");
        return permu_tab(nums, target, dp);
    }

    // 322 leetcode
    public int coinChange(int[] coins, int amount, int dp[]) {
        if (amount == 0) {
            return 0;
        }

        if (dp[amount] != (int) 1e9)
            return dp[amount];

        int minCoins = (int) 1e8;
        for (int i = 0; i < coins.length; i++) {
            if (amount - coins[i] >= 0) {
                minCoins = Math.min(minCoins, coinChange(coins, amount - coins[i], dp) + 1);
            }
        }

        return dp[amount] = minCoins;
    }

    public int coinChange(int[] coins, int amount) {
        int dp[] = new int[amount + 1];
        Arrays.fill(dp, (int) 1e9);
        int ans = coinChange(coins, amount, dp);
        return ans == (int) 1e8 ? -1 : ans;
    }

    // tabulation
    public int coinChange_tab(int[] coins, int amount, int dp[]) {
        for (int tar = 0; tar <= amount; tar++) {
            if (tar == 0) {
                dp[tar] = 0;
                continue;
            }

            int min = (int) 1e9;
            for (int i = 0; i < coins.length; i++) {
                if (tar - coins[i] >= 0) {
                    min = Math.min(min, dp[tar - coins[i]]);
                }
            }

            dp[tar] = min + 1;
        }

        return dp[amount];
    }

    public int coinChange_tabmenu(int[] coins, int amount) {
        int dp[] = new int[amount + 1];
        // Arrays.fill(dp,(int)1e9);
        // int ans = coinChange(coins,amount,dp);
        int ans = coinChange_tab(coins, amount, dp);
        return ans == (int) 1e9 + 1 ? -1 : ans;
    }

    // find number of solutions of a linear equation of n variables

    // ---> this is similar to combination infinite coins
    public static int numberOfSolutions(int coeff[], int idx, int target) {
        int dp[] = new int[target + 1];

        for (int ele : coeff) {
            for (int tar = 0; tar <= target; tar++) {
                if (tar - ele >= 0) {
                    dp[tar] += dp[tar - ele];
                }
            }
        }
        return dp[target];
    }

    // subset sum

    // subsequence method Time complexity - O(2^n)
    public static int subsetSum(int arr[], int n, int target, int dp[][]) {
        if (n == 0 || target == 0) {
            return dp[n][target] = (target == 0) ? 1 : 0;
        }

        if (dp[n][target] != -1)
            return dp[n][target];

        boolean res = false;
        if (target - arr[n - 1] >= 0)
            res = res || (subsetSum(arr, n - 1, target - arr[n - 1], dp) == 1);
        res = res || (subsetSum(arr, n - 1, target, dp) == 1);

        return dp[n][target] = (res == true) ? 1 : 0;
    }

    public static boolean subsetSum(int arr[], int target) {
        int n = arr.length;
        int dp[][] = new int[n + 1][target + 1];
        for (int d[] : dp)
            Arrays.fill(d, -1);
        boolean res = subsetSum(arr, n, target, dp) == 1 ? true : false;
        utility.print2D(dp);
        return res;
    }

    // tabulation subsetSum subsequence
    public static boolean subsetSum_tab(int arr[], int target) {
        int N = arr.length;
        boolean dp[][] = new boolean[N + 1][target + 1];

        for (int n = 0; n < dp.length; n++) {
            for (int tar = 0; tar < dp[0].length; tar++) {
                if (n == 0 || tar == 0) {
                    dp[n][tar] = (tar == 0) ? true : false;
                    continue;
                }

                boolean res = false;
                if (target - arr[n - 1] >= 0)
                    res = dp[n - 1][tar - arr[n - 1]];
                res = res || dp[tar - 1][tar];

                dp[n][tar] = res;
            }
        }

        return dp[N][target];
    }

    // how many subsetSums are possible (basically count)
    public static int subsetSum_count(int arr[], int target) {
        int N = arr.length;
        int dp[][] = new int[N + 1][target + 1];

        for (int n = 0; n < dp.length; n++) {
            for (int tar = 0; tar < dp[0].length; tar++) {
                if (n == 0 || tar == 0) {
                    dp[n][tar] = (tar == 0) ? 1 : 0;
                    continue;
                }

                if (tar - arr[n - 1] >= 0)
                    dp[n][tar] += dp[n - 1][tar - arr[n - 1]];
                dp[n][tar] += dp[n - 1][tar];
            }
        }

        return dp[N][target];
    }

    // subsetSum printing all possible combinations using back engineering

    // public static int subsetSum_backEngineering(int arr[],int target){
    // return 0;
    // }

    // leetcode 494

    // leetcode 416
    public boolean canPartition(int[] nums) {
        double sum = 0.0;
        for (int ele : nums)
            sum += ele;
        double c = Math.ceil(sum / 2.0);
        double f = Math.floor(sum / 2.0);

        if (c != f)
            return false;
        int target = (int) f;
        int n = nums.length;
        boolean dp[][] = new boolean[n + 1][target + 1];

        return canPartition(nums, target, dp);
    }

    public boolean canPartition(int nums[], int target, boolean dp[][]) {
        int N = nums.length;
        for (int n = 0; n < dp.length; n++) {
            for (int tar = 0; tar < dp[0].length; tar++) {
                if (n == 0 || tar == 0) {
                    dp[n][tar] = (tar == 0) ? true : false;
                    continue;
                }

                boolean res = false;
                if (tar - nums[n - 1] >= 0)
                    res = dp[n - 1][tar - nums[n - 1]];
                res = res || dp[n - 1][tar];

                dp[n][tar] = res;
            }
        }

        return dp[N][target];
    }

    // leetcode target_set 494
    public int findTargetSumWays(int[] arr, int n, int target, int sum) {
        if (n == 0) {
            return (target == sum) ? 1 : 0;
        }

        int count = 0;
        count += findTargetSumWays(arr, n - 1, target - arr[n - 1], sum); // + ve number
        count += findTargetSumWays(arr, n - 1, target - (-arr[n - 1]), sum); // + ve number
        return count;
    }

    public int findTargetSumWays(int[] nums, int S) {
        int n = nums.length;
        int sum = 0;
        for (int ele : nums)
            sum += ele;
        if (sum < S || S < -sum)
            return 0;
        return findTargetSumWays(nums, n, 0, sum);
    }

    // now using 2D DP

    public static int targetSum_2D(int arr[], int target, int sum, int n, int dp[][]) {
        if (n == 0) {
            return dp[n][sum] = (target == sum) ? 1 : 0;
        }

        if (dp[n][sum] != -1)
            return dp[n][sum];

        int count = 0;
        count += targetSum_2D(arr, target, sum + arr[n - 1], n - 1, dp);
        count += targetSum_2D(arr, target, sum - arr[n - 1], n - 1, dp);

        return dp[n][sum] = count;
    }

    public static int helperTargetSum_2D(int arr[], int target) {
        int n = arr.length;
        int sum = 0;
        for (int ele : arr)
            sum += ele;

        if (sum < target || target < -sum)
            return 0;

        int dp[][] = new int[n + 1][2 * sum + 1];
        for (int d[] : dp)
            Arrays.fill(d, -1);
        int ans = targetSum_2D(arr, target + sum, sum, n, dp);
        return ans;
    }

    public int findTargetSumWays_(int[] nums, int S) {
        return helperTargetSum_2D(nums, S);
    }

    // 0-1 knapsack problem
    // running recursion on weight array

    public static int knapsack(int value[], int weight[], int idx, int capacity, int dp[][]) {
        if (idx == 0 || capacity == 0) {
            return dp[idx][capacity] = 0;
        }

        if (dp[idx][capacity] != -1)
            return dp[idx][capacity];
        int maxCost = 0;
        if (capacity - weight[idx - 1] >= 0)
            maxCost = knapsack(value, weight, idx - 1, capacity - weight[idx - 1], dp) + value[idx - 1];

        maxCost = Math.max(maxCost, knapsack(value, weight, idx - 1, capacity, dp));

        return dp[idx][capacity] = maxCost;
    }

    // tabulation
    public static int knapsack_tab(int weight[], int value[], int maxCapacity) {
        int N = weight.length;
        int dp[][] = new int[N + 1][maxCapacity + 1];

        for (int n = 0; n < dp.length; n++) {
            for (int cap = 0; cap < dp[0].length; cap++) {
                if (n == 0 || cap == 0) {
                    dp[n][cap] = 0;
                    continue;
                }

                int maxCost = 0;
                if (maxCapacity - weight[n - 1] >= 0) {
                    maxCost = dp[n - 1][cap - weight[n - 1]] + value[n - 1];
                }
                maxCost = Math.max(maxCost, dp[n - 1][cap]);

                dp[n][cap] = maxCost;
            }
        }

        return dp[N][maxCapacity];
    }

    public static int knapSack(int weight[], int value[], int maxCapacity) {
        int n = weight.length;
        int dp[][] = new int[n + 1][maxCapacity + 1];
        for (int d[] : dp)
            Arrays.fill(d, -1);
        return knapsack(value, weight, n, maxCapacity, dp);
    }

    // unbounded knapsack
    public static int unbounded_knapsack(int weight[], int value[], int W, int idx) {
        // combination method

        int dp[] = new int[W + 1];
        for (int i = 0; i < weight.length; i++) {
            for (int wt = weight[i]; wt <= W; wt++) {
                dp[wt] = Math.max(dp[wt], dp[wt - weight[i]] + value[i]);
            }
        }

        return dp[W];
    }

    // partition into k equal sum subsets
    public boolean canPartition(int arr[], int target, int sumSF, int idx, int k, boolean vis[]) {
        if (k == 0)
            return true;

        if (sumSF > target)
            return false;

        if (sumSF == target) {
            return canPartition(arr, target, 0, 0, k - 1, vis);
        }

        boolean res = false;
        for (int i = idx; i < arr.length; i++) {
            if (!vis[i]) {
                vis[i] = true;
                res = res || canPartition(arr, target, sumSF + arr[i], i + 1, k, vis);
                vis[i] = false;
            }
        }

        return res;
    }

    public boolean canPartitionKSubsets(int[] arr, int k) {
        int n = arr.length;
        int sum = 0;
        int maxEle = -1;
        for (int ele : arr) {
            sum += ele;
            maxEle = Math.max(ele, maxEle);
        }

        if (sum % k != 0 || maxEle > sum / k)
            return false;
        int target = sum / k;
        boolean vis[] = new boolean[n];

        return canPartition(arr, target, 0, 0, k, vis);
    }

    public static void main(String args[]) {

        long start = System.nanoTime();

        System.out.println(subsetSum(new int[] { 2, 3, 5, 7 }, 10));

        long end = System.nanoTime();
        System.out.println("Execution Time : " + (end - start) / 1000000 + "ms");
    }
}