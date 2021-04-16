import java.util.*;

public class crossword{
    public static void display(char [][]grid){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean isPossibleToPlaceH(char grid[][],String cities[],int r,int c,int idx){
        String word = cities[idx];
        if(c - 1 >= 0 && grid[r][c - 1] != '+'){
            return false;
        }else if(c + word.length() < grid[0].length && grid[r][c + word.length()] != '+'){
            return false;
        }

        for(int i = 0; i < word.length(); i++){
            if(grid[r][c + i] >= grid[0].length){
                return false;
            }

            if(grid[r][c + i] == '-' || grid[r][c + i] == word.charAt(i)){
                continue;
            } else{
                return false;
            }
        }
        return true;
    }

    public static boolean[] placeH(char grid[][],String cities[],int r,int c,int idx){
        String word = cities[idx];
        boolean []charLoc = new boolean[word.length()];
        for(int i = 0; i < word.length(); i++){
            if(grid[r][c + i] == '-'){
                charLoc[i] = true;
                grid[r][c + i] = word.charAt(i); 
            }
        }
        return charLoc;
    }

    public static void unPlaceH(char grid[][],String cities[],int r,int c,int idx,boolean []charLoc){
        String word = cities[idx];
        for (int i = 0; i < word.length(); i++) {
            if (charLoc[i]) {
                grid[r][c + i] = '-';
            }
        }
    }

    public static boolean isPossibleToPlaceV(char grid[][],String cities[], int r, int c, int idx) {
        String word = cities[idx];
        if (r - 1 >= 0 && grid[r - 1][c] != '+') {
            return false;
        } else if (r + word.length() < grid.length && grid[r + word.length()][c] != '+') {
            return false;
        }

        for (int i = 0; i < word.length(); i++) {
            if (grid[r + i][c] >= grid[0].length) {
                return false;
            }

            if (grid[r + i][c] == '-' || grid[r + i][c] == word.charAt(i)) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    public static boolean[] placeV(char grid[][],String cities[], int r, int c, int idx) {
        String word = cities[idx];
        boolean[] charLoc = new boolean[word.length()];
        for (int i = 0; i < word.length(); i++) {
            if (grid[r + i][c] == '-') {
                charLoc[i] = true;
                grid[r + i][c] = word.charAt(i);
            }
        }
        return charLoc;
    }

    public static void unPlaceV(char grid[][],String cities[], int r, int c, int idx,boolean[] charLoc) {
        String word = cities[idx];
        for(int i = 0; i < word.length(); i++){
            if(charLoc[i]){
                grid[r + i][c] = '-';
            }
        }
    }

    public static int crossword(char grid[][],String cities[],int idx){
        if(idx == cities.length){
            display(grid);
            return 1;
        }
        
        int count = 0;
        String word = cities[idx];
        for(int i = 0 ; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == '-' || grid[i][j] == word.charAt(0)){
                    if(isPossibleToPlaceH(grid,cities, i, j, idx)){
                        boolean charLoc[] = placeH(grid,cities, i, j, idx);
                        count += crossword(grid,cities,idx + 1);
                        unPlaceH(grid,cities, i, j, idx,charLoc);
                    }

                    if(isPossibleToPlaceV(grid,cities, i, j, idx)){
                        boolean charLoc[] = placeV(grid,cities, i, j, idx);
                        count += crossword(grid,cities, idx + 1);
                        unPlaceV(grid,cities, i, j, idx,charLoc);
                    }
                }
            }
        }

        return count;
    }
    public static void main(String args[]){
        Scanner scn = new Scanner(System.in);

        char grid[][] = new char[10][10];
        for(int i = 0; i < grid.length; i++){
            String str = scn.nextLine();
            grid[i] = str.toCharArray();
        }

        String s = scn.nextLine();
        String cities[] = s.split(";");
        
        System.out.println(crossword(grid,cities,0));
        scn.close();
    }
}