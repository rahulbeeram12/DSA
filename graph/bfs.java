import java.util.ArrayDeque;

public class bfs{
    
    public static class Edge{
        int v;
        int w;

        public Edge(int v,int w){
            this.v = v;
            this.w = w;
        }
    }

    public static void BFS(int src){
        Queue<Integer> que = new ArrayDeque<>();
        que.add(src);

        int level = 0;
        int dest = 6;
        boolean isCycle = false;

        while(que.size() != 0){
            int size = que.size();
            for(int i = 0 ; i < size; i++){
                int rv = que.remove();
                
            }
        }
    }

    public static void main(String args[]){

    }
}