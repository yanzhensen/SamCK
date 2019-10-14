package com.springboott.ttdemo.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;

public class HashXTest {
    public static void main(String[] args) {
        //HashSet HashMap HashTable
        //实现Set
        HashSet hset = new HashSet();
        hset.add("1"); hset.add("2"); hset.add("3");
        hset.add("5"); hset.add("4"); hset.add("2");
        System.out.println("hset = " + hset);
        //实现Map
        HashMap hmap = new HashMap();
        hmap.put("1",1); hmap.put("2",2); hmap.put("3",3);
        hmap.put("5",5); hmap.put("4",4); hmap.put("2",2);
        System.out.println("hmap = " + hmap);
        //实现Map
        Hashtable htable = new Hashtable();
        htable.put("1",1); htable.put("2",2); htable.put("3",3);
        htable.put("5",5); htable.put("4",4); htable.put("2",2);
        System.out.println("htable = " + htable);
    }

}
