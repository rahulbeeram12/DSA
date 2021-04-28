import java.util.ArrayList;

public class heap {    

    private ArrayList<Integer> list;
    private boolean isMaxHeap = true;

    private void defaultValue(boolean isMaxHeap){
        this.list = new ArrayList<>();
        this.isMaxHeap = isMaxHeap;
    }

    private void constructHeap(){  // seems nlogn but its n
        for(int i = this.list.size() - 1; i >= 0; i--){
            downHeapify(i);
        }
    }

    public heap(){
        defaultValue(true);
    }

    public heap(boolean isMaxHeap){
        defaultValue(isMaxHeap);
    }

    public heap(int arr[],boolean isMaxHeap){
        defaultValue(isMaxHeap);
        for(int x : arr)
            this.list.add(x);

        constructHeap();
    }

    public int size(){
        return this.list.size();
    }

    public boolean isEmpty(){
        return this.list.size() == 0;
    }

    private void upHeapify(int ci){
        int pi = (ci - 1) / 2;

        if(pi >= 0 && compare(pi,ci)){
            swap(pi,ci);
            upHeapify(pi);
        }
    }

    public void add(int data){
        this.list.add(data);
        int n = this.list.size();

        upHeapify(n - 1);
    }

    private boolean compare(int i, int j){
        if(isMaxHeap)
            return list.get(i) > list.get(j);
        else
            return list.get(i) < list.get(j);
    }

    public int remove(){
        int rele = list.get(0);
        swap(0,this.list.size() - 1);
        this.list.remove(this.list.size() - 1);

        downHeapify(0);
        return rele;
    }

    public int top(){
        return this.list.get(0);
    }

    private void swap(int pi,int maxidx){
        int temp = list.get(pi);
        list.set(pi,list.get(maxidx));
        list.set(maxidx,temp);
    }

    private void downHeapify(int pi){
        int maxidx = pi;
        int lidx = 2 * pi + 1;
        int ridx = 2 * pi + 2;
        int n = list.size();

        if(lidx < n && compare(lidx,maxidx)){
            maxidx = lidx;
        }

        if (ridx < n && compare(ridx,maxidx)) {
            maxidx = ridx;
        }

        if(maxidx != pi){
            swap(maxidx,pi);
            downHeapify(maxidx);
        }
    }
}
