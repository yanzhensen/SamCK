package com.springboott.ttdemo.controller;

import com.springboott.ttdemo.util.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Datetest3 {
    public static int[] getAllDate(String startDate, String endDate) throws ParseException {
        int[] arr = new int[2];
        int monthday = 0;
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate1 = fmt.parse(startDate);
        Calendar starCal = Calendar.getInstance();
        starCal.setTime(startDate1);
        int sYear = starCal.get(Calendar.YEAR);
        int sMonth = starCal.get(Calendar.MONTH);
        int sDay = starCal.get(Calendar.DAY_OF_MONTH);
        Date endDate1 = fmt.parse(endDate);
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate1);
        int eYear = endCal.get(Calendar.YEAR);
        int eMonth = endCal.get(Calendar.MONTH);
        int eDay = endCal.get(Calendar.DAY_OF_MONTH);
        // 算出合约期内的总月数
        monthday = ((eYear - sYear) * 12 + (eMonth - sMonth));
        System.out.println("====================" + monthday);
        // 如果开始日期为一个月的第一天，结束日期是一个月的最后一天，则总月数加一（05.01~06.30）
        if (starCal.getActualMinimum(Calendar.DAY_OF_MONTH) == sDay
                && endCal.getActualMaximum(Calendar.DAY_OF_MONTH) == eDay) {
            monthday += 1;
        }
        // 账单
        if (sDay < eDay) {
            arr[0] = monthday;
            // 给开始的日期进行月份的加减获取新的结束日期
            String newDate = DateUtil.dateAddSub(startDate, arr[0]);
            // 获取剩余的天数
            arr[1] = DateUtil.getDayDiff(newDate, endDate) + 1;
        } else if (sDay > eDay) {
            arr[0] = monthday - 1;
            String newDate = DateUtil.dateAddSub(startDate, arr[0]);
            arr[1] = DateUtil.getDayDiff(newDate, endDate) + 1;
        } else {
            arr[0] = monthday;
            arr[1] = 1;
        }
        System.out.println("arr[0]:" + arr[0]);
        System.out.println("arr[1]:" + arr[1]);
        return arr;
    }

    public static void main(String[] args) throws ParseException {
        String date1 = "2019-08-30";
        String date2 = "2020-02-29";
        getAllDate(date1,date2);
    }
}
