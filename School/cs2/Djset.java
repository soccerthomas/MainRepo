package School.cs2;
import java.io.*;
import java.util.*;
public class Djset {
    static class DisjointSet {
        int[] parent, rank, size;
        long connectivity;

        public DisjointSet(int n) {
            parent = new int[n];
            rank = new int[n];
            size = new int[n];
            connectivity = 0;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
                size[i] = 1;
                connectivity += 1; 
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX != rootY) {
                connectivity -= (long) size[rootX] * size[rootX];
                connectivity -= (long) size[rootY] * size[rootY];

                if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                    size[rootX] += size[rootY];
                    connectivity += (long) size[rootX] * size[rootX];
                } else if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                    size[rootY] += size[rootX];
                    connectivity += (long) size[rootY] * size[rootY];
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                    size[rootX] += size[rootY];
                    connectivity += (long) size[rootX] * size[rootX];
                }
            }
        }

        public long getConnectivity() {
            return connectivity;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);
        int d = Integer.parseInt(firstLine[2]);
        int[][] edges = new int[m][2];
        for (int i = 0; i < m; i++) {
            String[] edgeLine = br.readLine().split(" ");
            edges[i][0] = Integer.parseInt(edgeLine[0]) - 1;
            edges[i][1] = Integer.parseInt(edgeLine[1]) - 1;
        }
        int[] destroyed = new int[d];
        for (int i = 0; i < d; i++) {
            destroyed[i] = Integer.parseInt(br.readLine()) - 1;
        }

        boolean[] isDestroyed = new boolean[m];
        for (int i = 0; i < d; i++) {
            isDestroyed[destroyed[i]] = true;
        }

        DisjointSet ds = new DisjointSet(n);
        for (int i = 0; i < m; i++) {
            if (!isDestroyed[i]) {
                ds.union(edges[i][0], edges[i][1]);
            }
        }

        List<Long> results = new ArrayList<>();
        results.add(ds.getConnectivity());

        for (int i = d - 1; i >= 0; i--) {
            int u = edges[destroyed[i]][0];
            int v = edges[destroyed[i]][1];

            ds.union(u, v);
            results.add(ds.getConnectivity());
        }

        Collections.reverse(results);
        for (long result : results) {
            System.out.println(result);
        }
    }
}
