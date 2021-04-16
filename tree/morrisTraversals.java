import java.util.*;

public class morrisTraversals{
    public static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    public void inorderMorris(Node root){
        Node curr = root;

        while(curr != null){
            Node next = curr.left;

            if(next == null){
                System.out.print(curr.data + " ");
                curr = curr.right;
                break;
            }

            boolean flag = false;
            while(next.right != null){
                next = next.right;
                if(next.right == curr){
                    flag = true;
                    next.right = null;
                    break;
                }
            }

            if(!flag) {
                next.right = curr;
                curr = curr.left;
            }
            if(flag){
                System.out.print(curr.data + " ");
                curr = curr.right;
            }
        }
    }

    public void preorderMorris(Node root) {
        Node curr = root;

        while (curr != null) {
            Node next = curr.left;

            if (next == null) {
                System.out.print(curr.data + " ");
                curr = curr.right;
                break;
            }

            boolean flag = false;
            while (next.right != null) {
                next = next.right;
                if (next.right == curr) {
                    flag = true;
                    next.right = null;
                    break;
                }
            }

            if (!flag) {
                System.out.print(curr.data + " ");
                next.right = curr;
                curr = curr.left;
            }
            if (flag) {
                curr = curr.right;
            }
        }
    }
    public static void main(String rah[]){

    }
}