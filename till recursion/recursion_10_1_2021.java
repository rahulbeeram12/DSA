public class recursion_10_1_2021 {

    public static int queenCombination1D(int boxes,int bno,int tnq,int qpsf,String ans){
        if(qpsf == tnq){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int i = bno; i < boxes; i++){
            count += queenCombination1D(boxes, i + 1, tnq, qpsf + 1, ans + "b" + i + "q" + qpsf + " ");
        }

        return count;
    }

    public static int queenPermutation1D(boolean boxes[], int bno, int tnq, int qpsf, String ans) {
        if (qpsf == tnq) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = 0; i < boxes.length; i++) {
            if(!boxes[i]){
                boxes[i] = true;
                count += queenPermutation1D(boxes, i + 1, tnq, qpsf + 1, ans + "b" + i + "q" + qpsf + " ");
                boxes[i] = false;
            }
        }

        return count;
    }

    public static int queenCombination2D(int [][]board,int idx,int tnq,String ans){
        if(tnq == 0){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        int n = board.length;
        int m = board[0].length;
        for(int i = idx; i < n * m; i++){
            int r = i / m;
            int c = r % m;

            count += queenCombination2D(board, i + 1, tnq - 1, ans + "(" + r + "," + c +")" + " ");
        }

        return count;
    }

    public static int queenPermutation2D(boolean board[][],int tnq,String ans){
        if(tnq == 0){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        int n = board.length;
        int m = board[0].length;
        for(int i = 0; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            
            if(!board[r][c]){
                board[r][c] = true;
                count += queenPermutation2D(board, tnq - 1, ans + "(" + r + "," + c + ")" + " ");
                board[r][c] = false;
            }        
        }

        return count;
    }

    //queen_difference
    public static boolean isSafeToPlaceQueen(boolean boxs[][],int r,int c){
        //vertical
        // for(int i = r - 1; i >= 0 ; i--){
        //     if(boxs[i][c] == true){
        //         return false;
        //     }
        // }

        // //north-west
        // for(int i = r - 1, j = c - 1; j >= 0 && i >= 0; j--,i--){
        //     if(boxs[i][j] == true){
        //         return false;
        //     }
        // }

        // //north-east
        // for(int i = r - 1, j = c + 1; j < boxs.length && i >= 0; i--,j++){
        //     if(boxs[i][j] == true){
        //         return false;
        //     }
        // }    

        int dir[][] = {{0,-1},{-1,-1},{-1,0},{-1,1}};
        for(int d = 0; d < dir.length; d++){
            for(int rad = 1; rad < boxs.length; rad++){
                int x = r + rad * dir[d][0];
                int y = c + rad * dir[d][1];

                if(x >= 0 && y >= 0 && x < boxs.length && y < boxs.length){
                    if(boxs[x][y] == true){
                        return false;
                    }
                }else break;
            }   
        }

        return true;
    }

    public static int nQueen01(boolean boxs[][],int idx,int tnq,String ans){
        if(tnq == 0){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        int n = boxs.length;
        int m = boxs[0].length;

        for(int i = idx; i < n * m; i++){
            int r = i / m;
            int c = i % m;

            if(isSafeToPlaceQueen(boxs,r,c)) {
                boxs[r][c] = true;
                count += nQueen01(boxs,i + 1,tnq - 1,ans + "(" + r + "," + c + ") ");
                boxs[r][c] = false;
            }
        }
        return count;
    }

    static boolean row[];
    static boolean col[];
    static boolean diag[];
    static boolean rDiag[];

    public static void toggle(int n, int r,int c){
        row[r] = !row[r];
        col[c] = !col[c];
        diag[r + c] = !diag[r + c];
        rDiag[r - c + n - 1] = !rDiag[r - c + n - 1]; 
    }

    public static int nQueen02(int n,int idx,int tnq,String ans) {
        if(tnq == 0){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int i = idx; i < n * n; i++){
            int r = i / n;
            int c = i % n;

            if(!row[r] && !col[c] && !diag[r + c] && !rDiag[r - c + n - 1]){
                toggle(n,r,c);
                count += nQueen02(n,i + 1,tnq - 1,ans + "(" + r + "," + c + ")" + " ");
                toggle(n,r,c);
            }
        }

        return count;
    }

    public static void main(String args[]){
        //boolean boxs[][] = new boolean[4][4];
        //System.out.println(nQueen01(boxs,0,4,""));
        //System.out.println(queenCombination1D(5,0,3,0,""));
        // boolean boxes[] = new boolean[5];
        // System.out.println(queenPermutation1D(boxes,0,3,0,""));
        //boolean board[][] = new boolean[4][4];
        //System.out.println(queenPermutation2D(board,4,""));
        int n = 4;
        row = new boolean[n];
        col = new boolean[n];
        diag = new boolean[n + n - 1];
        rDiag = new boolean[n + n - 1];
        System.out.println(nQueen02(n,0,n,""));
    }
}