package com.springboott.ttdemo.test;

import com.springboott.ttdemo.po.CangKu;
import com.springboott.ttdemo.po.Consumers;
import com.springboott.ttdemo.po.Producters;

public class ConsumeTest {
    public static void main(String[] args) throws Exception {

        /**
         * 1.基本角色
         * 仓库、 生产者、 消费者
         *
         * 2.基本思想
         * 仓库里放着俩引擎，一个生成一个消费。
         * 仓库有一个初始化的最大存放量，当消费者消费完所有的产品，则消费的线程进人等待,。当生成到仓库最大库存就让生产者进入等待，并唤醒消费者进行消费。
         *然后唤醒所有的生产者线程开始生产
         */
        //仓库
        CangKu cangku = new CangKu();

        //生产者
        Producters producters1 = new Producters(cangku);
        producters1.setNum(10);

        Producters producters2 = new Producters(cangku);
        producters2.setNum(20);

        Producters producters3 = new Producters(cangku);
        producters3.setNum(30);

        Producters producters4 = new Producters(cangku);
        producters4.setNum(40);

        Producters producters5 = new Producters(cangku);
        producters5.setNum(50);

        Producters producters6 = new Producters(cangku);
        producters6.setNum(60);

        Producters producters7 = new Producters(cangku);
        producters7.setNum(70);

        Producters producters8 = new Producters(cangku);
        producters8.setNum(80);

        //消费者
        Consumers consumers1 = new Consumers(cangku);
        consumers1.setNum(20);

        Consumers consumers2 = new Consumers(cangku);
        consumers2.setNum(30);

        Consumers consumers3 = new Consumers(cangku);
        consumers3.setNum(40);

        Consumers consumers4 = new Consumers(cangku);
        consumers4.setNum(50);

        Consumers consumers5 = new Consumers(cangku);
        consumers5.setNum(60);

        //八个生产者
        new Thread(producters1).start();
        new Thread(producters2).start();
        new Thread(producters3).start();
        new Thread(producters4).start();
        new Thread(producters5).start();
        new Thread(producters6).start();
        new Thread(producters7).start();
        new Thread(producters8).start();

        //四个消费者
        new Thread(consumers1).start();
        new Thread(consumers2).start();
        new Thread(consumers3).start();
        new Thread(consumers4).start();
        new Thread(consumers5).start();
        Thread.sleep(5000);
        new Thread(consumers3).start();
        //new Thread(consumers5).start();

    }
}
