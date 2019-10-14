package com.springboott.ttdemo.controller;

import com.springboott.ttdemo.po.User;

import java.util.Timer;
import java.util.TimerTask;

public class Time {
    public static void main(String[] args) throws InterruptedException {

    }

    public static void aa() throws InterruptedException {
//        final String[] a = new String[1];
        Timer timer = new Timer();
        User u = new User();
        final User user = u;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
//                user.setChecked(0);
                new Time().bbb(user);
            }
        }, 100);
        Thread.sleep(101);
    }
    public void bbb(User user){

    }
}
