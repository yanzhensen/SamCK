package com.springboott.ttdemo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    // ********************************** 时间获取以及时间计算、转换 等函数
    // *******************************************************

    /**
     * 获取上上个月一号的日期
     *
     * @return "2018-01-01"
     */
    public static String getLastLastMonthOneDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -2);
        calendar.set(Calendar.DATE, 1);
        Date date = calendar.getTime();
        return sdf.format(date);
    }

    /**
     * 获取今天的日期
     *
     * @return "2018-01-01"
     */
    public static String getToday() {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return fmt.format(date);
    }

    /**
     * 获取两个日期的天数差值
     *
     * @param startDate
     * @param endDate
     * @return
     * @throws ParseException
     */
    public static int getDayDiff(String startDate, String endDate)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(startDate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(endDate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        int days = Integer.parseInt(String.valueOf(between_days));
        return days;
    }

    /**
     * 对给定日期进行年份加减，返回新日期
     *
     * @param date
     * @param nums
     * @return
     * @throws ParseException
     */
    public static String yearAddSub(String date, int nums)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = sdf.parse(date);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);

        rightNow.add(Calendar.YEAR, nums);
        Date dt1 = rightNow.getTime();
        String reStr = sdf.format(dt1);

        return reStr;
    }

    /**
     * 对给定日期进行月份加减，返回新日期
     *
     * @param date
     * @param months
     * @return
     * @throws ParseException
     */
    public static String dateAddSub(String date, int months)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = sdf.parse(date);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);

        rightNow.add(Calendar.MONTH, months);
        Date dt1 = rightNow.getTime();
        String reStr = sdf.format(dt1);
        return reStr;
    }

    /**
     * 对给定日期进行月、日加减，返回新日期
     *
     * @param date
     * @param months
     * @param days
     * @return
     * @throws ParseException
     */
    public static String dateAddSub(String date, int months, int days)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = sdf.parse(date);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);

        rightNow.add(Calendar.MONTH, months);
        rightNow.add(Calendar.DAY_OF_MONTH, days);
        Date dt1 = rightNow.getTime();
        String reStr = sdf.format(dt1);
        return reStr;
    }

    /**
     * 对给定日期进行年、月、日加减，返回新日期
     *
     * @param date
     * @param years
     * @param months
     * @param days
     * @return
     * @throws ParseException
     */
    public static String dateAddSub(String date, int years, int months, int days)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = sdf.parse(date);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.YEAR, years);
        rightNow.add(Calendar.MONTH, months);
        rightNow.add(Calendar.DAY_OF_MONTH, days);
        Date dt1 = rightNow.getTime();
        String reStr = sdf.format(dt1);
        return reStr;
    }

    /**
     * 对给定日期进行天数加减，返回新日期
     *
     * @param date
     * @param nums
     * @return
     * @throws ParseException
     */
    public static String dayAddSub(String date, int nums) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = sdf.parse(date);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);

        rightNow.add(Calendar.DAY_OF_MONTH, nums);
        Date dt1 = rightNow.getTime();
        String reStr = sdf.format(dt1);

        return reStr;
    }

    /**
     * 判断时间字符串格式是否正确
     *
     * @param date
     * @return
     */
    public static boolean juDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt = sdf.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * 计算date中月份的最大天数
     *
     * @param date
     * @return
     */
    public static int getMonthDays(String date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(date.substring(0, 4)));
        calendar.set(Calendar.MONTH, Integer.parseInt(date.substring(5, 7)) - 1);
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return maxDay;
    }

    /**
     * 获取上个月一号的日期
     *
     * @return
     */
    public static String getLastMonthOneDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DATE, 1);
        Date d = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(d);
        return dateNowStr;
    }

    /**
     * 月份的加减
     *
     * @param startData
     * @param num
     * @return
     * @throws ParseException
     */
    public static String getmonth(String startData, int num)
            throws ParseException {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = sd.parse(startData);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.MONTH, num);// 日期加12个月
        rightNow.add(Calendar.DAY_OF_YEAR, -1);
        Date dt1 = rightNow.getTime();
        String reStr = sd.format(dt1);
        // System.out.println(reStr);
        return reStr;
    }

    /**
     * 日期格式转换YYYY/MM/DD -> YYYY-MM-DD
     *
     * @param date
     * @return
     */
    public static String changeDateFormat(String date) {
        StringBuffer buff = new StringBuffer();
        String[] src = {};
        if (date.split("-").length == 3) {
            return date;
        } else if (date.split("_").length == 3) {
            src = date.split("_");
        } else if (date.split("/").length == 3) {
            src = date.split("/");
        } else {
            return "2050-01-01";
        }
        for (int i = 0; i < src.length; ++i) {
            if (i == 0) {
                if (src[i].length() != 4) {
                    return "2050-01-01";
                }
                buff.append(src[i] + "-");
            } else if (i == 1) {
                if (src[i].length() == 1) {
                    buff.append("0" + src[i] + "-");
                    continue;
                }
                buff.append(src[i] + "-");
            } else {
                if (src[i].length() == 1) {
                    buff.append("0" + src[i]);
                    continue;
                }
                buff.append(src[i]);
            }
        }
        return buff.toString();
    }

    /**
     * 两个日期之间有几年几月几日 实现逻辑：
     * 方法名 计算年月日(开始日期，结束日期){
     * y = 1
     * while((开始日期 + y年 - 1天) <= 结束日期 ) {
     * y++
     * }
     * y--
     * m = 1
     * while( (开始日期 + y年 + m月 - 1天) <= 结束日期 ){
     * m++
     * }
     * m--
     * d = 1
     * while( (开始日期 + y年 + m月 + d天 - 1天) <= 结束日期 ){
     * d++
     * }
     * d--
     * return y年m月d日
     * }
     *
     * @param beginDate "yyyy-MM-dd"
     * @param endDate   "yyyy-MM-dd"
     * @return [year, month, day]
     * @throws ParseException
     * @author wangchong
     */
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
//            date.add(Calendar.DATE, -1);
        } while (date.compareTo(end) <= 0);
        y--;

        int m = 0;
        do {
            date.setTime(sdf.parse(beginDate));
            date.add(Calendar.YEAR, y);
            date.add(Calendar.MONTH, ++m);
//            date.add(Calendar.DATE, -1);
        } while (date.compareTo(end) <= 0);
        m--;

        int d = 0;
        do {
            date.setTime(sdf.parse(beginDate));
            date.add(Calendar.YEAR, y);
            date.add(Calendar.MONTH, m);
            date.add(Calendar.DAY_OF_MONTH, ++d);
//            date.add(Calendar.DATE, ++d);
//            date.add(Calendar.DATE, -1);
        } while (date.compareTo(end) <= 0);
        d--;

        int[] result = {y, m, d};
        System.out.println("两个日期之间有" + y + "年" + m + "月" + d + "日");
        return result;
    }


    /**
     * 获取当月的第一天和最后一天
     *
     * @return
     */
    public static String getCurrentMonthTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        // 当月的第一天
        c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        String first = df.format(c.getTime());
        // 当月的最后一天
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = df.format(c.getTime());
        return first + "###" + last;
    }

    /**
     * 获取系统当前日期时间，精确到秒（年 月 日 时 分 秒）
     *
     * @return dateNowStr
     */
    public static String getCurrentDateSecond() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = sdf.format(d);
        return dateNowStr;
    }

    /**
     * 获取系统当前日期时间，精确到日（年 月 日）
     *
     * @return dateNowStr
     */
    public static String getCurrentDate() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(d);
        return dateNowStr;
    }

    /**
     * 对日期进行年、月、日加减，返回新的日期字符串
     *
     * @param date  "2018-01-01"
     * @param year
     * @param month
     * @param day
     * @return "2018-01-01"
     * @throws ParseException
     * @author wangchong
     */
    public static String countDate(String date, int year, int month, int day)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(date));
        cal.add(Calendar.YEAR, year);
        cal.add(Calendar.MONTH, month);
        cal.add(Calendar.DATE, day);
        return sdf.format(cal.getTime());
    }

    /**
     * 计算两个日期间的天数
     *
     * @param begin "2018-01-01"
     * @param end   "2018-01-01"
     * @return 天数
     * @throws ParseException
     * @author wangchong
     */
    public static int countDay(String begin, String end) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(begin));
        long beginMillis = cal.getTimeInMillis();
        cal.setTime(sdf.parse(end));
        long endMillis = cal.getTimeInMillis();
        return (int) ((endMillis - beginMillis) / (1000 * 3600 * 24));
    }
    /**
     * 比较两个时间的大小
     * @param begin
     * @param end
     * @return
     * @throws ParseException
     */
    public static boolean isBefore(String begin, String end) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date bt = sdf.parse(begin);
        Date et = sdf.parse(end);
        return bt.before(et);
    }


    /**
     * 根据两个日期获取合约整期数、剩余不满一期的天数
     *
     * @param startDate
     * @param endDate
     * @param flag
     * @return
     * @throws ParseException
     */
    public static int[] getCyclesAndDays(String startDate, String endDate, int flag) throws ParseException {
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
        System.out.println("===================="+monthday);
        // 如果开始日期为一个月的第一天，结束日期是一个月的最后一天，则总月数加一（05.01~06.30）
        if (starCal.getActualMinimum(Calendar.DAY_OF_MONTH) == sDay
                && endCal.getActualMaximum(Calendar.DAY_OF_MONTH) == eDay) {
            monthday += 1;
        }
        // 账单
        if (flag == 1) {// 月付
            if (sDay < eDay) {
                arr[0] = monthday;
                // 给开始的日期进行月份的加减获取新的结束日期
                String newDate = dateAddSub(startDate, arr[0]);
                // 获取剩余的天数
                arr[1] = getDayDiff(newDate, endDate) + 1;
            } else if (sDay > eDay) {
                arr[0] = monthday - 1;
                String newDate = dateAddSub(startDate, arr[0]);
                arr[1] = getDayDiff(newDate, endDate) + 1;
            } else {
                arr[0] = monthday;
                arr[1] = 1;
            }
        } else if (flag == 2) {// 季付
            if (sDay <= eDay) {
                arr[0] = monthday / 3;
                String newDate = dateAddSub(startDate, arr[0] * 3);
                arr[1] = getDayDiff(newDate, endDate) + 1;
            } else if (sDay > eDay) {
                arr[0] = (monthday - 1) / 3;
                String newDate = dateAddSub(startDate, arr[0] * 3);
                arr[1] = getDayDiff(newDate, endDate) + 1;
            } else {
                System.out.println("________________"+monthday);
                arr[0] = monthday;
                arr[1] = 1;
            }
        } else if (flag == 3) {// 半年付
            if (sDay <= eDay) {
                arr[0] = monthday / 6;
                String newDate = dateAddSub(startDate, arr[0] * 6);
                arr[1] = getDayDiff(newDate, endDate) + 1;
            } else if (sDay > eDay) {
                arr[0] = (monthday - 1) / 6;
                String newDate = dateAddSub(startDate, arr[0] * 6);
                arr[1] = getDayDiff(newDate, endDate) + 1;
            } else {
                arr[0] = monthday;
                arr[1] = 1;
            }
        } else if (flag == 4) {// 年付
            if (sDay <= eDay) {
                arr[0] = monthday / 12;
                String newDate = dateAddSub(startDate, arr[0] * 12);
                arr[1] = getDayDiff(newDate, endDate) + 1;
            } else if (sDay > eDay) {
                arr[0] = (monthday - 1) / 12;
                String newDate = dateAddSub(startDate, arr[0] * 12);
                arr[1] = getDayDiff(newDate, endDate) + 1;
            } else {
                arr[0] = monthday;
                arr[1] = 1;
            }
        } else {
            arr[0] = 0;
            arr[1] = 0;
        }
        System.out.println("arr[0]:" + arr[0]);
        System.out.println("arr[1]:" + arr[1]);
        return arr;
    }
}
