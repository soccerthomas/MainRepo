package School.cs2;

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] line = scan.nextLine().split(" ");
        int cities = Integer.parseInt(line[0]);
        int roads = Integer.parseInt(line[1]);
        int capital = Integer.parseInt(line[2]);

        Djikstra obj = new Djikstra(cities, capital);
        for (int i = 0; i < roads; i++) {
            String[] road = scan.nextLine().split(" ");
            int src = Integer.parseInt(road[0]);
            int dst = Integer.parseInt(road[1]);
            int len = Integer.parseInt(road[2]);

            obj.add_edge(src, dst, len);
            obj.add_edge(dst, src, len);
        }
        int L = Integer.parseInt(scan.nextLine());

        obj.dijkstra(capital - 1);

        System.out.println("In City: " + obj.treasure_city(L));
        System.out.println("On the road: " + obj.treasure_road(L));
    }
}

class Djikstra {
    private int n;
    private int capital;
    private int[] dist;
    private int[][] graph;
    private int[][] dup;
    private Boolean[] fixed;

    public Djikstra(int n, int cap) {
        this.n = n;
        this.capital = cap - 1;
        this.dist = new int[n];
        this.dup = new int[n][n];
        this.fixed = new Boolean[n];
        this.graph = new int[n][n];
    }

    public void add_edge(int src, int dest, int len) {
        this.graph[src - 1][dest - 1] = len;
    }

    void dijkstra(int src) {
        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
            fixed[i] = false;
        }
        dist[src] = 0;

        for (int count = 0; count < n - 1; count++) {
            int u = minimum_distance(dist);
            fixed[u] = true;
            for (int v = 0; v < n; v++) {
                if (!fixed[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && (dist[u] + graph[u][v] < dist[v])) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }
    }

    int minimum_distance(int dist[]) {
        int min = Integer.MAX_VALUE;
        int min_index = -1;

        for (int v = 0; v < n; v++) {
            if (fixed[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        }
        return min_index;
    }

    int treasure_city(int L) {
        int count = 0;
        for (int i = 0; i < this.n; i++) {
            if (this.dist[i] == L) {
                count++;
            }
        }
        return count;
    }

    int treasure_road(int L) {
        int count = 0;
        for (int i = 0; i < this.n; i++) {
            if (i != this.capital) {
                int d = L - dist[i];
                for (int j = 0; j < this.n; j++) {
                    if (j != this.capital && j != i && this.graph[i][j] > d && d != 0) {
                        if ((this.graph[i][j] - d) == (this.graph[i][j]) / 2) {
                            if (dup[i][j] == 0) {
                                count++;
                                this.dup[i][j] = 1;
                                this.dup[j][i] = 1;
                            }
                        } else {
                            count++;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < this.n; i++) {
            if (i != this.capital) {
                if (this.graph[this.capital][i] > L) {
                    count++;
                }
            }
        }
        return count;
    }
}
