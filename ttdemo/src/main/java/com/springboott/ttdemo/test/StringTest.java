package com.springboott.ttdemo.test;

public class StringTest {
    public static void main(String[] args) throws InterruptedException {
        StringBuffer sub = new StringBuffer();
        StringBuilder sbi = new StringBuilder();
        for (int i = 0; i < 10 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        sub.append(i);
                        System.out.println();
                    }
                }
            }).start();
        }
        for (int i = 0; i < 10 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        sbi.append(i);
                    }
                }
            }).start();
        }
        Thread.sleep(50);
        System.out.println(sub.length());
        System.out.println(sbi.length());
    }
}
