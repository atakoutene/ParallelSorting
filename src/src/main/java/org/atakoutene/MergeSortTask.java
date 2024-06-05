package org.atakoutene;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class MergeSortTask extends RecursiveTask<List<Integer>> {
    private static final int THRESHOLD = 150;
    private List<Integer> numbers;
    private int start, end;

    public MergeSortTask(List<Integer> numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    //Testing the merge lists algorithm
//    public static void main(String[] args) {
//        List<Integer> n1 = new ArrayList<>();
//        List<Integer> n2 = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            n1.add(i);
//            n2.add(i*2);
//        }
//        List<Integer> n3 = merge(n1, n2);
//        n3.forEach(System.out::println);
//    }

    @Override
    protected List<Integer> compute() {
        if(end - start <= THRESHOLD) {
            List<Integer> nums = numbers.subList(start, end);
            Collections.sort(nums);
            return nums;
        }
        int mid = (start + end) / 2;
        MergeSortTask leftTask = new MergeSortTask(numbers, start, mid);
        MergeSortTask rightTask = new MergeSortTask(numbers, mid,  end);
        //execute the left task asynchronously
        leftTask.fork();
        //execute the right task directly
        List<Integer> right = rightTask.compute();
        List<Integer> left = leftTask.join();
        return merge(right, left);
    }

    private static List<Integer> merge(List<Integer> right, List<Integer> left) {
        List<Integer> merged = new ArrayList<>();
        int p1 = 0, p2 = 0;
        while(p1 < right.size() && p2 < left.size()) {
            if (right.get(p1) < left.get(p2)) {
                merged.add(right.get(p1));
                p1 += 1;
            }
            else{
                merged.add(left.get(p2));
                p2 += 1;
            }
        }
        if(p1 < right.size()) {
            merged.addAll(right.subList(p1, right.size()));
        }
        else if(p2 < left.size()) {
            merged.addAll(left.subList(p2, left.size()));
        }


        return merged;
    }
}
