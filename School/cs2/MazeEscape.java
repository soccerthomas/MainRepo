package School.cs2;
import java.util.*;
/* COP 3503C Assignment 4
 * This program is written by: Thomas Lavadinho
 */

public class MazeEscape{
     public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        scanner.nextLine();
        char[][] maze = new char[a][b];
        int startX = -1, startY = -1, endX = -1, endY = -1;
        Map<Character, List<int[]>> teleportMap = new HashMap<>();

        for (int i = 0; i < a; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < b; j++) {
                maze[i][j] = line.charAt(j);
                if (maze[i][j] == '*') {
                    startX = i;
                    startY = j;
                } else if (maze[i][j] == '$') {
                    endX = i;
                    endY = j;
                } else if (Character.isUpperCase(maze[i][j])) {
                    teleportMap.putIfAbsent(maze[i][j], new ArrayList<>());
                    teleportMap.get(maze[i][j]).add(new int[]{i, j});
                }
            }
        }
        scanner.close();
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        boolean[][] visited = new boolean[a][b];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY, 0});
        visited[startX][startY] = true;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0], y = current[1], dist = current[2];

            if (x == endX && y == endY) {
                System.out.println(dist);
                return;
            }
             for (int[] dir : directions) {
                int newX = x + dir[0], newY = y + dir[1];
                if (newX >= 0 && newX < a && newY >= 0 && newY < b && !visited[newX][newY] && maze[newX][newY] != '!') {
                    visited[newX][newY] = true;
                    queue.add(new int[]{newX, newY, dist + 1});
                }
            }

            if (Character.isUpperCase(maze[x][y])) {
                char teleportChar = maze[x][y];
                for (int[] pos : teleportMap.get(teleportChar)) {
                    if (!visited[pos[0]][pos[1]]) {
                        visited[pos[0]][pos[1]] = true;
                        queue.add(new int[]{pos[0], pos[1], dist + 1});
                    }
                }
                teleportMap.get(teleportChar).clear();
            }
        }
         System.out.println("Stuck, we need help!");
    }
}
