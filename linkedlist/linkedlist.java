import java.util.*;

public class linkedlist{
    private class Node{
        private int data = 0;
        private Node next = null;

        Node(int data){
            this.data = data;
        }
    }

    private Node head = null;
    private Node tail = null;
    private int numberOfNodes = 0;

    public int size(){
        return this.numberOfNodes;
    }

    public boolean isEmpty(){
        return this.size() == 0;
    }    
    public static void main(String args[]){
        
    }
}