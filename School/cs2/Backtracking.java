package School.cs2;
import java.util.Scanner;
public class Backtracking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        int numWords = scanner.nextInt();
        scanner.nextLine();
        
        char[][] matrix = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            String row = scanner.nextLine().replace(" ", "");
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = row.charAt(j);
            }
        }
        
        //Reads words to search for
        String[] words = new String[numWords];
        for (int i = 0; i < numWords; i++) {
            words[i] = scanner.nextLine();
        }
        
        //Checks for every word
        for (String word : words) {
            System.out.println("Looking for " + word);
            boolean found = false;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    boolean[][] visited = new boolean[rows][cols];
                    if (matrix[i][j] == word.charAt(0) && backtrack(matrix, word, 0, i, j, visited)) {
                        printSolution(matrix, visited);
                        found = true;
                        break;
                    }
                }
                if (found) break;
            }
            if (!found) {
                System.out.println(word + " not found!");
                System.out.println("");
            }
        }
        scanner.close();
    }
    
    private static boolean backtrack(char[][] matrix, String word, int index, int x, int y, boolean[][] visited) {
        if (index == word.length()) {
            return true;
        }
        if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length || visited[x][y] || matrix[x][y] != word.charAt(index)) {
            return false;
        }

        visited[x][y] = true;
        
        //Possible Directions with weird codegrade order
        int[] rowOffsets = {0, 0, -1, 1, 1, 1, -1, -1};
        int[] colOffsets = {-1, 1, 0, 0, -1, 1, -1, 1};

        for (int d = 0; d < rowOffsets.length; d++) {
            if (backtrack(matrix, word, index + 1, x + rowOffsets[d], y + colOffsets[d], visited)) {
                return true;
            }
        }

        visited[x][y] = false; // Unmarks the current cell for other paths
        return false;
    }

    private static void printSolution(char[][] matrix, boolean[][] visited) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.print("[");
            for (int j = 0; j < matrix[0].length; j++) {
                if (visited[i][j]) {
                    System.out.print(matrix[i][j]);
                } else {
                    System.out.print(" ");
                }
                if (j < matrix[0].length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
        System.out.println("");
    }
}
