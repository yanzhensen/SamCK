package com.springboott.ttdemo.po;

//--消费者--
public class Consumers extends Thread{

    private int num;

    private CangKu cangku;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Consumers(CangKu cangku){
        this.cangku = cangku;
    }

    public void consumer(int num) {
        cangku.consumer(num);
    }

    @Override
    public void run() {
        consumer(num);
    }
}
