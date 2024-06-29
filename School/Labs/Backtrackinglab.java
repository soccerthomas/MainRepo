package School.Labs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Backtrackinglab {
    private int n;
    private int magicConstant;
    private List<int[][]> magicSquares;

    public Backtrackinglab(int n) {
        this.n = n;
        this.magicConstant = n * (n * n + 1) / 2;
        this.magicSquares = new ArrayList<>();
    }

    public List<int[][]> generateMagicSquares() {
        int[][] square = new int[n][n];
        boolean[] used = new boolean[n * n + 1];
        backtrack(square, used, 0, 0);
        return magicSquares;
    }

    private void backtrack(int[][] square, boolean[] used, int row, int col) {
        if (row == n) {
            if (isValid(square)) {
                magicSquares.add(copySquare(square));
            }
            return;
        }

        for (int num = 1; num <= n * n; num++) {
            if (!used[num]) {
                square[row][col] = num;
                used[num] = true;

                if (col == n - 1) {
                    backtrack(square, used, row + 1, 0);
                } else {
                    backtrack(square, used, row, col + 1);
                }

                used[num] = false;
                square[row][col] = 0;
            }
        }
    }

    private boolean isValid(int[][] square) {
        for (int row = 0; row < n; row++) {
            int rowSum = 0;
            for (int col = 0; col < n; col++) {
                rowSum += square[row][col];
            }
            if (rowSum != magicConstant) {
                return false;
            }
        }

        for (int col = 0; col < n; col++) {
            int colSum = 0;
            for (int row = 0; row < n; row++) {
                colSum += square[row][col];
            }
            if (colSum != magicConstant) {
                return false;
            }
        }

        int diagonalSum1 = 0;
        int diagonalSum2 = 0;
        for (int i = 0; i < n; i++) {
            diagonalSum1 += square[i][i];
            diagonalSum2 += square[i][n - i - 1];
        }
        return diagonalSum1 == magicConstant && diagonalSum2 == magicConstant;
    }

    private int[][] copySquare(int[][] square) {
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(square[i], 0, copy[i], 0, n);
        }
        return copy;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the order of the magic square (n > 2): ");
        int n = scanner.nextInt();
        System.out.println("N = " + n);
        Backtrackinglab magicSquare = new Backtrackinglab(n);
        List<int[][]> results = magicSquare.generateMagicSquares();
        for (int[][] square : results) {
            System.out.println("Done!!");
            for (int[] row : square) {
                System.out.print("[");
                for (int j = 0; j < row.length; j++) {
                    System.out.print(row[j]);
                    if (j < row.length - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println("]");

            }
        }scanner.close();
    }
}
