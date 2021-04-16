import java.util.*;

public class l003 {
    public static int printTreePath(int n){
        if(n == 0 || n == 1){
            return n;
        }

        int ans = 0;
        ans += printTreePath(n - 1);

        ans += printTreePath(n - 2);

        return ans + 3;
    }

    public static int subsequence(String ques, int idx, String ans) {
        if (idx == ques.length()) {
            System.out.println(ans);
            return 1;
        }

        char ch = ques.charAt(idx);

        int count = 0;
        count += subsequence(ques, idx + 1, ans + ch);
        count += subsequence(ques, idx + 1, ans);

        return count;
    }

    public static int ratInAMaze(int sr,int sc,int dr,int dc,boolean vis[][],int dir[][],String dirS[],String ans){
        if(sr == dr && sc == dc){
            System.out.println(ans);
            return 1;
        }

        int n = vis.length;
        int m = vis[0].length;

        int count = 0;
        vis[sr][sc] = true;
        for(int i = 0; i < dir.length; i++){
            int r = sr + dir[i][0];
            int c = sc + dir[i][1];

            if(r >= 0 && c >= 0 && r < n && c < m && !vis[r][c]){
                count += ratInAMaze(r,c,dr,dc,vis,dir,dirS,ans + dirS[i]);
            }
        }
        vis[sr][sc] = false;

        return count;
    }

    public static void ratInMaze(){
        boolean visited[][] = new boolean[3][3];
        int dir[][] = { { 1, 0 }, { -1, 0 }, { 1, 1 }, { -1, -1 }, { 0, 1 }, { 0, -1 }, { -1, 1 }, { 1, -1 } };
        String dirS[] = { "D", "U", "S", "N", "R", "L", "E", "W" };

        System.out.println(ratInAMaze(0,0,2,2,visited,dir,dirS,""));
    }

    // public static int floodFill(int sr, int sc, int er, int ec, boolean[][] vis, int[][] dir, String[] dirS,
    //         String ans) {
    //     if (sr == er && sc == ec) {
    //         System.out.println(ans);
    //         return 1;
    //     }

    //     int n = vis.length; // no of rows
    //     int m = vis[0].length; // no of col

    //     vis[sr][sc] = true; // mark

    //     int count = 0;
    //     for (int d = 0; d < dir.length; d++) { // call for all unvisited nbr's
    //         int r = sr + dir[d][0];
    //         int c = sc + dir[d][1];

    //         if (r >= 0 && c >= 0 && r < n && c < m && !vis[r][c])
    //             count += floodFill(r, c, er, ec, vis, dir, dirS, ans + dirS[d]);
    //     }

    //     vis[sr][sc] = false;

    //     return count;
    // }

    // public static void floodfill() {
    //     boolean[][] vis = new boolean[3][3]; // vector<vector<bool>> vis(3,vector<bool>(3,false));
    //     //int[][] dir = { { 1, 0 }, { -1, 0 }, { 1, 1 }, { -1, -1 }, { 0, 1 }, { 0, -1 }, { -1, 1 }, { 1, -1 } };
    //     //String[] dirS = { "D", "U", "S", "N", "R", "L", "E", "W" };
 
    //     int[][] dir = { { 1, 0 }, { 0, 1 }, { 1, 1 } };
    //     String[] dirS = { "V", "H", "D" };
    //     System.out.println(floodFill(0, 0, 2, 2, vis, dir, dirS, ""));
    // }

    public static void main(String args[]) {
        //Scanner scn = new Scanner(System.in);
        //int n = scn.nextInt();
        //int ans = printTreePath(n);
        //System.out.println(ans);
        ratInMaze();
        //floodfill();
    }
}
