package org.atakoutene;

import java.util.ArrayList;
import java.util.Collections;
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
        List<Integer> array2 = array.subList(0, array.size());

        //Sorting and timing using sequential sorting
        Long startSequentialTime = System.currentTimeMillis();
        Collections.sort(array2);
        Long sequentialTime = System.currentTimeMillis() - startSequentialTime;

        // Create a ForkJoinPool to manage parallel tasks
        ForkJoinPool pool = new ForkJoinPool();

        // Create a MergeSortTask to sort the array
        MergeSortTask task = new MergeSortTask(array, 0, array.size());

        // Record the start time for performance measurement
        Long startTime = System.currentTimeMillis();

        // Invoke the MergeSortTask using the ForkJoinPool
        List<Integer> sortedList = pool.invoke(task);

        // Record the end time for performance measurement
        Long parallelTime = System.currentTimeMillis() - startTime;

        //Compute the speedup
        float speedUp = (float) sequentialTime / parallelTime;

        System.out.println("For " + array.size() + " elements.");
        // Print the elapsed time
        System.out.println("Elapsed Time for sequential sort: " + sequentialTime + "ms");
        System.out.println("Elapsed Time for parallel sort: " + parallelTime + "ms");
        System.out.println("The speedup is: " + speedUp);
    }

    /**
     * Fills the given list with random integers.
     *
     * @param array the list to be filled
     */
    public static void fillarray(List<Integer> array) {
        // Define the size of the array
        int SIZE = 100000000;

        // Create a Random object with a fixed seed for reproducibility
        Random random = new Random(122);

        // Fill the list with random integers between 0 (inclusive) and 500 (exclusive)
        for (int i = 0; i < SIZE; i++) {
            array.add(random.nextInt(500));
        }
    }
}
