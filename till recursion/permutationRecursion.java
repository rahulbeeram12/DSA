public class permutationRecursion {
    
    //this question belongs to shortest and longest paths in a maze with multiple directions (generic)
    public static void mazePaths(){
        int dir[][] = {{0,1},{-1,0},{1,0},{0,-1}};
        String dirS[] = {"R","U","D","L"};
        boolean[][] vis = new boolean[3][3];
        pair ans = longestPath(0,0,2,2,dir,dirS,vis);
        System.out.println(ans.longestPath);
        System.out.println(ans.longestPathLength);
    }

    public static class pair{
        int longestPathLength = 0;
        String longestPath = "";

        pair(int longestPathLength,String longestPath){
            this.longestPathLength = longestPathLength;
            this.longestPath = longestPath;
        }
    }

    public static pair longestPath(int sr,int sc,int dr,int dc,int dir[][],String dirS[],boolean vis[][]){
        if(sr == dr && sc == dc){
            return new pair(0,"");
        }

        pair myans = new pair((int)-1e8,"");

        vis[sr][sc] = true;
        for(int i = 0; i < dir.length; i++){
            int r = sr + dir[i][0];
            int c = sc + dir[i][1];

            if(r >= 0 && c >= 0 && r <= dr && c <= dc && !vis[r][c]){
                pair rAns = longestPath(r,c,dr,dc,dir,dirS,vis);
                if(rAns.longestPathLength + 1 > myans.longestPathLength){
                    myans.longestPathLength = rAns.longestPathLength + 1;
                    myans.longestPath = dirS[i] + rAns.longestPath;
                }
            }
        }
        vis[sr][sc] = false;
        return myans;
    }

    public static pair shortestPath(int sr, int sc, int dr, int dc, int dir[][], String dirS[], boolean vis[][]) {
        if (sr == dr && sc == dc) {
            return new pair(0, "");
        }

        pair myans = new pair((int)1e8, "");

        vis[sr][sc] = true;
        for (int i = 0; i < dir.length; i++) {
            int r = sr + dir[i][0];
            int c = sc + dir[i][1];

            if (r >= 0 && c >= 0 && r <= dr && c <= dc && !vis[r][c]) {
                pair rAns = longestPath(r, c, dr, dc, dir, dirS, vis);
                if (rAns.longestPathLength + 1 < myans.longestPathLength) {
                    myans.longestPathLength = rAns.longestPathLength + 1;
                    myans.longestPath = dirS[i] + rAns.longestPath;
                }
            }
        }
        vis[sr][sc] = false;
        return myans;
    }

    public static int permuteInfi(int arr[],int target,String ans){
        if(target == 0){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int i = 0; i < arr.length; i++){
            if(target - arr[i] >= 0) count += permuteInfi(arr,target - arr[i],ans + arr[i]);
        }

        return count;
    }

    public static int combInfi(int arr[],int target,int start,String ans){
        if(target == 0){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int i = start; i < arr.length; i++){
            if(target - arr[i] >= 0) count += combInfi(arr,target - arr[i],i,ans + arr[i]);
        }

        return count;
    }

    // public static int permutation(int arr[],int target,String ans){
    //     if(target == 0){
    //         System.out.println(ans);
    //         return 1;
    //     }

    //     for(int i = 0; i < arr.length; i++){
    //         if(target - arr[i] >= 0){
                
    //         }
    //     }
    // }
    
    public static int combination(int arr[],int target,int idx,String ans){
        if(target == 0){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int i = idx; i < arr.length; i++){
            if(target - arr[i] >= 0){
                count += combination(arr,target - arr[i],i + 1,ans + arr[i]);
            }
        }
        return count;
    }

    public static int permutation(int arr[],int target,String ans){
        if(target == 0){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int i = 0; i < arr.length; i++){
            int ele = arr[i];
            if(target - ele >= 0 && arr[i] >= 0){
                arr[i] = -arr[i];
                count += permutation(arr,target - ele,ans + ele);
                arr[i] = -arr[i];
            }
        }
        return count;
    }

    public static int combinationSubSeq(int arr[],int idx,int target,String ans){
        if(idx == arr.length || target == 0){
            if(target == 0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if(target - arr[idx] >= 0) count += combinationSubSeq(arr,idx + 1,target - arr[idx],ans + arr[idx]);
        count += combinationSubSeq(arr,idx + 1,target,ans);

        return count;
    } 

    public static int combinationsInfiSubSeq(int arr[],int target,int idx,String ans){
        if(idx == arr.length || target == 0){
            if(target == 0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if(target - arr[idx] >= 0) count += combinationsInfiSubSeq(arr,target - arr[idx],idx,ans + arr[idx]);
        combinationsInfiSubSeq(arr,target,idx + 1,ans);

        return count;
    }

    public static int permutationInfiSubSeq(int arr[],int idx,int target,String ans){
        if(idx == arr.length || target == 0){
            if(target == 0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

            int count = 0;
            if(target - arr[idx] >= 0) count += permutationInfiSubSeq(arr, 0, target - arr[idx], ans + arr[idx]);
            count += permutationInfiSubSeq(arr, idx + 1 , target, ans);
        
        return count;
    }

    public static int permutationSubSeq(int arr[], int target,int idx, String ans) {
        if(idx == arr.length || target == 0){
            if(target == 0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        int ele = arr[idx];
        if(target - arr[idx] >= 0 && arr[idx] > 0){
            arr[idx] = -arr[idx];
            count += permutationSubSeq(arr,target - ele,0,ans + ele);
            arr[idx] = -arr[idx];
        }
        count += permutationSubSeq(arr, target, idx + 1, ans);
        return count;
    }

    

    public static void main(String args[]){
        //mazePaths();
        int arr[] = {2,3,5,7};
        int target = 10;
        //System.out.println(permuteInfi(arr,target,""));
        //System.out.println(combInfi(arr,target,0,""));
        //System.out.println(combination(arr, target, 0, ""));
        //System.out.println(permutation(arr, target,""));
        //System.out.println(combinationsInfiSubSeq(arr,target,0,""));
        //System.out.println(permutationInfiSubSeq(arr,0,target,""));
        // System.out.println(permutationSubSeq(arr,target,0,""));
        //System.out.println(combinationSubSeq(arr,target,0,""));
    }
}
