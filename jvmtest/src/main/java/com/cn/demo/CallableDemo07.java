package com.cn.demo;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class CallableDemo07 {
    public static void main(String[] args) {

        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println("start");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//            if (true) {
//                throw new RuntimeException("exception");
//            }

            return "hello";
        });

        CompletableFuture<String> cf1 = cf.thenApply(new Function<String, String>() {
            public String apply(String s) {
                System.out.println(s);
                return s + " world";
            }
        });

        System.out.println("cf.join:"+cf.join());
        System.out.println(cf1.join());

    }
}
