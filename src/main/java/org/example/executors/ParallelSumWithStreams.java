package org.example.executors;

import java.util.Arrays;

public class ParallelSumWithStreams {

    public static void main(String[] args) {
        int[] array = new int[10];
        for(int i=0 ;i<array.length; i++) array[i] = i+1;
        int totalSum = Arrays.stream(array)
                .parallel()
                .peek(i -> System.out.println("Processing " + i + " in thread " + Thread.currentThread().getName()))
                .sum();

        System.out.println("Total Sum = " + totalSum); // Should print 55

    }
    /*
        Thread Creation in parallelStream()
           Step-by-Step:
                1. Stream is divided (forked) into chunks internally.
                2. Each chunk is assigned to a worker thread.
                3. All tasks run concurrently in the common thread pool.
                4. Results are joined and combined to get the final result.
     */

}
