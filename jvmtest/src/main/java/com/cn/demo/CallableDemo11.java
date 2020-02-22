package com.cn.demo;

import java.util.concurrent.CompletableFuture;

public class CallableDemo11 {
    public static void main(String[] args) {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getId()+"  start");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        });

        cf.thenRun(() -> System.out.println(Thread.currentThread().getId()+" end")).join();
    }
}
