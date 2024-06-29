package School.cs2;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
//SLMP
class IntPair {
    int first;
    int second;

    public IntPair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}

public class SLMP {
    public static IntPair getCandidatePair(int[] points, int T, boolean isSorted) {
        if (isSorted) {
            // Two-pointer strategy
            int left = 0;
            int right = points.length - 1;
            while (left < right) {
                int currentSum = points[left] + points[right];
                if (currentSum == T) {
                    return new IntPair(points[left], points[right]);
                } else if (currentSum < T) {
                    left++;
                } else {
                    right--;
                }
            }
        } else {
            // Hashset for unsorted array
            Set<Integer> seen = new HashSet<>();
            for (int point : points) {
                int complement = T - point;
                if (seen.contains(complement)) {
                    return new IntPair(Math.min(point, complement), Math.max(point, complement));
                }
                seen.add(point);
            }
        }
        // If no pair is found
        return new IntPair(0, 0);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        scanner.nextLine(); 
        for (int i = 1; i <= k; i++) {
            String[] firstLine = scanner.nextLine().split(" ");
            boolean isSorted = firstLine[0].equals("1");
            int n = Integer.parseInt(firstLine[1]);
            int[] points = new int[n];
            for (int j = 0; j < n; j++) {
                points[j] = Integer.parseInt(firstLine[j + 2]);
            }
            int T = Integer.parseInt(scanner.nextLine());

            IntPair result = getCandidatePair(points, T, isSorted);
            if (result.first == 0 && result.second == 0) {
                System.out.println("Test case #" + i + ": No way you can spend exactly " + T + " points.");
            } else {
                System.out.println("Test case #" + i + ": Spend " + T + " points by playing the games with " + result.first + " points and " + result.second + " points.");
            }
        }

        scanner.close();
    }
}



