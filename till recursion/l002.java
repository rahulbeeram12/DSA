import java.util.Collections;

public class l002 {
    
    public int binarySearchFirstIndex(int arr[], int data) {
        int si = 0;
        int ei = arr.length - 1;

        while (si <= ei) {
            int mid = (ei + si) / 2;
            if(arr[mid] == data){
                if(mid - 1 >= 0 && arr[mid - 1] == data) ei = mid - 1;
                else return mid;
            }else if(arr[mid] < data) si = mid + 1;
            else ei = mid - 1;
        }

        return -1;
    }
    
    public int binarySearchLastIndex(int arr[], int data) {
        int si = 0;
        int ei = arr.length - 1;

        while (si <= ei) {
            int mid = (ei + si) / 2;
            if(arr[mid] == data){
                if(mid + 1 < arr.length && arr[mid + 1] == data) si = mid + 1;
                else return mid;
            }else if(arr[mid] < data) si = mid + 1;
            else ei = mid - 1;
        }

        return -1;
    }
    
    public int[] searchRange(int[] nums, int target) {
        int ans[] = new int[2];
        
        ans[0] = binarySearchFirstIndex(nums,target);
        ans[1] = binarySearchLastIndex(nums,target);
            
        return ans;
    }

    public static int binarySearchNearestIndex(int arr[],int data){
        if(arr.length == 0) return -1;
        if(data <= arr[0] || data >= arr[arr.length - 1]) return data <= arr[0] ? 0 : arr.length - 1;

        int si = 0;
        int ei = 0;
        while(si <= ei){
            int mid = (ei + si) / 2;
            if(arr[mid] == data) return mid;
            else if(arr[mid] < data) si = mid + 1;
            else ei = mid - 1;
        }

        return data - arr[ei] > arr[si] - data ? si : ei;
    }

    public int searchInsert(int[] arr, int data) {
        int si = 0;
        int ei = arr.length - 1;

        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (arr[mid] == data)
                return mid;
            else if (arr[mid] < data)
                si = mid + 1;
            else
                ei = mid - 1;
        }

        return si;
    }
                            //SEARCHING IN A 2-D ARRAY
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0)
            return false;

        int n = matrix.length;
        int m = matrix[0].length;

        int i = 0;
        int j = n * m - 1;

        while (i <= j) {
            int mid = i + (j - i) / 2;
            int val = matrix[mid / m][mid % m];
            if (val > target)
                j = mid - 1;
            else if (val < target)
                i = mid + 1;
            else if (target == val)
                return true;
        }

        return false;
    }
    //Count inversions

    static long totalInversionCount(long arr[],long sortedArray[],int si,int mid,int ei){
        long count = 0;
        int i = si;
        int j = mid;
        int k = si;

        while(i < j && j <= ei){
            if(arr[i] <= arr[j]) sortedArray[k++] = arr[i++];
            else{
                count += mid - i;
                sortedArray[k++] = arr[j++];
            } 
        }

        while(i < mid) sortedArray[k++] = arr[i++];
        while(j <= si) sortedArray[k++] = arr[j++];

        //for(int s = 0; s < sortedArray.length; s++) arr[s] = sortedArray[s];
        while(si <= ei) arr[si] = sortedArray[si++];

        return count;
    }

    static long inversionCount(long arr[],long sortedArray[],long si,Long ei){
        if(si >= ei) return 0;

        long count = 0;
        long mid = si + (ei - si) / 2;
        count += inversionCount(arr,sortedArray,si,mid);
        count += inversionCount(arr,sortedArray,mid + 1,ei);
        
        count += totalInversionCount(arr,sortedArray,si,mid + 1,ei);
        return count;
    }

    static long inversionCount(long arr[], long N) {
        long sortedArray[] = new long[arr.length];
        return inversionCount(arr,sortedArray,0,N);
    }

    // K CLOSEST ELEMENTS   
    {    
        public int elementPosition(int arr[], int x) {
            int si = 0;
            int ei = arr.length;

            while (si < ei) {
                int mid = si + (ei - si) / 2;
                if (arr[mid] == x)
                    return mid;
                else if (arr[mid] < x)
                    si = mid + 1;
                else
                    ei = mid;
            }

            return ei;
        }

        public List<Integer> findClosestElements(int[] arr, int k, int x) {
            ArrayList<Integer> list = new ArrayList<>();

            for (int val : arr)
                list.add(val);
            int n = arr.length;

            if (x <= arr[0])
                return list.subList(0, k);
            else if (x >= arr[n - 1])
                return list.subList(n - k, n);
            else {
                int idx = elementPosition(arr, x);
                // int idx = Collections.binarySearch(arr,x);
                // if(idx < 0) idx = - idx - 1;

                int si = Math.max(0, idx - k);
                int ei = Math.min(n - 1, idx + k);

                while (ei - si + 1 > k) {
                    if ((x - arr[si]) > (arr[ei] - x))
                        si++;
                    else
                        ei--;
                }
                return list.subList(si, ei + 1);
            }
        }
    }
    
