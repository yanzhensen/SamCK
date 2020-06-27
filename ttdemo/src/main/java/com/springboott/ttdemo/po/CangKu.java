package com.springboott.ttdemo.po;

import java.util.LinkedList;

//--仓库--
public class CangKu {

    // 仓库最大的容量
    private final int MAX_SIZE = 100;

    // 载体 双向链表/队列
    LinkedList<Object> linkedList = new LinkedList<Object>();

    /**
     * 生产者方法/引擎
     */
    public void produce(int num) {
        synchronized (linkedList) {
            while (linkedList.size() + num > MAX_SIZE) {
                System.out.println("无法生产,大于仓库最大容量");
                try {
                    linkedList.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //消费者消耗部分，while条件不成立，继续执行for
            for (int i = 1; i <= num; ++i) {
                linkedList.add(new Object());
            }
            System.out.println("【已经生产粮食】:" + num + "/t【仓库中粮食总量】:" + linkedList.size());
            linkedList.notifyAll();

        }
    }

    /**
     * 消费者方法/引擎
     */
    public void consumer(int num) {
        synchronized (linkedList) {
            while (linkedList.size() < num) {
                System.out.println("粮食不足,停止消费");
                try {
                    linkedList.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //粮食充足可以消费
            for (int i = 1; i <= num; ++i) {
                linkedList.remove();
            }
            System.out.println("【已经消费产品数】:" + num + "/t【现仓库存储量为】：" + linkedList.size());
            //唤醒全部生产者
            linkedList.notifyAll();
        }
    }
}
