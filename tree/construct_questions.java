public class construct_questions {
    public static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        public TreeNode(int data) {
            this.data = data;
        }
    }

    public static int findIn(int inorder[],int ele){
        for(int i = 0; i < inorder.length; i++){
            if(inorder[i] == ele) return i;
        }
        return -1;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder,int psi,int pei,int isi,int iei) {
        if(psi > pei) return null;

        TreeNode node = new TreeNode(preorder[psi]);
        int idx = findIn(inorder,preorder[psi]);

        int count = idx - isi;

        node.left = buildTree(preorder,inorder,psi + 1,psi + count,isi,idx - 1);
        node.right = buildTree(preorder,inorder,psi + count + 1,pei,idx + 1,iei);

        return node;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        return buildTree(preorder,inorder,0,n - 1,0,n - 1);
    }

    //========================= inorder and postorder =====================
    public static int find(int inorder[], int ele) {
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == ele)
                return i;
        }
        return -1;
    }

    public TreeNode buildTree_(int[] inorder, int[] postorder, int isi, int iei, int psi, int pei) {
        if (psi > pei)
            return null;

        TreeNode node = new TreeNode(postorder[pei]);
        int idx = find(inorder, postorder[pei]);

        int count = idx - isi;

        node.left = buildTree(inorder, postorder, isi,idx - 1,psi , psi + count + 1);
        node.right = buildTree(inorder, postorder, idx + 1,iei,psi + count, pei);

        return node;
    }

    public TreeNode buildTree_(int[] inorder, int[] postorder) {
        int n = inorder.length;
        return buildTree(inorder, postorder, 0, n - 1, 0, n - 1);
    }

    // ===================  buildTree from pre and post ==============================
    class Solution {
        public TreeNode buildTree__(int[] pre, int[] post, int psi, int pei, int ppsi, int ppei) {
            if (psi > pei)
                return null;
            if (psi == pei)
                return new TreeNode(pre[psi]);

            TreeNode node = new TreeNode(pre[psi]);

            int idx = ppsi;
            while (post[idx] != pre[psi + 1])
                idx++;

            int tel = idx - ppsi + 1;

            node.left = buildTree__(pre, post, psi + 1, psi + tel, ppsi, idx);
            node.right = buildTree__(pre, post, psi + tel + 1, pei, idx + 1, ppei - 1);

            return node;
        }

        public TreeNode constructFromPrePost(int[] pre, int[] post) {
            if (pre.length == 0 && post.length == 0)
                return null;
            int n = pre.length;
            return buildTree__(pre, post, 0, n - 1, 0, n - 1);
        }
    }

    public static void main(String args[]){

    }
}
