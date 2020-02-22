package com.cn.demo;

import java.util.concurrent.*;

public class CallableDemo {
    public static void main(String[] args) throws Exception {
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        Future<String> stringFuture = executor.submit(new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                Thread.sleep(2000);
//                return "async thread";
//            }
//        });
//        Thread.sleep(1000);
//        System.out.println("main thread");
//        System.out.println(stringFuture.get(1, TimeUnit.MICROSECONDS));
////        ForkJoinPool ForkJoinPool = null;


        CompletableFuture<String> futureA = CompletableFuture.
                supplyAsync(() -> "执行结果:" + (100 / 0))
                .thenApply(s -> "apply result:" + s)
                .whenComplete((s, e) -> {
                    if (s != null) {
                        System.out.println(s);//未执行
                    }
                    if (e == null) {
                        System.out.println(s);//未执行
                    } else {
                        System.out.println(e.getMessage());//java.lang.ArithmeticException: / by zero
                    }
                })
                .exceptionally(e -> {
                    System.out.println("ex"+e.getMessage()); //ex:java.lang.ArithmeticException: / by zero
                      return "futureA result: 100"; });
        System.out.println(futureA.join());//futureA result: 100


    }

}
