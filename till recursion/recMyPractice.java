import java.util.*;

public class recMyPractice {

    public static boolean isSafeToPlaceQueen(boolean[][] board,int r,int c){
        int dir[][] = {{0,-1},{-1,-1},{-1,0},{-1,1}};

        for(int d = 0; d < dir.length; d++){
            for(int rad = 1; rad < board.length; rad++) {
                int x = r + rad * dir[d][0];
                int y = c + rad * dir[d][1];

                if(x >= 0 && y >= 0 && x < board.length && y < board[0].length){
                    if(board[x][y] == true) return false;
                }else break;
            }
        }

        return true;
    }

    public static int nQueenComb(boolean board[][],int idx,int tnq,String ans){
        if(tnq == 0){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        int n = board.length;
        int m = board[0].length;

        for(int i = idx; i < n * m; i++){
            int r = i / m;
            int c = i % m;

            if(isSafeToPlaceQueen(board,r,c)){
                board[r][c] = true;
                count += nQueenComb(board,i + 1,tnq - 1,ans + "(" + r + "," + c + ")" + " ");
                board[r][c] = false;
            }
        }

        return count;
    }
    public static void main(String args[]){
        boolean[][] board = new boolean[4][4];
        System.out.println(nQueenComb(board,0,4,""));
    }
}
