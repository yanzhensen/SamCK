package com.springboott.ttdemo.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class ListXTest {
    public static void main(String[] args) {
        Long startTime;
        Long endTime;
        startTime = System.currentTimeMillis();
        List<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            arrayList.add(i);
        }
        for (int i = 0; i < 50000; i++) {
            arrayList.remove(i);
        }
        endTime = System.currentTimeMillis();
//        System.out.println("arrayList = " + arrayList);
        System.out.println("wasteTime "+(endTime-startTime));

        startTime = System.currentTimeMillis();
        List<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 100000; i++) {
            linkedList.add(i);
        }
        for (int i = 0; i < 50000; i++) {
            linkedList.remove(i);
        }
        endTime = System.currentTimeMillis();
//        System.out.println("linkedList = " + linkedList);
        System.out.println("wasteTime "+(endTime-startTime));

        startTime = System.currentTimeMillis();
        List<Integer> vectorList = new Vector<>();
        for (int i = 0; i < 100000; i++) {
            vectorList.add(i);
        }
        for (int i = 0; i < 50000; i++) {
            vectorList.remove(i);
        }
        endTime = System.currentTimeMillis();
//        System.out.println("vectorList = " + vectorList);
        System.out.println("wasteTime "+(endTime-startTime));

    }
}
