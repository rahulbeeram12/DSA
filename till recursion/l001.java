import java.util.*;

public class l001{
    public int lengthOfLongestSubstring(String s) {
        if(s.length() <= 1){
            return s.length();
        }
        
        int n = s.length();
        
        int freq[] = new int[128];
        int si = 0;
        int ei = 0;
        int len = 0;
        int head = 0;
        int count = 0;
        int Mei = 0;
        int Msi = 0;
        
        while(ei < n){
            if(freq[s.charAt(ei++)]++ > 0) count++;
            
            while(count > 0){
                if(freq[s.charAt(si++)]-- > 1) count--;
            }
            
            if(ei - si > len){
                len = ei - si;
                //Msi = si;
                //Mei = ei;
            }
        }
        
        return len;
    }

    public String minWindow(String s, String t) {
        if(s.length() == 0){
            return "";
        }
        
        int freq[] = new int[128];
        for(int i = 0; i < t.length(); i++){
            freq[t.charAt(i)]++;
        }
        
        int requirement = 0;
        for(int i = 0 ; i < freq.length; i++){
            requirement += freq[i];
        }
        
        int si = 0;
        int ei = 0;
        int len = (int)1e8;
        int head = 0;
        int n = s.length();
        
        while(ei < n){
            if(freq[s.charAt(ei++)]-- > 0) requirement--;
                
            while(requirement == 0){
                if(ei - si < len) len = ei - (head = si);
                
                if(freq[s.charAt(si++)]++ == 0) requirement++;
            }
        }
        
        return len == (int)1e8 ? "" : s.substring(head, head + len);
    }

    public static int smallestDistinctWindow(String s){
	    if(s.length() <= 1){
	        return s.length();                               //window with no repeating characters
	    }

	    int freq[] = new int[128];
	    int si = 0;
	    int ei = 0;
	    int len = (int)1e8;
	    int requirement = 0;
	    
	    for(int i = 0 ;i < s.length(); i++){
	        freq[s.charAt(i)] = 1;
	    }
	    
	    for(int i = 0; i < freq.length; i++){
	        requirement += freq[i];
	    }
	    int n = s.length();
	    while(ei < n){
	        if(freq[s.charAt(ei++)]-- > 0) requirement--;
	        
	        while(requirement == 0){
	            if(ei - si <= len) len = (ei - si);
	            
	            if(freq[s.charAt(si++)]++ == 0) requirement++;
	        }
	    }
	    
	    return len;
    }
    
    //Smallest window in a string containing all the characters of another string
    public static String smallestWindow(String S, String P){
        int freq[] = new int[128];
	    int si = 0;
	    int ei = 0;
	    int len = (int)1e8;
	    int requirement = 0;
	    int head = 0;                                      // similar questions as minwindow
	    
	    for(int i = 0 ;i < P.length(); i++){
	        freq[P.charAt(i)]++;
	    }
	    
	    for(int i = 0; i < freq.length; i++){
	        requirement += freq[i];
	    }
	    int n = S.length();
	    while(ei < n){
	        if(freq[S.charAt(ei++)]-- > 0) requirement--;
	        
	        while(requirement == 0){
	            if(ei - si < len) len = (ei - (head = si));
	            
	            if(freq[S.charAt(si++)]++ == 0) requirement++;
	        }
	    }
	    
	    return len == (int)1e8 ? "-1" : S.substring(head,head + len);
    
    }

    //given a string s, find the longest substring t that contains atmost 2 distinct characters
    public static int lengthOfLongestSubstringTwoDistinctCharacters(String s){
        int si = 0;
        int ei = 0;

        int distinctCount = 0;
        int freq[] = new int[128];
        int n = s.length();
        int len = 0;
        while(ei < n){
            if(freq[s.charAt(ei++)]++ == 0) distinctCount++;

            while(distinctCount > 2){
                if(freq[s.charAt(si++)]-- == 1) distinctCount--;
            }
            len = (ei - si) > len ? ei - si : len;
        }

        return len;
    }

    public static int lengthOfLongestSubstringKDistinctCharacters(String s){
        int si = 0;
        int ei = 0;

        int distinctCount = 0;                        //same approach as 2 distinct characters
        int freq[] = new int[128];
        int n = s.length();
        int len = 0;
        while(ei < n){
            if(freq[s.charAt(ei++)]++ == 0) distinctCount++;

            while(distinctCount > k){
                if(freq[s.charAt(si++)]-- == 1) distinctCount--;
            }
            len = (ei - si) > len ? ei - si : len;
        }

        return len;
    }

