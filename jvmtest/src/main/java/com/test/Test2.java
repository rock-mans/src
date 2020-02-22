package com.test;

public class Test2 {

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(30*1000);
        while (true) {
            loadData();
        }
    }


    private static void loadData() throws InterruptedException {
        byte[] data = null;

        for (int i = 0;i<50;i++){
            data = new byte[1024*1024];
        }

        data = null;

        Thread.sleep(1000);
    }

}
