package com.springboott.ttdemo.controller;

import com.springboott.ttdemo.util.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Datetest6 {
    public static void main(String[] args) throws ParseException {
        String date1 = "2019-08-30";
        String date2 = "2020-02-29";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        Date dt1 = sdf.parse(date1);
        Date dt2 = sdf.parse(date2);
        c1.setTime(dt1);
        c2.setTime(dt2);
        int diff = DateUtil.getDayDiff("2019-08-30","2020-02-29");
        System.out.println(diff);
//        String s = dateAddSub("2019-08-30",0,6,0);
//        System.out.println(s);
        int moday = getMonthDays("2020-02-02");
        System.out.println(moday);
        int yeday=365;
        int year=0;
        int mon=0;
        int day=0;
        if(c2.get(Calendar.YEAR)%4==0){
            yeday=366;
        }
        do{
            if(diff-yeday>0){
                diff=diff-yeday;
                year++;
            }
        }while (diff-yeday>0);

        do {
            if(diff-moday>0){
                diff=diff-moday;
                mon++;
            }
        }while (diff-moday>0);
        do {
            if(diff-1>0){
                diff--;
                day++;
            }
        }while (diff-day>0);
//        System.out.println(year);
//        System.out.println(mon);
//        System.out.println(day);
        getYearMonthDay(date1,date2);
        System.out.println(countDate("2020-01-30",0,1,-1));
    }
    public static int getMonthDays(String date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(date.substring(0, 4)));
        calendar.set(Calendar.MONTH, Integer.parseInt(date.substring(5, 7)));
        calendar.set(Calendar.DAY_OF_MONTH,0);
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return maxDay;
    }
    public static String dateAddSub(String date, int years, int months, int days)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = sdf.parse(date);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.YEAR, years);
        rightNow.add(Calendar.MONTH, months);
        rightNow.add(Calendar.DAY_OF_MONTH, days);
        System.out.println(rightNow.get(Calendar.YEAR));
        System.out.println(rightNow.get(Calendar.MONTH)+1);
        System.out.println(rightNow.get(Calendar.DAY_OF_MONTH));
        Date dt1 = rightNow.getTime();
        String reStr = sdf.format(dt1);
        return reStr;
    }
    public static String countDate(String date, int year, int month, int day)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(date));
        cal.add(Calendar.YEAR, year);
        cal.add(Calendar.MONTH, month);
        cal.add(Calendar.DAY_OF_MONTH, day);
        return sdf.format(cal.getTime());
    }
    public static int[] getYearMonthDay(String beginDate, String endDate)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar begin = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        Calendar date = Calendar.getInstance();
        begin.setTime(sdf.parse(beginDate));
        end.setTime(sdf.parse(endDate));
        if (begin.compareTo(end) >= 0) {
            int[] result = {0, 0, 0};
            return result;
        }

        int y = 0;
        do {
            date.setTime(sdf.parse(beginDate));
            date.add(Calendar.YEAR, ++y);
            date.add(Calendar.DATE, -1);
        } while (date.compareTo(end) <= 0);
        y--;

        int m = 0;
        do {
            date.setTime(sdf.parse(beginDate));
            date.add(Calendar.YEAR, y);
            date.add(Calendar.MONTH, ++m);
            date.add(Calendar.DATE, -1);
        } while (date.compareTo(end) <= 0);
        m--;

        int d = 0;
        do {
            date.setTime(sdf.parse(beginDate));
            date.add(Calendar.YEAR, y);
            date.add(Calendar.MONTH, m);
            date.add(Calendar.DAY_OF_MONTH, ++d);
//            date.add(Calendar.DATE, -1);
        } while (date.compareTo(end) <= 0);
        d--;

        int[] result = {y, m, d};
        System.out.println("两个日期之间有" + y + "年" + m + "月" + d + "日");
        return result;
    }
}
