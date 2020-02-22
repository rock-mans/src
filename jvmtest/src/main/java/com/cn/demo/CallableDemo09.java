package com.cn.demo;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class CallableDemo09 {
    public static void main(String[] args) {

        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getId()+" cf..start");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        });


        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getId()+" cf1..start");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "world";
        });

       cf.thenAcceptBoth(cf1, (s, s2) -> {
            System.out.println(Thread.currentThread().getId()+"``  "+s + " ..... " + s2);
        }).join();


    }
}
