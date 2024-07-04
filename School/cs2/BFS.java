package School.cs2;
import java.util.*;

public class BFS {

    // Graph class to represent a graph using adjacency list
    static class Graph {
        private int numVertices;
        private LinkedList<Integer> adjLists[];

        // Constructor
        Graph(int vertices) {
            numVertices = vertices;
            adjLists = new LinkedList[vertices];
            for (int i = 0; i < vertices; i++) {
                adjLists[i] = new LinkedList<>();
            }
        }

        // Add edge to the graph
        void addEdge(int src, int dest) {
            adjLists[src].add(dest);
        }

        // BFS algorithm
        void BFS(int startVertex) {
            boolean visited[] = new boolean[numVertices];
            LinkedList<Integer> queue = new LinkedList<>();

            visited[startVertex] = true;
            queue.add(startVertex);

            while (queue.size() != 0) {
                startVertex = queue.poll();
                System.out.print(startVertex + " ");

                Iterator<Integer> i = adjLists[startVertex].listIterator();
                while (i.hasNext()) {
                    int n = i.next();
                    if (!visited[n]) {
                        visited[n] = true;
                        queue.add(n);
                    }
                }
            }
        }
    }

    public static void main(String args[]) {
        Graph g = new Graph(6);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 4);
        g.addEdge(3, 5);
        g.addEdge(4, 5);

        System.out.println("Breadth First Traversal starting from vertex 0:");

        g.BFS(0);
    }
}
