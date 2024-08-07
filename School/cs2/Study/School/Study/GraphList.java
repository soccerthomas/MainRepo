package School.Study;
//Edge List
class GraphList{
    int src,dest,weight;
    public Edge(int src,int dest,int weight){
        this.src = src;
        this.weight = weight;
        this.dest = dest;
    }
    class GraphEdge{
        List<Edge> Edges = new ArrayList<>();
        public void addEdge(int src,int dest,int weight){
            edges.add(src,dest,weight);
        }
    }

}
//Adjacency List
class GraphAdjList{
    List<List<Edge>> adjList;
    public GraphAdjList(int vertices){
        adjList = new ArrayList<>(vertices);
        for(int i =0 ;i<vertices;i++){
            adjList.add(new LinkedList<>());
        }
    }
    public void addEdge(int src,int dest,int weight){
        adjList.add(new LinkedList<>());
    }
}
//Adjacency Matrix
class GraphAdjMatrix{
    int[][] adjMatrix;
    public GraphAdjMatrix(int vertices){
        adjMatrix = new int[vertices][vertices];
    }
    public void addEdge(int src,int dest, int weight){
        adjMatrix[src][dest] = weight;
        adjMatrix[dest][src] = weight;//Undirected Graph
    }
}