public class question_1 {
    public static class Node{
        int data;
        Node left = null;
        Node right = null;

        public Node(int data){
            this.data = data;
        }
    }

    public static class allPairSolution{
        Node prev = null;
        Node pred = null;
        Node succ = null;
    }

    public static void allSolution(Node root,int data,allPairSolution pair){
        if(root == null) return;

        allSolution(root.left, data, pair);
        if(root.data == data && pair.pred == null) pair.pred = pair.prev;
        if(pair.prev != null && pair.prev.data == data && pair.succ == null) pair.succ = root;

        pair.prev = root;
        allSolution(root.right, data, pair);
    }

    //in BST
    

    public static void main(String arhs[]){
        
    }
}
