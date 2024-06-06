# Parallel Sorting using Multithreading, RecursiveTask, and ForkJoinPool in Java

## Overview

This project demonstrates the implementation of a parallel sorting algorithm using Java's multithreading capabilities. Specifically, it leverages the `RecursiveTask` class and the `ForkJoinPool` to efficiently sort large arrays in parallel. This implementation is particularly useful for improving performance on multi-core processors.

## Features

- **Parallel Merge Sort**: The algorithm uses the merge sort technique, which is well-suited for parallel execution.
- **ForkJoin Framework**: Utilizes Java's `ForkJoinPool` to manage and execute tasks.
- **Scalable Performance**: Designed to take advantage of multiple CPU cores, resulting in faster sorting times for large datasets.

## Getting Started

### Prerequisites

- Java 8 or higher
- Maven (for building the project)

### Installation

1. **Clone the repository**:
    ```bash
    git clone https://github.com/atakoutene/ParallelSorting
    cd parallel-sorting
    ```

2. **Build the project using Maven**:
    ```bash
    mvn clean install
    ```

### Usage

Here is a quick example of how to use the parallel sorting algorithm in your code:

```java
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        List<Integer> array = List.of(5, 3, 8, 4, 2, 7, 1, 6);

        ForkJoinPool pool = new ForkJoinPool();
        ParallelMergeSort task = new ParallelMergeSort(array);

        pool.invoke(task);

        List<Integer> sortedArray = task.join();

        sortedArray.forEach(e -> {System.out::println});
    }
}
```



### ForkJoinPool

The `ForkJoinPool` is used to execute the sorting tasks in parallel. It automatically manages the thread pool and task scheduling.

```java
ForkJoinPool pool = new ForkJoinPool();
        ParallelMergeSort task = new ParallelMergeSort(array);

        pool.invoke(task);

        List<Integer> sortedArray = task.join();
```

## Performance

The parallel merge sort algorithm can significantly reduce the sorting time for large arrays by dividing the work across multiple CPU cores. However, the performance gain depends on the hardware and the size of the input array.

## Contributing

Contributions are welcome! Please feel free to submit a pull request or open an issue if you have any suggestions or bug reports.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