//--------------------------------------------------------LONGEST INCREASING SUBSEQUENCE--------------------------------------------------------------------
    public int lengthOfLIS(int[] arr) {     //---------------------N * N APPROACH---------
        int dp[] = new int[arr.length];

        dp[0] = 1;
        for (int i = 1; i < dp.length; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    max = Math.max(max, dp[j]);
                }
            }

            if (max == Integer.MIN_VALUE)
                dp[i] = 1;
            else
                dp[i] = max + 1;
        }

        int ans = dp[0];
        for (int val : dp)
            ans = Math.max(ans, val);

        return ans;
    }
    public int lengthOfLIS(int[] arr) { 
        if(arr.length <= 1) return arr.length;

        int n = arr.length;
        ArrayList<Integer> list = new ArrayList<>();
        for(int ele : arr){
            int idx = Collections.binarySearch(list,ele);
            if(idx < 0) idx = - idx - 1;
            
            if(idx == list.size()) list.add(ele);
            list.set(idx,ele);
        }

        return list.size();
    }

     //---------------------------------------------------------------KOKO EATING BANANAS-------------------------------------------------------------
    public boolean isPossibleToEat(int piles[],int eatingSpeed,int H){
        int count = 0;
        for(int i = piles.length - 1; i >= 0; i--){
            int val = piles[i];
            if(val <= eatingSpeed){
                count = count + 1;
            }else{
                count += (int)Math.ceil(val * 1.0 / eatingSpeed * 1.0);
            }
            
            if(count > H){
                return false;
            }
        }
        
        return true;
    }
    
    public int minEatingSpeed(int[] piles, int H) {
        Arrays.sort(piles);
        //int max = Integer.MIN_VALUE;
        //for(int val : piles) max = Math.max(val,max);
        int n = piles.length;
        int minSpeed = 1;
        int maxSpeed = piles[n - 1];
        
        while(minSpeed < maxSpeed){
            int eatingSpeed = minSpeed + (maxSpeed - minSpeed) / 2;
            
            if(isPossibleToEat(piles,eatingSpeed,H)) maxSpeed = eatingSpeed;
            else minSpeed = eatingSpeed + 1;
        }
        
        return minSpeed;
    }

    public double maximumAreaServingCake(int radii[],int k){
        int n = radii.length;
        double area[] = new double[n];

        for(int i = 0; i < radii.length; i++){
            area[i] = Math.PI * radii[i] * radii[i];
        }
        double lo = 0.0; 
        double hi = 0.0;
        double mid = 0.0;
        
        while(hi - lo >= 1e-5){
            mid = lo + (hi - lo) / 2.0;
            if(isPossibleToServe(area,mid,k)) lo = mid;
            else hi = Math.max(hi,area[i]);
        }

        return mid;
    }

    // public double maximizeAreaOfCake(int arr[],int noOfPeople){
    //     double radii[] = new double[arr.length];
    //     double si = 0.0,ei = 0.0,mid = 0.0;
    //     for(int i = 0; i < arr.length; i++){
    //         radii[i] = Math.PI * arr[i] * arr[i];
    //         ei = Math.max(ei,radii[i]);
    //     } 

    //     //Arrays.sort(radii);
    //     int n = radii.length;

    //     while(ei - si > 1e5){
    //         double mid = si + (ei - si) / 2.0;
    //         if(isValidCakeArea(radii,mid,noOfPeople)) si = mid;
    //         else hi = mid;
    //     }

    // }

    // public boolean isValidCakeArea(double radii[],double mid,int noOfPeople){
    //     int people = 0;

    //     for(int i = 0; i < radii.length; i--){
    //         double val = radii[i];
    //         people += val / mid;
    //     }

    //     if(people >= noOfPeople){
    //         return true;
    //     }

    //     return false;
    // }


    //--------------------------------------------Leetcode Minimize Maximum distance to a gas station-------------------------------------------------
    public double minmaxGasDist(int[] stations, int k) {
        //double cd[] = new double[stations.length];
        double si = 0.0,ei = 1e9,mid = 0.0;
        //cd[0] = stations[0];
        // for(int i = 1 ; i < stations.length; i++){
        //     cd[i] = stations[i] - stations[i - 1];
        //     ei = Math.max(ei,cd[i]);
        // }
        
        while(ei - si >= 1e-5){
            mid = si + (ei - si) / 2.0;
            if(isValid(arr,mid,k)) si = mid;
            else ei = mid;
        }
        
        return mid;
    }
    
    public boolean isValid(int[] arr,double mid,int k){
        int count = 0;
        for(int i = 1 ; i < arr.length; i++){
            count += (int)((arr[i] - arr[i - 1]) / mid);
            
            if(count > k){
                return true;
            }
        }
        
        return false;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        if(n > m) return findMedianSortedArrays(nums2,nums1);

        int gMid = (n + m + 1) / 2;
        int si = 0,ei = n;

        while(si <= ei){
            int sMid = (si + ei) / 2;
            int lMid = gMid - sMid;

            int sLeftRegionEle = sMid - 1 >= 0 ? nums1[sMid - 1] : -(int)1e8;
            int sRightRegionEle = sMid < n ? nums1[sMid] : (int)1e8;

            int lLeftRegionEle = lMid - 1 >= 0 ? nums2[lMid - 1] : -(int) 1e8;
            int lRightRegionEle = lMid < m ? nums2[lMid] : (int) 1e8;
            
            if(sLeftRegionEle > lRightRegionEle) ei = mid - 1;
            else if(lLeftRegionEle > sRightRegionEle) si = mid + 1;
            else{
                int lMax = Math.max(sLeftRegionEle,lLeftRegionEle);
                int rMin = Math.min(lRightRegionEle, sRightRegionEle);

                if((n + m) % 2 != 0) return lMax * 1.0;
                else return (lMax + rMin) / 2.0;
            }
        }
    }

    //Key pad combinations
    static String[] codes = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz" };
    
    public static int getKPC(String s,int idx,String ans}{
        if(idx == s.length()){
            System.out.println(ans);
            return 1;
        }

        char ch = s.charAt(idx);
        String code = codes[ch - '0'];
        
        int count = 0;
        for(int i = 0; i < code.length(); i++){
            char chcode = code.charAt(i);
            count += getKPC(s,idx + 1,ans + chcode);
        }

        if(idx < s.length() - 1){
            int num = ((s.charAt(idx) - '0') * 10 + (s.charAt(idx + 1) - '0'));
            if(num == 10 || num == 11){
                String extcode = codes[num];
                for(int i = 0; i < extcode.length(); i++){
                    count += getKPC(s,idx + 2,ans + extcode.charAt(i));
                }
            }
        }
        return count;
    }

    public static int printPaths(int sr, int sc, int dr, int dc, String psf) {
        if (sr == dr && sc == dc) {
            System.out.println(psf);
            return 1;
        }

        int count = 0;
        if (sr + 1 <= dr)
            count += printMazePaths(sr + 1, sc, dr, dc, psf + "v");
        if (sc + 1 <= dc)
            count += printMazePaths(sr, sc + 1, dr, dc, psf + "h");
        if (sr + 1 <= dr && sc + 1 <= dc)
            count += printMazePaths(sr + 1, sc + 1, dr, dc, psf + "d");

        return count;
    }

    public static int eightDirectionsCall(int sr, int sc, int dr, int dc, String psf, boolean visited[][]) {
        if (sr == dr && sc == dc) {
            System.out.println(psf);
            return 1;
        }

        visited[sr][sc] = true;
        int count = 0;
        // up call
        if (sr - 1 >= 0 && visited[sr][sc] != true) {
            eightDirectionsCall(sr - 1, sc, dr, dc, psf + "up", visited);
        }//down
        if (sr + 1 >= dr && visited[sr][sc] != true) {
            eightDirectionsCall(sr + 1, sc, dr, dc, psf + "down", visited);
        }//left
        if (sc - 1 >= 0 && visited[sr][sc] != true) {
            eightDirectionsCall(sr, sc - 1, dr, dc, psf + "left", visited);
        }//right
        if (sc + 1 <= dc && visited[sr][sc] != true) {
            eightDirectionsCall(sr, sc + 1, dr, dc, psf + "right", visited);
        }//south
        if (sr + 1 <= dr && sc + 1 <= dc && visited[sr][sc] != true) {
            eightDirectionsCall(sr + 1, sc + 1, dr, dc, psf + "south", visited);
        }//north
        if (sr - 1 >= 0 && sc - 1 >= 0 && visited[sr][sc] != true) {
            eightDirectionsCall(sr - 1, sc - 1, dr, dc, psf + "north", visited);
        }//west
        if (sr + 1 <= dr && sc - 1 >= 0 && visited[sr][sc] != true) {
            eightDirectionsCall(sr + 1, sc - 1, dr, dc, psf + "west", visited);
        }//east
        if (sr - 1 >= 0 && sc + 1 <= dc && visited[sr][sc] != true) {
            eightDirectionsCall(sr - 1, sc + 1, dr, dc, psf + "east", visited);
        }
        visited[sr][sc] = false;

        return count;
    }

    // shortest and longest paths
    public static class pair{
        int longestPathlength = 0;
        String longestPath = "";

        pair(int longestPathLength)
    }

    // public static String printPath(String ans){
        
    // }

    public static void main(String args[]){
        Scanner scn = new Scanner(System.in);
        int m = scn.nextInt();
        int n = scn.nextInt();

        boolean []visited = new boolean[m][n];
        int count = eightDirectionsCall(0,0,m - 1,n - 1,"",visited);
        System.out.println(count);
    }
}
