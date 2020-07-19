package com.springboott.ttdemo.util;


import com.springboott.ttdemo.common.enums.ScaleEnum;

import java.math.BigDecimal;

public class BigDecimalUtils {
    // 除法运算默认精度 默认两位小数
    private static final ScaleEnum DEF_DIV_SCALE = ScaleEnum.TWO;

    /**
     * 多个数精确加法 使用默认精度
     *
     * @param arg
     * @return 返回double
     */
    public static double add(double... arg) {
        BigDecimal sum = BigDecimal.valueOf(0.0);
        for (double d : arg) {
            BigDecimal bigDecimal = BigDecimal.valueOf(d);
            sum = sum.add(bigDecimal);
        }
        return sum.setScale(DEF_DIV_SCALE.getValue(), BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 多个数精确加法 使用默认精度
     *
     * @param arg
     * @return 返回double
     */
    public static double add(String... arg) {
        BigDecimal sum = new BigDecimal(0.0);
        for (String s : arg) {
            BigDecimal bigDecimal = new BigDecimal(s);
            sum = sum.add(bigDecimal);
        }
        return sum.setScale(DEF_DIV_SCALE.getValue(), BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 多个数精确加法 返回指定精度
     *
     * @param scale
     * @param arg
     * @return 返回double
     */
    public static double add(ScaleEnum scale, double... arg) {
        BigDecimal sum = BigDecimal.valueOf(0.0);
        for (double d : arg) {
            BigDecimal bigDecimal = BigDecimal.valueOf(d);
            sum = sum.add(bigDecimal);
        }
        return sum.setScale(scale.getValue(), BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 多个数精确减法 使用默认精度
     *
     * @param base
     * @param arg
     * @return 返回double
     */
    public static double sub(double base, double... arg) {
        BigDecimal sum = BigDecimal.valueOf(base);
        for (double d : arg) {
            BigDecimal bigDecimal = BigDecimal.valueOf(d);
            sum = sum.subtract(bigDecimal);
        }
        return sum.setScale(DEF_DIV_SCALE.getValue(), BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 多个数精确减法 使用默认精度
     *
     * @param base
     * @param arg
     * @return 返回double
     */
    public static double sub(String base, String... arg) {
        BigDecimal sum = new BigDecimal(base);
        for (String s : arg) {
            BigDecimal bigDecimal = new BigDecimal(s);
            sum = sum.subtract(bigDecimal);
        }
        return sum.setScale(DEF_DIV_SCALE.getValue(), BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 多个数精确减法 返回指定精度
     *
     * @param scale
     * @param arg
     * @return 返回double
     */
    public static double sub(ScaleEnum scale, double base, double... arg) {
        BigDecimal sum = BigDecimal.valueOf(base);
        for (double d : arg) {
            BigDecimal bigDecimal = BigDecimal.valueOf(d);
            sum = sum.subtract(bigDecimal);
        }
        return sum.setScale(scale.getValue(), BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 精确乘法 使用默认精度
     *
     * @param v1
     * @param v2
     * @return 返回double
     */
    public static double mul(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.multiply(b2).setScale(DEF_DIV_SCALE.getValue(), BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 精确乘法 使用默认精度
     *
     * @param value1
     * @param value2
     * @return 返回int
     */
    public static int mulInt(double value1, double value2) {
        BigDecimal b1 = BigDecimal.valueOf(value1);
        BigDecimal b2 = BigDecimal.valueOf(value2);
        return b1.multiply(b2).setScale(DEF_DIV_SCALE.getValue(), BigDecimal.ROUND_HALF_UP).intValue();
    }

    /**
     * 精确批量乘法 使用默认精度
     *
     * @param arg
     * @return 返回double
     */
    public static double mul(double... arg) {
        BigDecimal sum = BigDecimal.valueOf(1);
        for (double d : arg) {
            BigDecimal b = BigDecimal.valueOf(d);
            sum = sum.multiply(b);
        }
        return sum.setScale(DEF_DIV_SCALE.getValue(), BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 精确除法 使用默认精度
     *
     * @param value1
     * @param value2
     * @return
     */
    public static double div(double value1, double value2) {
        return div(value1, value2, DEF_DIV_SCALE);
    }

    /**
     * 精确除法 使用默认精度
     *
     * @param value1
     * @param value2
     * @return
     */
    public static double div(String value1, String value2) {
        return div(value1, value2, DEF_DIV_SCALE);
    }

    /**
     * 精确除法
     *
     * @param scale 精度
     */
    public static double div(double value1, double value2, ScaleEnum scale) {
        BigDecimal b1 = BigDecimal.valueOf(value1);
        BigDecimal b2 = BigDecimal.valueOf(value2);
        return b1.divide(b2, scale.getValue(), BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 精确除法
     *
     * @param value1
     * @param value2
     * @param scale  精度
     * @return
     */
    public static double div(String value1, String value2, ScaleEnum scale) {
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        return b1.divide(b2, scale.getValue(), BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 四舍五入
     *
     * @param v
     * @param scale 小数点后保留几位
     * @return
     */
    public static double round(double v, ScaleEnum scale) {
        return div(v, 1, scale);
    }

    /**
     * 四舍五入
     *
     * @param v
     * @param scale 小数点后保留几位
     * @return
     */
    public static double round(String v, ScaleEnum scale) {
        return div(v, "1", scale);
    }

    /**
     * 比较大小
     *
     * @param b1
     * @param b2
     * @return
     */
    public static boolean equalTo(BigDecimal b1, BigDecimal b2) {
        if (b1 == null || b2 == null) {
            return false;
        }
        return 0 == b1.compareTo(b2);
    }

    public static void main(String[] args) {
        System.out.println(0.1 + 0.2);//0.30000000000000004
        System.out.println(0.3 - 0.2);//0.09999999999999998
        System.out.println(0.1 * 0.2);//0.020000000000000004
        System.out.println(0.1 / 3);//0.03333333333333333
        System.out.println();
        System.out.println(add(0.1, 0.2));//0.3
        System.out.println(sub(0.3, 0.2));//-0.1
        System.out.println(mul(0.1, 0.2));//0.02
        System.out.println(div(0.1, 3));//0.03
        System.out.println(add(0.1, 0.21525));//0.3
        System.out.println(add(ScaleEnum.TWO, 0.1, 0.21525));//0.3
    }
}
