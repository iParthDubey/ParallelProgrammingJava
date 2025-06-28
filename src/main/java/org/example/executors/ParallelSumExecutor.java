package org.example.executors;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelSumExecutor {

    static class SumTask implements Callable<Integer> {
        int[] A;
        int start, end;

        SumTask(int[] A, int start, int end) {
            this.A = A;
            this.start = start;
            this.end = end;
        }

        @Override
        public Integer call() {
            int sum = 0;
            for (int i = start; i <= end; i++) {
                sum += A[i];
            }
            return sum;
        }
    }

    public static void main(String[] args) throws Exception {
        int[] array = new int[10];
        for (int i = 0; i < array.length; i++) array[i] = i + 1;

//      The code will automatically
//      shut down the ExecutorService — but only if you're using Java 9 or higher.
        try (ExecutorService executorService = Executors.newFixedThreadPool(2)) {
            SumTask task1 = new SumTask(array, 0, 4); // First half
            SumTask task2 = new SumTask(array, 5, 9); // Second half

            var future1 = executorService.submit(task1);
            var future2 = executorService.submit(task2);

            int sum1 = future1.get();
            int sum2 = future2.get();

            System.out.println("Sum = " + (sum1 + sum2)); // Should print 55
        }

        /*
            This relies on the fact that as of Java 9,
            ExecutorService inherits AutoCloseable via the
            java.util.concurrent.ExecutorService interface,
            allowing it to be used in a try-with-resources block.

            When the try block finishes (normally or due to an exception),
            the close() method is called — which in this case calls executorService.shutdown().

         */
    }
}
