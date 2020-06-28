package com.springboott.ttdemo.test;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.TemporalAdjusters;
import java.util.Objects;

public class LocalDateTest {
    public static void main(String[] args) {
        //Java8 新增日期计算器api
        LocalDate today = LocalDate.now();
        System.out.println(today);
        System.out.println("Today is before 2019-9-26? " + today.isBefore(LocalDate.of(2019, 9, 28)));
        System.out.println("Current time " + today.atTime(LocalTime.now()));
        //plus and minus operations
        System.out.println("10 days after today wi11 be " + today.plusDays(10));
        System.out.println("3 weeks after today will be " + today.plusWeeks(3));
        System.out.println("20 months after today will be " + today.plusMonths(20));
        System.out.println("10 days before today wil1 be " + today.minusDays(10));
        System.out.println("3 weeks before today wil1 be " + today.minusWeeks(3));
        System.out.println("20 months before today will be " + today.minusMonths(20));

        //Temporal adjusters for adjusting the dates
        System.out.println("Eirst date of this month= " + today.with(TemporalAdjusters.firstDayOfMonth()));
        LocalDate lastDayOfYear = today.with(TemporalAdjusters.lastDayOfYear());
        System.out.println("Last date of this year= " + lastDayOfYear);
        Period period = today.until(lastDayOfYear);
        System.out.println("Period Format= " + period);
        System.out.println("Months remaining in the year= " + period.getMonths());


        LocalDate localDate = LocalDate.parse("2020-03-20");
        System.out.println("这周星期几：" + localDate.getDayOfWeek());
        System.out.println("这年第多少天：" + localDate.getDayOfYear());
        System.out.println("获取年：" + localDate.getYear());
        System.out.println("获取月：" + localDate.getMonthValue());
        System.out.println("获取日：" + localDate.getDayOfMonth());

        System.out.println(LocalDate.now().compareTo(LocalDate.now().plusDays(10)));
        System.out.println(LocalDate.now().plusDays(10).compareTo(LocalDate.now()));

        Period next = Period.between(localDate, today);
        System.out.println("两日期相隔多少年 = " + next.getYears());
        System.out.println("两日期相隔多少月 = " + next.getMonths());
        System.out.println("两日期相隔多少天 = " + next.getDays());
    }

