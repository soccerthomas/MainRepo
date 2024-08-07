package School.Labs;
import java.util.*;
//Thomas Lavadinho Lab 3
class DisjointSet {
    private int[] parents;
    private int[] ranks;
    private int numTrees;

    public DisjointSet(int n) {
        parents = new int[n];
        ranks = new int[n];
        numTrees = 0;
        Arrays.fill(parents, -1);
    }

    public int find(int id) {
        if (parents[id] != id) {
            parents[id] = find(parents[id]);
        }
        return parents[id];
    }

    public boolean union(int id1, int id2) {
        int root1 = find(id1);
        int root2 = find(id2);

        if (root1 == root2) {
            return false;
        }

        if (ranks[root1] > ranks[root2]) {
            parents[root2] = root1;
        } else if (ranks[root1] < ranks[root2]) {
            parents[root1] = root2;
        } else {
            parents[root2] = root1;
            ranks[root1]++;
        }

        numTrees--;
        return true;
    }

    public void addTree(int id) {
        parents[id] = id;
        numTrees++;
    }

    public boolean isConnected() {
        return numTrees == 1;
    }
}

public class Djsetlab {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();

        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            int barn1 = scanner.nextInt() - 1;
            int barn2 = scanner.nextInt() - 1;
            edges.add(new int[]{barn1, barn2});
        }

        int[] orderOfClosures = new int[N];
        for (int i = 0; i < N; i++) {
            orderOfClosures[i] = scanner.nextInt() - 1;
        }

        DisjointSet disjointSet = new DisjointSet(N);
        boolean[] open = new boolean[N];
        boolean[] result = new boolean[N];
        for (int i = N - 1; i >= 0; i--) {
            int barn = orderOfClosures[i];
            open[barn] = true;
            disjointSet.addTree(barn);

            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                if (open[u] && open[v]) {
                    disjointSet.union(u, v);
                }
            }
        result[i] = disjointSet.isConnected();
        }

        for (boolean res : result) {
            System.out.println(res ? "YES" : "NO");
        }
    }
}

