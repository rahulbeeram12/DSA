public class utility {
    public static void print2D(int arr[][]) {
        for (int d[] : arr) {
            for (int ele : d) {
                System.out.print(ele + " ");
            }
            System.out.println();
        }
    }
}
