import java.util.Arrays;

class Edge{
    int src,dest,weight;
    public Edge(int src,int dest, int weight){
        this.src = src;
        this.weight = weight;
        this.dest = dest;
    }
}
public class BellmanFord{
    private int V,E;
    private Edge[] edges;
    
    public BellmanFord(int v, int e){
        V = v;
        E = e;
        edges = new Edge[E];
    }
    public void addEdge(int e, int src, int dest, int weight){
        edges[e] = new Edge(src,dest,weight);
    }
    public void bellmanFord(int src){
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        for(int i = 1;i < V;i++){
            for(int j = 0; j < E;j++){
                int u = edges[j].src;
                int v = edges[j].dest;
                int weight = edges[j].weight;
                if(dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]){
                    dist[v] = dist[u] + weight;
                }
            }
        }
        for(int j = 0; j < E;j++){
            int u = edges[j].src;
            int v = edges[j].dest;
            int weight = edges[j].weight;
            if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]){
                System.out.println("Graph contains negative weight cycles");
                return;
            }
        }
        printSolution(dist);
    }
}
private void printSolution(int[] dist){
    System.out.println("Vertex Distance from Source");
    for(int i = 0; i < V; i++){
        System.out.println(i + "\t\t" + dist[i]);
    }
}

    public static void main(String[] args){
        int V = 5;
        int E = 8;

        BellmanFord graph = new BellmanFord(V,E);
        
    }