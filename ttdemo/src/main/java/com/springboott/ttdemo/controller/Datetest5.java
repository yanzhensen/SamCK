package com.springboott.ttdemo.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Datetest5 {
    /**
     * date2比date1多的天数  判断偶数
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Date date1,Date date2)
    {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);
        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2)   //同一年
        {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0)    //闰年
                {
                    timeDistance += 366;
                }
                else	//不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2-day1) ;
        }
        else	//不同年
        {

            return day2-day1;
        }
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysByMillisecond(Date date1,Date date2)
    {
		/*System.out.println(date1);
		System.out.println(date2);
		System.out.println("毫秒数差距：" + (date2.getTime() - date1.getTime()));*/
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return days;
    }


    public static void main(String[] args)
    {
        String dateStr = "2019-08-30 00:00:00";
        String dateStr2 = "2020-2-29 00:00:00";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try
        {
            Date date2 = format.parse(dateStr2);
            Date date = format.parse(dateStr);

            System.out.println("两个日期的差距：" + differentDays(date,date2));
            System.out.println("两个日期的差距：" + differentDaysByMillisecond(date,date2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
