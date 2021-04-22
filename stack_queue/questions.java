import java.util.Stack;

public class questions {
    
    public static int[] ngeOnRight(int arr[]){
        int res[] = new int[arr.length];
        Arrays.fill(res,arr.length);

        Stack<Integer> st = new Stack<>();
        st.push(0);

        for(int i = 0; i < arr.length ; i++){
            while(st.size() > 0 && arr[st.peek()] < arr[i]){
                res[st.pop()] = i;
            }
            st.push(i);
        }

        return res;
    }

    public static int[] ngeOnLeft(){
        int res[] = new int[arr.length];
        Arrays.fill(res, -1);

        Stack<Integer> st = new Stack<>();
        st.push(0);

        for (int i = arr.length - 1; i >= 0; i--) {
            while (st.size() > 0 && arr[st.peek()] < arr[i]) {
                res[st.pop()] = i;
            }
            st.push(i);
        }

        return res;
    }

    public static int[] nseOnRight(){
        int res[] = new int[arr.length];
        Arrays.fill(res, arr.length);

        Stack<Integer> st = new Stack<>();
        st.push(0);

        for (int i = 0; i < arr.length; i++) {
            while (st.size() > 0 && arr[st.peek()] > arr[i]) {
                res[st.pop()] = i;
            }
            st.push(i);
        }

        return res;
    }

    public static int[] nseOnleft(){
        int res[] = new int[arr.length];
        Arrays.fill(res, -1);

        Stack<Integer> st = new Stack<>();
        st.push(0);

        for (int i = arr.length - 1; i >= 0; i--) {
            while (st.size() > 0 && arr[st.peek()] > arr[i]) {
                res[st.pop()] = i;
            }
            st.push(i);
        }

        return res;
    }

    // leetcode 503
    public int[] nge(int nums[], int res[]) {
        int n = nums.length;
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < 2 * n; i++) {
            while (st.size() > 0 && nums[st.peek()] < nums[i % n]) {
                res[st.pop()] = nums[i % n];
            }
            if (i < n)
                st.push(i);
        }
        return res;
    }

    public int[] nextGreaterElements(int[] nums) {
        int res[] = new int[nums.length];
        Arrays.fill(res, -1);
        return nge(nums, res);
    }

    // leetcode 901
    // online stock span

    public StockSpanner() {
        
    }

    public int next(int price) {

    }

    // gfg
    public int[] calculateSpan(int price[], int n)
    {
        int v[] = new int[n];
        int res[] = new int[n];
        Arrays.fill(v,-1);
        Stack<Integer> st = new Stack<>();
        
        for(int i = n - 1; i >= 0; i--){
            while(st.size() > 0 && price[st.peek()] < price[i]){
                v[st.pop()] = i;
            }
            st.push(i);
        }
        
        for(int i = 0; i < n; i++){
            res[i] = i - (v[i]);
        }
        
        return res;
    }

    // leetcode 20
    public static boolean isValid(String s){
        if (s.length() == 0)
            return false;

        Stack<Character> st = new Stack<>();
        st.push(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '{' || ch == '[') {
                st.push(ch);
            } else if (ch == ')') {
                if (st.size() > 0 && st.peek() == '(') {
                    st.pop();
                } else {
                    return false;
                }
            } else if (ch == '}') {
                if (st.size() > 0 && st.peek() == '{') {
                    st.pop();
                } else {
                    return false;
                }
            } else if (ch == ']') {
                if (st.size() > 0 && st.peek() == '[') {
                    st.pop();
                } else {
                    return false;
                }
            }
        }

        return st.size() == 0;
    }

    // leetcode 946
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int n = popped.length;
        int i = 0;
        Stack<Integer> st = new Stack<>();
        for(int ele : pushed){
            st.push(ele);
            while(st.size() > 0 && st.peek() == popped[i]){
                st.pop();
                i++;
            }
        }
        return i == n;
    }

    // leetcode 1249
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                st.push(i);
            } else if (ch == ')') {
                if (st.size() > 0 && s.charAt(st.peek()) == '(') {
                    st.pop();
                } else {
                    st.push(i);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (st.size() > 0 && st.peek() == i) {
                st.pop();
                continue;
            } else {
                sb.append(s.charAt(i));
            }
        }

        return sb.reverse().toString();
    }   

    // leetcode 32

    // Method 1
    public int longestValidParentheses(String s) {
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                st.push(i);
            } else if (ch == ')') {
                if (st.size() > 0 && s.charAt(st.peek()) == '(') {
                    st.pop();
                } else {
                    st.push(i);
                }
            }
        }

        int arr[] = new int[s.length()];
        for (int i = s.length() - 1; i >= 0; i--) {
            if (st.size() > 0 && st.peek() == i) {
                arr[i] = 0;
                st.pop();
            } else {
                arr[i] = 1;
            }
        }

        int count = 0;
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                count++;
            } else {
                max = Math.max(max, count);
                count = 0;
            }
        }

        return Math.max(count, max);
    }

    // Method 2
    public int longestValidParentheses(String s) {
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                st.push(i);
            } else if (ch == ')') {
                if (st.size() > 1 && s.charAt(st.peek()) == '(') {
                    st.pop();
                    max = Math.max(max, i - st.peek());
                } else {
                    st.push(i);
                }
            }
        }

        return max;
    }

    // leetcode 735 asteroid collision
    public int[] asteroidCollision(int[] asteroids) {

    }

    public static void main(String args[]){

    }
}