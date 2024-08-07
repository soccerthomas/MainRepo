package School.Study;

import java.util.Scanner;

public class MaxProblemsSolved {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Read the number of students in each row
        int n = scanner.nextInt();
        
        // Read the number of problems solved by each student in the first row
        int[] row1 = new int[n];
        for (int i = 0; i < n; i++) {
            row1[i] = scanner.nextInt();
        }
        
        // Read the number of problems solved by each student in the second row
        int[] row2 = new int[n];
        for (int i = 0; i < n; i++) {
            row2[i] = scanner.nextInt();
        }
        
        // If there is only one student in each row, return the maximum of the two
        if (n == 1) {
            System.out.println(Math.max(row1[0], row2[0]));
            return;
        }
        
        // Create the dp table
        int[][] dp = new int[2][n];
        
        // Initialize the first student's score
        dp[0][0] = row1[0];
        dp[1][0] = row2[0];
        
        // Fill the dp table
        for (int i = 1; i < n; i++) {
            dp[0][i] = Math.max(dp[0][i - 1], dp[1][i - 1] + row1[i]);
            dp[1][i] = Math.max(dp[1][i - 1], dp[0][i - 1] + row2[i]);
        }
        
        // The result is the maximum value at the last student in either row
        System.out.println(Math.max(dp[0][n - 1], dp[1][n - 1]));
    }
}

