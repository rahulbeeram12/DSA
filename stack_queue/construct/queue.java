public class queue {
    private int size;
    private int arr[];
    private int capacity;
    private int front;
    private int back;

    protected int initialize(int size){
        this.size = size;
        this.arr = new int[size];
        this.capacity = size;
        this.front = 0;
        this.back = 0;
    }

    public queue(){
        initialize(10);
    }

    public queue(int size){
        initialize(size);
    }
    
    protected int capacity() {
        return this.capacity;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    protected void QueueEmptyException() throws Exception {
        throw new Exception("Queue is Empty");
    }

    protected void QueueFullException() throws Exception {
        throw new Exception("Queue is Full");
    }

    public void push(int val) throws Exception {
        if (this.size == this.capacity) {
            QueueFullException();
        }
        push_(val);
    }

    protected void push_(int val) {
        this.back = (++this.back % this.capacity);
        this.arr[this.back] = val;
        this.size++;
    }

    protected int front_() {
        this.front = (this.front % this.capacity);
        return this.arr[this.front];
    }

    public int front() throws Exception {
        if (this.size == this.capacity) {
            QueueFullException();
        }

        return front_();
    }

    public void display(){
        int k = this.front;
        for(int i = 0; i < this.size; i++){
            System.out.print(this.arr[k++] + " ");
            k = k % this.capacity;
        }
    }

    protected int pop_() {
        int rv = this.arr[this.front];
        this.arr[this.front] = 0;
        this.front = (++this.front % this.capacity);
        this.size--;
        return rv;
    }

    public int pop() throws Exception {
        if (this.size == 0) {
            QueueEmptyException();
        }

        return pop_();
    }
}