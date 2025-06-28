package org.example.executors;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ParallelSumWithCompletableFuture {

    public static CompletableFuture<Integer> sumRange (int[] arr, int start, int end){
        return CompletableFuture.supplyAsync(
                ()->{
                    int sum =0;
                    for(int i=start; i<=end; i++){
                        sum +=arr[i];
                    }
                    System.out.println("Sum for "+ start+ " to "+end);
                    return sum;
                }
        );
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[] arr = new int[10];
        for(int i=0; i<arr.length; i++) arr[i] = i+1;

        CompletableFuture<Integer> leftSumFuture = sumRange(arr,0,arr.length/2);
        CompletableFuture<Integer> rightSumFuture = sumRange(arr, (arr.length/2)+1, arr.length-1);

        CompletableFuture<Integer> totalSumFuture = leftSumFuture.thenCombine(rightSumFuture,Integer::sum);
        int totalSum = totalSumFuture.get(); // blocks until both complete

        System.out.println("Total Sum = " + totalSum);

    }
}
