package com.cn.demo;

import java.util.concurrent.CompletableFuture;

public class CallableDemo5 {
    public static void main(String[] args) {

//        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
//            System.out.println("start");
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            if (true) {
//                throw new RuntimeException("exception");
//            }
//            return "hello";
//        });
//
//        cf.whenComplete((s, throwable) -> {
//            if (throwable == null) {
//                System.out.println(s);
//            } else {
//                System.out.println("cf.whenComplete...."+throwable.getMessage());
//            }
//        });
//
//        CompletableFuture<String> cf1 = cf.exceptionally(throwable -> {
//            System.out.println("cf.exceptionally....."+throwable.getMessage());
//            return "exception happened";
//        });
//
//        System.out.println("cf1.join:"+cf1.join());

        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println("start");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
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

        CompletableFuture<String> cf1 = cf.exceptionally(throwable -> {
            System.out.println(throwable.getMessage());
            return "exception happened";
        });

        System.out.println(cf1.join());

    }
}
