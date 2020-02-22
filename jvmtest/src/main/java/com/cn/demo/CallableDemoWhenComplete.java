package com.cn.demo;

import java.util.concurrent.CompletableFuture;

public class CallableDemoWhenComplete {
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
        CompletableFuture<String> cf1 = cf.whenComplete((s, throwable) -> {
            if (throwable == null) {
                System.out.println(s);
            }
        });

        System.out.println(cf.join());
        System.out.println(cf1.join());
    }
}
