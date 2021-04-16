import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.PriorityQueue;

class traversals{
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val){
            this.val = val;
        }
    }

    public static void levelOrder(TreeNode root){
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);

        while(que.size() > 0){
            TreeNode node = que.removeFirst();
            System.out.println(node.val);

            if(node.left != null) que.addLast(node.left);
            if(node.right != null) que.addLast(node.right);
        }
    }

    public static void levelOrderLineWise(TreeNode root){
        LinkedList<TreeNode> mq = new LinkedList<>();
        LinkedList<TreeNode> cq = new LinkedList<>();

        int level = 0;
        System.out.print("level : " + level);
        mq.addLast(root);
        while(mq.size() > 0){
            TreeNode node = mq.removeFirst();
            System.out.print(node.val + " ");

            if(node.left != null) cq.addLast(node.left);
            if(node.right != null) cq.addLast(node.right);

            if(mq.size() == 0){
                System.out.println();
                System.out.print("level : " + (++level));
                
                LinkedList<TreeNode> temp = mq;
                mq = cq;
                cq = temp;
            }
        }
    }

    public static void levelOrder_graphmethod(TreeNode root){
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);

        while(que.size() > 0){
            int size = que.size();
            for(int i = 0; i < size; i++){
                TreeNode node = que.removeFirst();
                System.out.print(node.val + " ");

                if(node.left != null) que.addLast(node.left);
                if(node.right != null) que.addLast(node.right);
            }
            System.out.println();
        }
    }

    public static void levelOrder_delimeter(TreeNode root){
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);
        que.addLast(null);

        while(que.size() != 1){
            TreeNode node = que.removeFirst();
            if(node == null) {
                System.out.println();
                que.addLast(node);
            }
            else 
                System.out.print(node.val + " ");

            if(node.left != null) que.addLast(node.left);
            if(node.right != null) que.addLast(node.right);
        }
    }

    public static class pair {
        TreeNode node = null;
        int hl = 0;

        pair(TreeNode node,int hl){
            this.node = node;
            this.hl = hl;
        }
    }

    public static List<List<Integer>> verticalOrder(TreeNode root){
        LinkedList<pair> que = new LinkedList<>();
        que.addLast(new pair(root,0));
        HashMap<Integer,List<Integer>> map = new HashMap<>();

        int min = 0;
        int max = 0;
        while(que.size() > 0){
            int size = que.size();
            for(int i = 0; i < size; i++){
                pair rp = que.removeFirst();

                map.putIfAbsent(rp.hl, new ArrayList<>());
                map.get(rp.hl).add(rp.node.val);

                max = Math.max(max,rp.hl);
                min = Math.min(min,rp.hl);

                if(rp.node.left != null) que.addLast(new pair(rp.node.left,rp.hl - 1));
                if(rp.node.right != null) que.addLast(new pair(rp.node.right,rp.hl + 1));
            }
        }

        List<List<Integer>> res = new ArrayList<>();
        while(min <=  max){
            res.add(map.get(min));
            min++;
        }
        return res;
    }    

    //finding width
    public static void width(TreeNode root,int hl,int arr[]){
        //{minHL,maxHL};
        if(root == null) return;
        arr[0] = Math.min(arr[0],hl);
        arr[1] = Math.max(arr[1],hl);

        width(root.left,hl - 1,arr);
        width(root.right,hl + 1,arr);
    }

    //vertical order approach 2
    public static List<List<Integer>> verticalOrder_1(TreeNode root){
        LinkedList<pair> que = new LinkedList<>();
        
        int res[] = new int[2];
        width(root,0,res);
        int size = res[1] - res[0] + 1;
        List<List<Integer>> ans = new ArrayList<>();
        for(int i = 0; i < size; i++) ans.add(new ArrayList<>());

        que.addLast(new pair(root, -res[0]));
        
        while (que.size() > 0) {
            int sz = que.size();
            for (int i = 0; i < sz; i++) {
                pair rp = que.removeFirst();

                ans.get(rp.hl).add(rp.node.val);

                if (rp.node.left != null)
                    que.addLast(new pair(rp.node.left, rp.hl - 1));
                if (rp.node.right != null)
                    que.addLast(new pair(rp.node.right, rp.hl + 1));
            }
        }

        return ans;
    }

    public static int[] verticalSum(TreeNode root){
        LinkedList<pair> que = new LinkedList<>();

        int res[] = new int[2];
        width(root, 0, res);
        int size = res[1] - res[0] + 1;
        int arr[] = new int[size];

        que.addLast(new pair(root, -res[0]));

        while (que.size() > 0) {
            int sz = que.size();
            for (int i = 0; i < sz; i++) {
                pair rp = que.removeFirst();

                arr[rp.hl] += rp.node.val;

                if (rp.node.left != null)
                    que.addLast(new pair(rp.node.left, rp.hl - 1));
                if (rp.node.right != null)
                    que.addLast(new pair(rp.node.right, rp.hl + 1));
            }
        }

        return arr;
    }

    public static int[] bottomView(TreeNode root) {
        LinkedList<pair> que = new LinkedList<>();

        int res[] = new int[2];
        width(root, 0, res);
        int size = res[1] - res[0] + 1;
        int arr[] = new int[size];

        que.addLast(new pair(root, -res[0]));

        while (que.size() > 0) {
            int sz = que.size();
            for (int i = 0; i < sz; i++) {
                pair rp = que.removeFirst();

                arr[rp.hl] = rp.node.val;

                if (rp.node.left != null)
                    que.addLast(new pair(rp.node.left, rp.hl - 1));
                if (rp.node.right != null)
                    que.addLast(new pair(rp.node.right, rp.hl + 1));
            }
        }

        return arr;
    }

    public static int[] topView(TreeNode root) {
        LinkedList<pair> que = new LinkedList<>();

        int res[] = new int[2];
        width(root, 0, res);
        int size = res[1] - res[0] + 1;
        int arr[] = new int[size];
        boolean vis[] = new boolean[size];

        que.addLast(new pair(root, -res[0]));

        while (que.size() > 0) {
            int sz = que.size();
            for (int i = 0; i < sz; i++) {
                pair rp = que.removeFirst();

                if(!vis[rp.hl]) {
                    vis[rp.hl] = true;
                    arr[rp.hl] = rp.node.val;
                }

                if (rp.node.left != null)
                    que.addLast(new pair(rp.node.left, rp.hl - 1));
                if (rp.node.right != null)
                    que.addLast(new pair(rp.node.right, rp.hl + 1));
            }
        }

        return arr;
    }

    public static void widthForDiagonal(TreeNode root,int hl,int arr[]){
        if(root == null) return;

        arr[0] = Math.min(arr[0],hl);
        
        widthForDiagonal(root.left,hl-1,arr);
        widthForDiagonal(root.right, hl, arr);
        return;
    }

    public static List<List<Integer>> diagonalTraversal(TreeNode root){
        LinkedList<pair> que = new LinkedList<>();
        
        int arr[] = new int[1];
        widthForDiagonal(root, 0, arr);
        int size = -arr[0] + 1;
        List<List<Integer>> ans = new ArrayList<>();
        for(int i = 0;i < size; i++) ans.add(new ArrayList<>());

        que.addLast(new pair(root,0));
        while(que.size() > 0){
            int sz = que.size();
            for(int i = 0; i < sz; i++){
                pair rp = que.removeFirst();

                ans.get(rp.hl).add(rp.node.val);

                if(rp.node.left != null) que.addLast(new pair(rp.node.left,rp.hl - 1));
                if(rp.node.right != null) que.addLast(new pair(rp.node.right,rp.hl));
            }
        }
        return ans;
    }

    //another approach
    public static List<List<Integer>> diagonalTraversal_2(TreeNode root) {
        LinkedList<pair> que = new LinkedList<>();
        que.addLast(new pair(root, 0));
        HashMap<Integer, List<Integer>> map = new HashMap<>();

        int min = 0;
        int max = 0;
        while (que.size() > 0) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                pair rp = que.removeFirst();

                map.putIfAbsent(rp.hl, new ArrayList<>());
                map.get(rp.hl).add(rp.node.val);

                max = Math.max(max, rp.hl);
                min = Math.min(min, rp.hl);

                if (rp.node.left != null)
                    que.addLast(new pair(rp.node.left, rp.hl - 1));
                if (rp.node.right != null)
                    que.addLast(new pair(rp.node.right, rp.hl + 1));
            }
        }

        List<List<Integer>> res = new ArrayList<>();
        while (min <= max) {
            res.add(map.get(min));
            min++;
        }
        return res;
    }

    //leetcode 987
    public static class verticalPair{
        TreeNode node;
        int hl;

        public verticalPair(TreeNode node,int hl){
            this.node = node;
            this.hl = hl;
        }
    }
    public List<List<Integer>> verticalTraversal_(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        PriorityQueue<verticalPair> que = new PriorityQueue<>((a,b)->{
            return a.node.val - b.node.val;
        });
        PriorityQueue<verticalPair> childQue = new PriorityQueue<>((a,b)->{
            return a.node.val - b.node.val;
        });
        
        int arr[] = new int[2];
        width(root,0,arr);
        int size = arr[1] - arr[0] + 1;
        for(int i = 0; i < size; i++) res.add(new ArrayList<>());

        que.add(new verticalPair(root,-arr[0]));
        while(que.size() > 0){
            verticalPair rp = que.remove();

            res.get(rp.hl).add(rp.node.val);

            if(rp.node.left != null) childQue.add(new verticalPair(rp.node.left,rp.hl - 1));
            if(rp.node.right != null) childQue.add(new verticalPair(rp.node.right,rp.hl + 1));

            if(que.size() == 0){
                PriorityQueue<verticalPair> temp = que;
                que = childQue;
                childQue = temp;
            }
        }

        return res;
    }

    //another approach
    public static class verticalPair_2{
        TreeNode node = null;
        int x = 0;
        int y = 0;

        public verticalPair_2(TreeNode node,int x,int y){
            this.node = node;
            this.x = x;
            this.y = y;
        }
    }

    public static List<List<Integer>> verticalorderTraversal_(TreeNode root){
        PriorityQueue<verticalPair_2> que = new PriorityQueue<>((a,b)->{
            if(a.y != b.y){
                return a.y - b.y;
            }else {
                return a.node.val - b.node.val; 
            }
        });

        int arr[] = new int[2];
        width(root,0,arr);
        int size = arr[1] - arr[0] + 1;
        List<List<Integer>> ans = new ArrayList<>();
        for(int i = 0; i < size; i++) ans.add(new ArrayList<>());

        que.add(new verticalPair_2(root, -arr[0], 0));

        while(que.size() > 0){
            verticalPair_2 rp = que.remove();

            ans.get(rp.x).add(rp.node.val);

            if(rp.node.left != null) que.add(new verticalPair_2(rp.node.left, rp.x - 1, rp.y + 1));
            if(rp.node.right != null) que.add(new verticalPair_2(rp.node.right, rp.x + 1, rp.y + 1));
        }

        return ans;
    }

    public static void main(String args[]){

    }
}