    @Test
    public void test1() {
        LocalDate localDate1 = LocalDate.parse("2017-08-28");
        LocalDate localDate2 = LocalDate.parse("2018-09-30");
        int yearsA = localDate1.until(localDate2).getYears();
        int monthsA = localDate1.until(localDate2).getMonths();
        int daysA = localDate1.until(localDate2).getDays();
        long totalMonths = localDate1.until(localDate2).toTotalMonths();
        System.out.println("间隔:" + yearsA + "年" + monthsA + "月" + daysA + "天");
        System.out.println("totalMonths = " + totalMonths);


        String rentFreeDailys = "46#0,46#0,46#0";
//        String rentFreeDailys = "10#10,10#10,10#10";
//        String rentFreeDailys = "10#10,10#10,10#10,10#10";
        LocalDate jrlBeginTime = LocalDate.parse("2020-02-02");
        LocalDate jrlEndTime = LocalDate.parse("2023-02-01");
//        LocalDate jrlBeginTime = LocalDate.parse("2020-02-02");
//        LocalDate jrlEndTime = LocalDate.parse("2021-02-01");
//        LocalDate jrlBeginTime = LocalDate.parse("2020-02-02");
//        LocalDate jrlEndTime = LocalDate.parse("2021-04-01");
//        LocalDate jrlBeginTime = LocalDate.parse("2020-03-02");
//        LocalDate jrlEndTime = LocalDate.parse("2020-09-01");
//        LocalDate jrlBeginTime = LocalDate.parse("2019-04-13");
//        LocalDate jrlEndTime = LocalDate.parse("2024-04-12");
        int y1 = jrlBeginTime.until(jrlEndTime.plusDays(1)).getYears();
        int m1 = jrlBeginTime.until(jrlEndTime.plusDays(1)).getMonths();
        int d1 = jrlBeginTime.until(jrlEndTime.plusDays(1)).getDays();
        String jrlTheTerm = y1 + "年" + m1 + "月" + d1 + "日";
        System.out.println("两时段相差：" + jrlTheTerm);
        int freeDays = 92;//2020-01-02#2020-02-01
        int rentFreeDays = 0;
        StringBuilder jrlRentFreeSegment = new StringBuilder();
        String[] rentSplit = rentFreeDailys.split(",");
        int years = jrlBeginTime.until(jrlEndTime.plusDays(1)).getYears();
        int ss = 0;
        if (years > 0) {
            for (int i = 0; i < years; i++) {
                int daysToAdd = Integer.parseInt(rentSplit[i].split("#")[0]) > 0 ? (Integer.parseInt(rentSplit[i].split("#")[0]) - 1) : 0;
                int daysToSubtract = Integer.parseInt(rentSplit[i].split("#")[1]) > 0 ? Integer.parseInt(rentSplit[i].split("#")[1]) : 1;
                jrlRentFreeSegment.append(jrlBeginTime.plusYears(i) + "#" + jrlBeginTime.plusYears(i).plusDays(daysToAdd) + "#" + jrlBeginTime.plusYears(i + 1).minusDays(daysToSubtract) + "#" + jrlBeginTime.plusYears(i + 1).minusDays(1));
                jrlRentFreeSegment.append(",");
                int days = Integer.parseInt(rentSplit[i].split("#")[0]) + Integer.parseInt(rentSplit[i].split("#")[1]);
                rentFreeDays += days;
                ss = i;
            }
            if (m1 > 0 || d1 > 0) {
                int i = ss + 1;
                int daysToAdd = Integer.parseInt(rentSplit[i].split("#")[0]) > 0 ? (Integer.parseInt(rentSplit[i].split("#")[0]) - 1) : 0;
                int daysToSubtract = Integer.parseInt(rentSplit[i].split("#")[1]) > 0 ? Integer.parseInt(rentSplit[i].split("#")[1]) : 1;
                jrlRentFreeSegment.append(jrlBeginTime.plusYears(i) + "#" + jrlBeginTime.plusYears(i).plusDays(daysToAdd) + "#" + jrlEndTime.plusDays(1).minusDays(daysToSubtract) + "#" + jrlEndTime);
                jrlRentFreeSegment.append(",");
                int days = Integer.parseInt(rentSplit[i].split("#")[0]) + Integer.parseInt(rentSplit[i].split("#")[1]);
                rentFreeDays += days;
            }
            //2020-02-02#2020-02-11#2021-02-01#2021-02-01,2021-02-02#2021-02-02#2021-03-23#2021-04-01
            //2020-02-02#2020-02-11#2021-02-01#2021-02-01,2021-02-02#2021-02-02#2021-03-23#2021-04-01
            jrlRentFreeSegment.deleteCharAt(jrlRentFreeSegment.length() - 1);
        } else {
            int daysToAdd = Integer.parseInt(rentSplit[0].split("#")[0]) > 0 ? (Integer.parseInt(rentSplit[0].split("#")[0]) - 1) : 0;
            int daysToSubtract = Integer.parseInt(rentSplit[0].split("#")[1]) > 0 ? Integer.parseInt(rentSplit[0].split("#")[1]) : 1;
            jrlRentFreeSegment.append(jrlBeginTime + "#" + jrlBeginTime.plusDays(daysToAdd) + "#" + jrlEndTime.plusDays(1).minusDays(daysToSubtract) + "#" + jrlEndTime);
            int days = Integer.parseInt(rentSplit[0].split("#")[0]) + Integer.parseInt(rentSplit[0].split("#")[1]);
            rentFreeDays += days;
        }
        System.out.println("rentFreeDays = " + rentFreeDays);
        System.out.println("jrlRentFreeSegment = " + jrlRentFreeSegment);
        //2020-02-02#2020-02-11#2021-01-23#2021-02-01,2021-02-02#2021-02-11#2022-01-23#2022-02-01,2022-02-02#2022-02-11#2023-01-23#2023-02-01
        String freeDailys = Objects.nonNull(freeDays) && freeDays > 0 ? jrlBeginTime.minusDays(freeDays) + "#" + jrlBeginTime.minusDays(1) : null;
        System.out.println("freeDailys = " + freeDailys);

    }
}
