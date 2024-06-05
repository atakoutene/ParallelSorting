package org.atakoutene;
import java.concurrent.*;


public class Main {
    public static void main(String[] args) {
        int SIZE = 10000;
        ArrayList<Integer> array = new int[SIZE];
        for(int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        ForkJoinPool pool = new ForkJoinPool();
        MergeSortTask task = new MergeSortTask(array, 0, SIZE);

        Long startTime = System.currentTimeMillis();
        ArrayList<Integer> sortedList = pool.invoke(task);
        Long endTime = System.currentTimeMillis();

        System.out.println("The sorted elements are: ") ;
        sortedList.forEach((el) -> {
            System.out.print(el + " ");
        })

        System.out.println("\nElapsed Time: " + endTime - startTime + "ms");
    }

    public static void fillarray(int[] array) {
        int SIZE = array.length;
        Random random = new Random();
        for(int i = 0; i < SIZE; i++) {
            array[i] = random.nextInt();
        }
    }
}