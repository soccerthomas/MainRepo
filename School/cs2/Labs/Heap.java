package School.Labs;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Heap {
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private double median = 0;

    public void addNum(int num) {
        if(maxHeap.isEmpty() && minHeap.isEmpty()) {
            maxHeap.add(num);
            median = num;
            return;
        }

        if(maxHeap.size() > minHeap.size()) {
            if (num < median) {
                minHeap.add(maxHeap.poll());
                maxHeap.add(num);
            }else{
                minHeap.add(num);
            }
            median =(maxHeap.peek() + minHeap.peek())/2.0;
        }else if(maxHeap.size() < minHeap.size()){
            if (num > median) {
                maxHeap.add(minHeap.poll());
                minHeap.add(num);
            } else {
                maxHeap.add(num);
            }
            median =(maxHeap.peek() + minHeap.peek())/2.0;
        }else{
            if(num < median) {
                maxHeap.add(num);
                median = maxHeap.peek();
            }else {
                minHeap.add(num);
                median = minHeap.peek();
            }
        }
    }

    public double findMedian() {
        return median;
    }

    public static void main(String[] args) {
        Main medianFinder = new Main();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of inputs: ");
        int numberOfInputs = scanner.nextInt();
        for (int i = 0; i < numberOfInputs; i++) {
            int num = scanner.nextInt();
            medianFinder.addNum(num);
            System.out.println(medianFinder.findMedian());
        }
    scanner.close();
    }
}