    //Maximum number of vowels in a substring of a given length
    public static boolean isVowel(char ch){
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

    public static int maximumNumberOfVowelsInASubstringOfGivenLength(String s,int k){
        int si = 0;
        int ei = 0;
        int len = 0;
        int vowelCount = 0;
        int MaxVC = 0;
        int len = 0;
        int n = s.length();
        while(ei < n){
            if(isVowel(s.charAt(ei++)) vowelCount++;

            if(ei - si == k){
                MaxVC = Math.max(MaxVC,vowelCount);
                if(isVowel(s.charAt(si++))) vowelCount--;
            }
        }
        return MaxVC;
    }
//----------------------------------------------------FINDING MIN / MAX IN SUBARRAY TYPE OF ARRAY QUESTIONS--------------------------------------------------------
    
    int longSubarrWthSumDivByK(int arr[], int n, int k) {
        int mlen = 0;
        int sum = 0;
        HashMap<Integer, Integer> hm = new HashMap<>();
        hm.put(0, -1);

        for (int i = 0; i < arr.length; i++) {
            int val = arr[i];
            sum += val;

            int rem = sum % k;
            if (rem < 0) {
                rem += k;
            }

            if (hm.containsKey(rem)) {
                int idx = hm.get(rem);
                int len = i - idx;
                if (len > mlen) {
                    mlen = len;
                }
            } else {
                hm.put(rem, i);
            }
        }
        return mlen;
    }

    static int countSubarrWithEqualZeroAndOne(int arr[], int N)
    {
        int ans = 0;
        HashMap<Integer,Integer> hm = new HashMap<>();
        hm.put(0,1);
        
        int sum = 0;
        for(int i = 0; i < arr.length; i++){
            int val = arr[i];
            if(val == 0){
                sum += -1;
            }
            sum += val;
            
            if(hm.containsKey(sum)){
                int freq = hm.get(sum);
                ans += freq;
                hm.put(sum,freq + 1);
            }else{
                hm.put(sum,1);
            }
        }
        return ans;
    }
    //930 1248 1004 904 209

    public int numSubarraysWithSum(int[] arr, int s) {

        HashMap<Integer, Integer> hm = new HashMap<>();
        hm.put(0, 1);
        int sum = 0;
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            int val = arr[i];
            sum += val;

            int ans = sum - s;
            if (hm.containsKey(ans)) {
                int freq = hm.get(ans);
                count += freq;
            }
            hm.put(sum, hm.getOrDefault(sum, 0) + 1);
        }

        return count;
    }

    //another approach of the above
    public int numSubarraysWithAtMostKSum(int[] arr, int S) {
        int ei = 0;
        int si = 0;
        int count = 0;
        int sum = 0;
        int n = arr.length;
        while(ei < n){
            sum += arr[ei++];

            while(sum > k){
                sum -= arr[si++];
            }

            count += ei - si;
        }

        return count;
    }

    public int numSubArraysWithSum(int arr[],int S){
        int n = arr.length;
        if(n == 0) return 0;
        return numSubarraysWithAtMostKSum(arr,S) - (S > 0 ? numSubarraysWithAtMostKSum(arr,S-1) : 0);
    }


    //1248
    public int subArrayAtmostKOddNums(int arr[], int k) {
        int si = 0;
        int ei = 0;
        int oddCount = 0;
        int n = arr.length;
        int sum = 0;
        int res = 0;

        while (ei < n) {
            if ((arr[ei++] & 1) == 1)
                oddCount++;

            while (oddCount > k) {
                if ((arr[si++] & 1) == 1)
                    oddCount--;
            }

            res += ei - si;
        }

        return res;
    }

    public int numberOfSubarrays(int[] arr, int k) {
        int n = arr.length;
        if (n == 0) {
            return 0;
        }

        return subArrayAtmostKOddNums(arr, k) - subArrayAtmostKOddNums(arr, k - 1);
    }

    //1004
    public int longestOnes(int[] arr, int k) {
        int ei = 0;
        int si = 0;
        int len = 0;
        int n = arr.length;
        int zeroCount = 0;
        while (ei < n) {
            if (arr[ei++] == 0)
                zeroCount++;

            while (zeroCount > k) {
                if (arr[si++] == 0)
                    zeroCount--;
            }

            len = Math.max(ei - si, len);
        }

        return len;
    }

    public int totalFruit(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }

        int freq[] = new int[40002];
        int si = 0;
        int ei = 0;
        int n = arr.length;
        int distCount = 0;
        int len = 0;

        while (ei < n) {
            if (freq[arr[ei++]]++ == 0)
                distCount++;

            while (distCount > 2) {
                if (freq[arr[si++]]-- == 1)
                    distCount--;
            }

            len = ei - si > len ? ei - si : len;
        }

        return len;
    }

    //-----------------------------------------------leetcode 2D array kadane's Questions------------------------------------------------------ 

    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] += matrix[i - 1][j];
            }
        }

        int freq = 0;
        for (int base = 0; base < n; base++) {
            for (int row = base; row < n; row++) {
                HashMap<Integer, Integer> map = new HashMap<>();
                map.put(0, 1);
                int sum = 0;
                for (int j = 0; j < m; j++) {
                    sum += matrix[row][j] - (base != 0 ? matrix[base - 1][j] : 0);
                    int key = sum - target;
                    if (map.containsKey(key)) {
                        freq += map.get(key);
                    }
                    map.put(sum, map.getOrDefault(sum, 0) + 1);
                }
            }
        }

        return freq;
    }

    public int maxSumSubmatrix(int[][] matrix, int k) {
        int n = matrix.length;
        int m = matrix[0].length;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] += matrix[i - 1][j];
            }
        }

        int maxSum = -(int) 1e8;

        for (int base = 0; base < n; base++) {
            for (int row = base; row < n; row++) {

                int gSum = -(int) 1e8, rsum = 0;
                for (int j = 0; j < m; j++) {
                    int val = matrix[row][j] - (base != 0 ? matrix[base - 1][j] : 0);

                    rsum = Math.max(val, rsum + val);
                    gSum = Math.max(rsum, gSum);
                    if (gSum == k)
                        return gSum;
                }

                if (gSum < k) {
                    maxSum = Math.max(gSum, maxSum);
                    continue;
                }

                TreeSet<Integer> map = new TreeSet<>();
                map.add(0);
                int sum = 0;
                for (int j = 0; j < m; j++) {
                    sum += matrix[row][j] - (base != 0 ? matrix[base - 1][j] : 0);

                    if (map.contains(sum - k)) {
                        return k;
                    }
                    Integer val = map.ceiling(sum - k);

                    if (val != null) {
                        maxSum = Math.max(maxSum, sum - val);
                    }
                    map.add(sum);
                }
            }
        }

        return maxSum;
    }

    public static void main(){

    }
}