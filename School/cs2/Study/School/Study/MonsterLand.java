package School.Study;

import java.util.*;


public class MonsterLand {

    class Edge {
        int target, weight;

        Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        new MonsterLand().solve();
    }

    public void solve() {
        Scanner sc = new Scanner(System.in);

        int C = sc.nextInt();
        int R = sc.nextInt();
        int S = sc.nextInt();
        List<Edge>[] graph = new ArrayList[C + 1];
        for (int i = 0; i <= C; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < R; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            graph[u].add(new Edge(v, w));
            graph[v].add(new Edge(u, w));
        }

        int L = sc.nextInt();

        int[] distances = dijkstra(C, graph, S);

        int treasuresInCities = 0;
        for (int i = 1; i <= C; i++) {
            if (distances[i] == L) {
                treasuresInCities++;
            }
        }

        int treasuresOnRoads = 0;
        for (int u = 1; u <= C; u++) {
            for (Edge edge : graph[u]) {
                int v = edge.target;
                int w = edge.weight;
                if (u < v) {  // Ensure each road is considered only once
                    int du = distances[u];
                    int dv = distances[v];
                    if (du < L && dv < L) {
                        if ((du < L && du + w >= L) || (dv < L && dv + w >= L)) {
                            treasuresOnRoads++;
                        }
                    }
                }
            }
        }

        System.out.println("In city: " + treasuresInCities);
        System.out.println("On the road: " + treasuresOnRoads);
    }

    private int[] dijkstra(int C, List<Edge>[] graph, int start) {
        int[] distances = new int[C + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int u = current.target;
            int currentDistance = current.weight;

            if (currentDistance > distances[u]) {
                continue;
            }

            for (Edge edge : graph[u]) {
                int v = edge.target;
                int newDist = currentDistance + edge.weight;
                if (newDist < distances[v]) {
                    distances[v] = newDist;
                    pq.add(new Edge(v, newDist));
                }
            }
        }

        return distances;
    }
}
