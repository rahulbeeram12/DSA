public class stack {

    private int tos;
    private int size;
    private int arr[];
    private int capacity;

    protected void initialize(int size) {
        this.arr = new int[size];
        this.size = 0;
        this.tos = -1;
    }

    protected int capacity(){
        return this.capacity;
    }

    public stack() {
        initialize(10);
    }

    public stack(int size) {
        initialize(size);
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    protected void StackEmptyException() throws Exception {
        throw new Exception("Stack is Empty");
    }

    protected void StackFullException() throws Exception {
        throw new Exception("Stack is Full");
    }

    public void push(int val) throws Exception {
        if (this.tos == this.capacity) {
            StackFullException();
        }
        push_(val);
    }

    protected void push_(int val){
        this.tos++;
        this.arr[this.tos] = val;
        this.size++;
    }

    protected int top_(){
        return this.arr[tos];
    }

    public int top() throws Exception {
        if (this.tos == -1) {
            StackFullException();
        }

        return top_();
    }

    protected int pop_(){
        int val = this.arr[tos];
        this.arr[tos] = -1;
        this.tos--;
        this.size--;

        return val;
    }

    public int pop() throws Exception {
        if (this.tos == -1) {
            StackEmptyException();
        }

        return pop_();
    }
}