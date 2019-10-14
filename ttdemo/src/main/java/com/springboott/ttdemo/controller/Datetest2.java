package com.springboott.ttdemo.controller;

import java.util.Calendar;

public class Datetest2 {
    public static void main(String[] args) {
        int ret[] = getDateLength("20190830", "20200229");
        System.out.println(ret[0] + ": " + ret[1] + ": " + ret[2]);
    }

    public static int[] getDateLength(String fromDate, String toDate) {
        Calendar c1 = getCal(fromDate);
        Calendar c2 = getCal(toDate);
        int[] p1 = {c1.get(Calendar.YEAR), c1.get(Calendar.MONTH), c1.get(Calendar.DAY_OF_MONTH)};
        int[] p2 = {c2.get(Calendar.YEAR), c2.get(Calendar.MONTH), c2.get(Calendar.DAY_OF_MONTH)};
        return new int[]{p2[0] - p1[0], p2[0] * 12 + p2[1] - p1[0] * 12 - p1[1], (int) ((c2.getTimeInMillis() - c1.getTimeInMillis()) / (24 * 3600 * 1000))};
    }

    public static Calendar getCal(String date) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(4, 6)) - 1, Integer.parseInt(date.substring(6, 8)));
        return cal;
    }
}
