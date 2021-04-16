public class recursion_bitmasking {
    
    public static void toggle(int n,int r,int c){
        row[r] = !row[r];
        col[c] = !col[c];
        diag[r + c] = !diag[r + c];
        adiag[r - c + n - 1] = !adiag[r - c + n - 1];
    }

    static boolean row[];
    static boolean col[];
    static boolean diag[];
    static boolean adiag[];

    public static int nQueen03(int n,int r,int tnq,String ans){
        if(tnq == 0){
            System.out.println(ans);
            return 1;
        }    

        int count = 0;
        for(int c = 0; c < n ; c++){
            if(!row[r] && !col[c] && !diag[r + c] && !adiag[r - c + n - 1]){
                toggle(n,r,c);
                count += nQueen03(n,r + 1,tnq - 1,ans + "(" + r + "," + c + ")" + " ");
                toggle(n,r,c);
            }
        }
        return count;
    }

    static int ro = 0;
    static int co = 0;
    static int diagonal = 0;
    static int rdiagonal = 0;

    public static void toggleBit(int n,int r,int c){
        co ^= (1 << c);
        diagonal ^= (1 << (r + c));
        rdiagonal ^= (1 << (r - c + n - 1)); 
    }

    public static int nQueen03_bitmasking(int n, int r, int tnq, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int c = 0; c < n; c++) {
            if ((co & (1 << c)) == 0 && (diagonal & (1 << (r + c))) == 0 && (rdiagonal & (1 << (r - c + n - 1))) == 0) {
                //toggle(n, r, c);
                toggle(n,r,c);
                count += nQueen03_bitmasking(n, r + 1, tnq - 1, ans + "(" + r + "," + c + ")" + " ");
                //toggle(n, r, c);
                toggle(n,r,c);
            }
        }
        return count;
    }

    public static void main(String args[]){
        int n = 4;
        row = new boolean[n];
        col = new boolean[n];
        diag = new boolean[n + n - 1];
        adiag = new boolean[n + n - 1];
        
        System.out.println(nQueen03(n,0,n,""));
    }
}