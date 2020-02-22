package com.cn.demo;

import java.util.concurrent.CompletableFuture;

public class CallableDemo4 {
    public static void main(String[] args) {

        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println("start");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (true) {
                throw new RuntimeException("exception");
            }
            return "hello";
        });
        cf.whenComplete((s, throwable) -> {
            if (throwable == null) {
                System.out.println(s);
            } else {
                System.out.println(throwable.getMessage());
            }
        });

        while (!cf.isDone()) {
            System.out.println(".....");
        }
        System.out.println("xxxxx");

    }
}
