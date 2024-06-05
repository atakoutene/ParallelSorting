package org.atakoutene;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

/**
 * Main class to demonstrate the use of ForkJoinPool for parallel merge sort.
 */
public class Main {
    public static void main(String[] args) {
        // Initialize an ArrayList to hold integers
        ArrayList<Integer> array = new ArrayList<>();

        // Fill the array with random integers
        fillarray(array);

        // Create a ForkJoinPool to manage parallel tasks
        ForkJoinPool pool = new ForkJoinPool();

        // Create a MergeSortTask to sort the array
        MergeSortTask task = new MergeSortTask(array, 0, array.size());

        // Record the start time for performance measurement
        Long startTime = System.currentTimeMillis();

        // Invoke the MergeSortTask using the ForkJoinPool
        List<Integer> sortedList = pool.invoke(task);

        // Record the end time for performance measurement
        Long endTime = System.currentTimeMillis();

        // Print the sorted elements
        System.out.println("The sorted elements are: ");
        sortedList.forEach((el) -> {
            System.out.print(el + " ");
        });

        // Print the elapsed time
        System.out.println("\nElapsed Time: " + (endTime - startTime) + "ms");
    }

    /**
     * Fills the given list with random integers.
     *
     * @param array the list to be filled
     */
    public static void fillarray(List<Integer> array) {
        // Define the size of the array
        int SIZE = 10000;

        // Create a Random object with a fixed seed for reproducibility
        Random random = new Random(122);

        // Fill the list with random integers between 0 (inclusive) and 500 (exclusive)
        for (int i = 0; i < SIZE; i++) {
            array.add(random.nextInt(500));
        }
    }
}
