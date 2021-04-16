public class avl {
    public static class Node{
        int data = 0;
        Node left = null;
        Node right = null;

        int height = -1;
        int bal = 0;

        public Node(int data){
            this.data = data;
        }
    }

    public static void updateHeightAndBalance(Node node){
        //height
        int lh = -1;
        int rh = -1;

        if(node.left != null){
            lh = node.left.height;
        }

        if(node.right != null){
            rh = node.right.height;
        }

        node.height = Math.max(lh,rh) + 1;
        node.bal = lh - rh;
    }
    
    public static Node ll(Node A){
        Node B = A.left;
        Node bKaRight = B.right;

        B.right = A;
        A.left = bKaRight;

        updateHeightAndBalance(A);
        updateHeightAndBalance(B);

        return B;
    }

    public static Node rr(Node A) {
        Node B = A.right;
        Node bKaleft = B.left;

        B.left = A;
        A.right = bKaleft;

        updateHeightAndBalance(A);
        updateHeightAndBalance(B);

        return B;
    }

    public static Node rotateSubTree(Node root){
        updateHeightAndBalance(root);
        if(root.bal == 2){ // ll,lr
            if(root.left.bal == 1){ // ll
                return ll(root);
            }else{
                root.left = rr(root.left);
                return ll(root);
            }
        }else if(root.bal == -2){ // rr,rl
            if(root.right.bal == -1){ // rr
                return rr(root);
            }else{ //rl
                root.right = ll(root.right);
                return rr(root);
            }
        }

        return root;
    }

    public static Node addData(Node root,int data){
        if(root == null) return new Node(data);

        if(root.data > data) root.left = addData(root.left,data);
        else root.right = addData(root.right,data);

        return rotateSubTree(root);
    }

    public static int maxLeft(Node root){
        Node curr = root;
        while(curr.right != null){
            curr = curr.right; 
        }
        return curr.data;
    }

    public static Node removeData(Node root,int data){
        if(root == null) return null;

        if(root.data > data) root.left = removeData(root.left,data);
        else if(root.data < data) root.right = removeData(root.right,data);
        else{
            if(root.left == null || root.right == null){
                return root.left != null ? root.left : root.right;
            }

            int maxLeft = maxLeft(root.left);
            root.data = maxLeft;

            root.left = removeData(root.left,maxLeft);
        }
        return root;
    }



    public static void main(String args[]){

    }
}
