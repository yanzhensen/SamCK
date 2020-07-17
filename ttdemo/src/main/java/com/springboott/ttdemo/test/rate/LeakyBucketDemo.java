package com.springboott.ttdemo.test.rate;

//漏桶
public class LeakyBucketDemo {
    //时间刻度
    private static long time = System.currentTimeMillis();
    //桶里面的水
    private static int water = 0;
    //桶的大小
    private static int size = 50;
    //出水速率
    private static int rate = 3;

    public static boolean miaosha() {
        //计算出水的数量
        long now = System.currentTimeMillis();
        int out = (int) ((now - time) / 700 * rate);
        int b = water - out;
        water = Math.max(0, b);
        System.out.println("now = " + now + " time = " + time + " water = " + water + " b = " + b);
        time = now;
        if ((water + 1) < size) {
            ++water;
            return true;
        } else {
            return false;
        }
    }

    /**
     * 虽然滑动窗口有效避免了时间临界点的问题，但是依然有时间片的概念，而漏桶算法在这方面比滑动窗口而言，更加先进。有一个固定的桶，进水的速率是不确定的，但是出水的速率是恒定的，当水满的时候是会溢出的。
     */
    public static void main(String[] args) {
        try {
            for (int i = 0; i < 200; i++) {
                if (i == 100) {
                    Thread.sleep(2000);
                    System.out.println("#########停止两秒#########");
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (miaosha()) {
                            System.out.println("执行业务逻辑");
                        } else {
                            System.out.println("限流");
                        }
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
