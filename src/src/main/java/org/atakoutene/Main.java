package org.atakoutene;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;


public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> array = new ArrayList<>();
        fillarray(array);
        ForkJoinPool pool = new ForkJoinPool();
        MergeSortTask task = new MergeSortTask(array, 0, array.size());

        Long startTime = System.currentTimeMillis();
        List<Integer> sortedList = pool.invoke(task);
        Long endTime = System.currentTimeMillis();

        System.out.println("The sorted elements are: ");
        sortedList.forEach((el) -> {
            System.out.print(el + " ");
        });

        System.out.println("\nElapsed Time: " + (endTime - startTime) + "ms");
    }

    public static void fillarray(List<Integer> array) {
        int SIZE = 10000;
        Random random = new Random(122);
        for(int i = 0; i < SIZE; i++) {
            array.add(random.nextInt(500));
        }
    }
}