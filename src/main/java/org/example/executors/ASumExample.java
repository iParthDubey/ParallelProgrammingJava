package org.example.executors;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

import static java.util.concurrent.ForkJoinTask.invokeAll;

public class ASumExample {

    static class Asum extends RecursiveAction {
        int[] A;
        int low, high;
        int sum;

        Asum(int[] A, int low, int high) {
            this.A = A;
            this.low = low;
            this.high = high;
            this.sum = 0;
        }
        @Override
        protected void compute() {
            if (high - low <= 1) {
                sum += A[low];
            } else {
                int mid = (low + high) / 2;
                Asum left = new Asum(A, low, mid);
                Asum right = new Asum(A, mid, high);
                invokeAll(left, right);
                sum = left.sum + right.sum;
            }
        }

    }

    public static void main(String[] args) {
        int[] array = new int[10];
        for (int i = 0; i < array.length; i++) array[i] = i + 1;

        Asum task = new Asum(array, 0, array.length);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(task);

        System.out.println("Sum = " + task.sum); // Should print 55
    }
}