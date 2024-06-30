package School.Study;
import java.io.*;
import java.util.*;

public class dijkstra {
	
	final public static int MAX = 1000000000;
	final public static int NOEDGE = 0;
	
	// Short driver program to test the Dijkstra's method.	
	public static void main(String[] args) throws Exception {
		
		Scanner stdin = new Scanner(System.in);
		int nC = stdin.nextInt();
		
		// Process cases.
		for (int loop=0; loop<nC; loop++) {
		
			// Read in this adjacency matrix.
			int n = stdin.nextInt();
			int[][] adj = new int[n][n];
			for (int i = 0; i<n*n; i++)
				adj[i/n][i%n] = stdin.nextInt();
		
			// Run Dijkstra's and print out shortest distances to each vertex.
			int[] answers = dijkstra(adj, 0);
			for (int i=0; i<n; i++)
				System.out.print(answers[i]+" ");
			System.out.println();		
		}
	}
	
	// Returns the shortest paths from vertex source to the rest of the 
	// vertices in the graph via Dijkstra's algorithm. It's required that
	// all edge weights in adj be non-negative and that all edges be 
	// assigned weights.
	public static int[] dijkstra(int[][] adj, int source) {
		
		// This array will store our estimates of shortest distances.
		int[] estimates = new int[adj.length];
		
		// Set these to a very large number, larger than any path in our
		// graph could possibly be.
		for (int i=0; i<estimates.length; i++)
			estimates[i] = MAX;
			
		// We are already at our source vertex.
		estimates[source] = 0;
		
		// This will keep track of which vertices we already have shortest
		// distances to.
		boolean[] chosen = new boolean[estimates.length];
		for (int i=0; i<chosen.length; i++) chosen[i] = false;
		
		// We need to loop this many times to finish the algorithm.
		for (int i=0; i<estimates.length-1; i++) {
			
			// Pick vertex.
			int vertex = 0;
			int bestseen = MAX;
			
			// Find the best vertex that has NOT been added to our set S yet.
			for (int j=0; j<estimates.length; j++) {
				if (chosen[j] == false && estimates[j] < bestseen) {
				
					bestseen = estimates[j];
					vertex = j;
				}
			}
			
			// Choose this vertex to be added.
			chosen[vertex] = true;
			
			// Update all estimates based on this particular vertex only.
			for (int j = 0; j<estimates.length; j++) {
				if (adj[vertex][j] == NOEDGE) continue;
				if (estimates[vertex]+adj[vertex][j] < estimates[j])
				    estimates[j] = estimates[vertex] + adj[vertex][j];	
			}
					
		}
		return estimates;
	}
}
