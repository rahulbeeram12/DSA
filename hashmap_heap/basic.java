public class basic {
    
    public static int heightofTree(int arr[],int idx){
        if(idx >= arr.length){
            return -1;
        }
        
        int lh = heightofTree(arr, 2 * idx + 1);
        int rh = heightofTree(arr, 2 * idx + 2);
        
        return Math.max(lh,rh) + 1;
    }

    public static void main(String args[]) {
        
    }   
}