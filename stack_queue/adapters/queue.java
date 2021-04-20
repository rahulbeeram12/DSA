public class queue {
    private class node {
        int data = 0;
        node next = null;

        public node(int data) {
            this.data = data;
        }
    }

    private node head = null;
    private node tail = null;
    private int size = 0;

    protected void setPointers(node node) {
        this.head = node;
        this.tail = node;
    }

    public void addLast(node node) {
        if (head == null) {
            setPointers(node);
            return;
        }

        this.tail.next = node;
        this.tail = node;
    }

    public node removeFirst() {
        node rv = this.head;
        if (this.head == this.tail) {
            this.tail = null;
            this.head = null;
        } else {
            this.head = rv.next;
        }
        return rv;
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

    public void push(int data) {
        node node = new node(data);
        addLast(node);
        this.size++;
    }

    protected int top_() {
        return this.head.data;
    }

    public int top() throws Exception {
        if (this.size == 0) {
            QueueEmptyException();
            return -1;
        }

        return top_();
    }

    public node pop_() {
        node temp = head;
        head = head.next;
        int rv = temp.data;
        this.size--;

        return temp;
    }

    public node pop() throws Exception {
        if (this.size == 0) {
            QueueEmptyException();
        }

        return pop_();
    }
}