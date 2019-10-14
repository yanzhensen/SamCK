package com.springboott.ttdemo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Datetest4 {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = "2019-08-30";
        String date2 = "2020-02-29";
        Date dt1 = sdf.parse(date1);
        Date dt2 = sdf.parse(date2);
        dayComparePrecise(dt1,dt2);
    }
    public static String dayComparePrecise(Date fromDate, Date toDate){
        Calendar  from  =  Calendar.getInstance();
        from.setTime(fromDate);
        Calendar  to  =  Calendar.getInstance();
        to.setTime(toDate);
        int fromYear = from.get(Calendar.YEAR);
        int fromMonth = from.get(Calendar.MONTH);
        int fromDay = from.get(Calendar.DAY_OF_MONTH);
        int toYear = to.get(Calendar.YEAR);
        int toMonth = to.get(Calendar.MONTH);
        int toDay = to.get(Calendar.DAY_OF_MONTH);
        int year = toYear  -  fromYear;
        int month = toMonth  - fromMonth;
        int day = toDay  - fromDay;
        System.out.println(year);
        System.out.println(month);
        System.out.println(day);
        return null;
    }
}
