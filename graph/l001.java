import java.util.ArrayList;

public class l001{
    public static class Edge{
        int v;
        int w;

        public Edge(int v,int w){
            this.v = v;
            this.w = w;
        }
    }

    public static int N = 7;

    @SuppressWarnings("unchecked");
    public static ArrayList<Edge>[] graph = new ArrayList[N];

    public static void addEdge(int u,int v,int w){
        graph[u].add(new Edge(v,w));
        graph[v].add(new Edge(u,w));
    }

    public static void display(){
        for(int u = 0 ;u < N; u++){
            System.out.print(u + " -> ");
            for(int v = 0 ; v < graph[u].size(); v++){
                System.out.print(graph[u].get(v).v + ", " + graph[u].get(v).w);
            }
            System.out.print(".");
            System.out.println();
        }
    }

    public static int findEdge(int u,int v){
        int idx = -1;
        for(int i = 0 ;i < graph[u].size(); i++){
            if(graph[u].get(i).v == v){
                idx = i;
                break;
            }
        }
    }

    public static void removeEdge(int u,int v){
        int idx1 = findEdge(u,v);
        int idx2 = findEdge(v,u);

        graph[u].remove(idx1);
        graph[v].remove(idx2);
    }

    public static void removeVertix(int u){
        for(int i = graph[u].size() - 1; i >= 0; i--){
            int v = graph[u].get(i).v;
            removeEdge(u,v);
        }
    }

    public static boolean hasPath(int u,int v,boolean vis[]){
        if(u == v) return true;

        boolean res = true;
        vis[u] = true;
        for(Edge e : graph[u]){
            if(!vis[e.v]){
                res = res || hasPath(e.v,v,vis);
            }
        }

        return false;
    }

    public static int printAllPath(int u,int v){
        
    }

    public static int heavyPath(int u,int v){

    }

    public static int lightPath(int u,int v){

    }

    public static int hamiltonianPath(int src){
         
    }

    public static void main(String args[]){
        
        for(int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();

        addEdge(0,1,10);
        addEdge(0,3,10);
        addEdge(1,2,10);
        addEdge(2,3,40);
        addEdge(3,4,2);
        addEdge(4,5,2);
        addEdge(4,6,8);
        addEdge(5,6,8);
    }
}