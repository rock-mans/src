package com.cn.demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CallableDemo12 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<String>();
//        completableFuture.complete("Future's Result");
        if(completableFuture.isDone()) {
            String result = completableFuture.get();
            System.out.println(result);
        }
//        System.out.println("this ...");
    }
}
