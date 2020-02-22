package com.cn.demo;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class CallableDemo3 {
    public static void main(String[] args) {

        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println("start");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        });

        CompletableFuture<String> cf1 = cf.whenComplete((s, throwable) -> {
            if (throwable == null) {
                System.out.println("throwable == null:"+s);
            }
        });

        System.out.println("cf.join:"+cf.join());
        System.out.println("cf1.join:"+cf1.join());

    }

}
