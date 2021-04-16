import java.util.*;

public class question{
    public static class TreeNode{
        int data;
        TreeNode left;
        TreeNode right;

        public TreeNode(int data){
            this.data = data;
        }
    }

    public static int size(TreeNode root){
        if(root == null) return 0;
        
        return size(root.left) + size(root.right) + 1;
    }

    public static int max(TreeNode root){
        if(root == null) return -(int)1e9;
        return Math.max(Math.max(max(root.left),max(root.right)),root.data);
    }

    public static int height(TreeNode root){
        if(root == null) return -1; // in terms of edges
        return Math.max(height(root.left),height(root.right)) + 1;
    }

    public static int height1(TreeNode root){
        if(root == null) return 0; // 0 in terms of edges
        return Math.max(height1(root.left),height1(root.right)) + 1;
    }

    public static boolean find(TreeNode root,int data){
        if(root == null) return false;
        if(root.data == data) return true;

        return find(root.left,data) || find(root.right,data);
    }

    public static boolean nodeToRootPath(TreeNode root,TreeNode node,ArrayList<TreeNode> list){
        if(root == null) return false;
        if(root == node){
            list.add(root);
            return true;
        }

        boolean res = nodeToRootPath(root.left,node,list) || nodeToRootPath(root.right,node,list);
        if(res) list.add(node);
        
        return res;
    }

    public static void main(String args[]){
        ArrayList<TreeNode> list = new ArrayList<>();
        nodeToRootPath(root, node, list);
    }
}