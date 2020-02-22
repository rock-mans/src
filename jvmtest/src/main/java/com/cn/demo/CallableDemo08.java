package com.cn.demo;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;

public class CallableDemo08 {
    public static void main(String[] args) {

        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getId()+" start");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        });


        cf.thenAccept(new Consumer<String>() {
            public void accept(String s) {
                System.out.println(Thread.currentThread().getId()+" s:"+s);
            }
        });

        System.out.println(Thread.currentThread().getId()+" cf.join:"+cf.join());
    }
}
