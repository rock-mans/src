package com.cn.demo;

import java.util.concurrent.CompletableFuture;

public class CallableDemo10 {
    public static void main(String[] args) {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getId()+" cf start");
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        });
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getId()+" cf1 start");
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "world";
        });

        cf.runAfterBoth(cf1, () -> {
            System.out.println(Thread.currentThread().getId()+" join end");
        }).join();
    }
}
