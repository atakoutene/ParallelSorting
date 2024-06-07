package org.atakoutene;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * A RecursiveTask implementation for performing merge sort on a list of integers.
 */
public class MergeSortTask extends RecursiveTask<List<Integer>> {
    // Threshold for switching to sequential sort
    private static final int THRESHOLD = 15;

    // List of numbers to be sorted
    private List<Integer> numbers;

    // Start and end indices for the current task
    private int start, end;

    /**
     * Constructor to initialize the MergeSortTask.
     *
     * @param numbers the list of integers to sort
     * @param start the starting index of the sublist
     * @param end the ending index of the sublist
     */
    public MergeSortTask(List<Integer> numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    // Uncomment this block for testing the merge lists algorithm
    /*
    public static void main(String[] args) {
        List<Integer> n1 = new ArrayList<>();
        List<Integer> n2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            n1.add(i);
            n2.add(i * 2);
        }
        List<Integer> n3 = merge(n1, n2);
        n3.forEach(System.out::println);
    }
    */

    /**
     * The main computation performed by the task.
     *
     * @return the sorted list of integers
     */

    @Override
    protected List<Integer> compute() {
        // If the sublist size is below the threshold, sort it directly
        if (end - start <= THRESHOLD) {
            List<Integer> nums = new ArrayList<>(numbers.subList(start, end));
            Collections.sort(nums);
            return nums;
        }
        // Find the middle index to split the list
        int mid = (start + end) / 2;
        // Create subtasks for the left and right halves
        MergeSortTask leftTask = new MergeSortTask(numbers, start, mid);
        MergeSortTask rightTask = new MergeSortTask(numbers, mid, end);
        // Execute the left task asynchronously
        leftTask.fork();
        // Execute the right task directly
        List<Integer> right = rightTask.compute();
        // Wait for the left task to complete and get the result
        List<Integer> left = leftTask.join();
        // Merge the results of the left and right tasks
        return merge(right, left);
    }

    /**
     * Merges two sorted lists into a single sorted list.
     *
     * @param right the first sorted list
     * @param left the second sorted list
     * @return the merged sorted list
     */
    private static List<Integer> merge(List<Integer> right, List<Integer> left) {
        List<Integer> merged = new ArrayList<>();
        int p1 = 0, p2 = 0;

        // Merge elements from both lists in sorted order
        while (p1 < right.size() && p2 < left.size()) {
            if (right.get(p1) < left.get(p2)) {
                merged.add(right.get(p1));
                p1 += 1;
            } else {
                merged.add(left.get(p2));
                p2 += 1;
            }
        }

        // Append remaining elements from right list, if any
        if (p1 < right.size()) {
            merged.addAll(right.subList(p1, right.size()));
        }
        // Append remaining elements from left list, if any
        else if (p2 < left.size()) {
            merged.addAll(left.subList(p2, left.size()));
        }

        return merged;
    }
}
