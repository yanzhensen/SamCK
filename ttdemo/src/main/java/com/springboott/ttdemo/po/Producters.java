package com.springboott.ttdemo.po;

//--生产者--
public class Producters extends Thread {

    private int num;

    private CangKu cangku;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Producters(CangKu cangku) {
        this.cangku = cangku;
    }

    public void produce(int num) {
        cangku.produce(num);
    }

    @Override
    public void run() {
        produce(num);
    }
}
