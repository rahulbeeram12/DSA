public class heapsort{
    bool compare(int arr[],int isIncreasing,int i,int j){
        if(isIncreasing){
            return arr[i] > arr[j];
        }else{
            return arr[i] < arr[j];
        }
    }

    public static void downheapify(int arr[],boolean isIncreasing,int pi,int li){
        int maxidx = pi;
        int lci = 2 * pi + 1;
        int rci = 2 * pi + 2;

        if(lci <= li && compare(lci,maxidx)){
            maxidx = lci;
        }

        if (rci <= li && compare(rci, maxidx)) {
            maxidx = rci;
        }

        if(maxidx != pi){
            swap(arr,maxidx,pi);
            downheapify(arr, isIncreasing, maxidx, li);
        }
    }

    public static void swap(int arr[],int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void heapSort(int arr[],boolean isIncreasing){
        int n = arr.length;
        for (int i = li; i >= 0; i--) {
            downheapify(arr, isIncreasing, i, li);
        }

        while(li >= 0){
            swap(0,li--);
            downheapify(arr, isIncreasing, 0, li);
        }
    }

    public static void main(String args[]){
        int arr[] = { 10, 20, 30, -2, -3, -4, 5, 6, 7, 8, 9, 22, 11, 13 };

        heapsort(arr,true);
    }
}
