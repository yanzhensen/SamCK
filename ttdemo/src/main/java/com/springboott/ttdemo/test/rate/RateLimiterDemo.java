package com.springboott.ttdemo.test.rate;

import com.google.common.util.concurrent.RateLimiter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

public class RateLimiterDemo {

    private static ReentrantLock lock = new ReentrantLock(true);

    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final int THREAD_COUNT = 100;

    private static int MAX_COUNT = 20;

    /**
     * RateLimit限流神器 获取令牌的方式限制请求执行数量
     * MAX_COUNT 来设置商品总数量
     * THREAD_COUNT 模拟请求数量
     */
    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(5);
        for (int i = 0; i < THREAD_COUNT; i++) {
            new Thread(new RateLimiterThread(rateLimiter), "RateLimiterThread-" + i).start();
        }
    }

    public static boolean miaosha(String threadName) {
        lock.lock();
        try {
            if (MAX_COUNT > 0) {
                MAX_COUNT--;
                return true;
            }
        } finally {
            lock.unlock();
        }
        System.out.println("抢购结束-失败线程：" + threadName);
        return false;
    }

    public static class RateLimiterThread implements Runnable {

        private RateLimiter rateLimiter;

        public RateLimiterThread(RateLimiter rateLimiter) {
            this.rateLimiter = rateLimiter;
        }

        @Override
        public void run() {
            if (miaosha(Thread.currentThread().getName())) {
                double acquire = rateLimiter.acquire(1);
                System.out.println("获取到了令牌线程：" + Thread.currentThread().getName() + "，时间 = " + FORMATTER.format(new Date()) + "，耗时" + acquire);
            }
        }

    }
}
