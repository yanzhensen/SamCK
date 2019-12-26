package com.springboott.ttdemo.test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.TemporalAdjusters;

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
    }
}
