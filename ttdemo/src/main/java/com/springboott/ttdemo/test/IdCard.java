package com.springboott.ttdemo.test;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author xieliang
 * @Description
 * @Date Create in 上午 11:40 2018-07-02
 * Modified By:
 */
public class IdCard {

    private static Logger log = LoggerFactory.getLogger(IdCard.class);

    private static final String AREA_CODE = "/area-codes.properties";


    public static JSONObject analysisIdCard(String idCard) {
        idCard = idCard.trim();
        JSONObject map = new JSONObject();
        if (!IdCardUtil.isValidatedAllIdcard(idCard)) {
            map.put("code", "400");
            map.put("msg", "请检查身份证号码是否正确");
            return map;
        }
        String area = analysisAddress(idCard.substring(0, 6));
        map.put("address", area);
        String birthday = analysisBirthday(idCard);
        map.put("birthday", birthday);
        String sex = analysisSex(idCard);
        map.put("sex", sex);
        int age = analysisAge(idCard);
        map.put("age", age);
        map.put("code", "200");
        map.put("msg", "成功");
        return map;
    }

    /**
     * 根据身份证解析 籍贯所在地
     *
     * @param code
     * @return
     */
    public static String analysisAddress(String code) {
        Properties properties = new Properties();
        String area;
        try {
            properties.load(IdCard.class.getResourceAsStream(AREA_CODE));
            area = properties.getProperty(code);
            if (area == null) {
                return "未知区域";
            }
            area = new String(area.getBytes("iso-8859-1"), "utf-8");
//            area = new String(area.getBytes("iso-8859-1"), "gbk");
            return area;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            log.error("解析身份证地址异常", ioe);
        }
        return null;
    }

    /**
     * 根据身份证解析 出生年月
     *
     * @param idCard
     * @return
     */
    public static String analysisBirthday(String idCard) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = analysisDate(idCard);
        return format.format(date);
    }

    public static Date analysisDate(String idCard) {
        String birthday = null;
        SimpleDateFormat format = null;
        Date date = new Date();
        if (idCard.length() == 18) {
            birthday = idCard.substring(6, 14);
            format = new SimpleDateFormat("yyyyMMdd");
        } else if (idCard.length() == 15) {
            birthday = idCard.substring(6, 12);
            format = new SimpleDateFormat("yyMMdd");
        }
        try {
            date = format.parse(birthday);
        } catch (ParseException pe) {
            log.error("日期转换异常===>", pe);
        } catch (NullPointerException pe) {
            log.error("请检查身份证号码是否正确");
        }
        return date;
    }

    /**
     * 根据身份证 解析年龄
     *
     * @param idCard
     * @return
     */
    public static int analysisAge(String idCard) {
        Date date = analysisDate(idCard);
        int age = Integer.parseInt(String.valueOf(((date.getTime() - System.currentTimeMillis()) / 86400000)));
        return age;
    }


    /**
     * 根据身份证 解析性别 身份证倒数第二位 奇数代表男 偶数代表女
     *
     * @param idCard
     * @return
     */
    public static String analysisSex(String idCard) {
        String sex = null;
        if (idCard.length() == 15) {
            sex = idCard.substring(14, 15);
        } else if (idCard.length() == 18) {
            sex = idCard.substring(16, 17);
        }
        int se = Integer.parseInt(sex);
        return se % 2 == 0 ? "女" : "男";
    }


    public static void main(String[] args) {
        List<String> strList = new LinkedList<>();
        strList.add("420113199003075937");
        strList.add("34020219710410689");
        strList.add("152526198707214777");
        strList.add("421126199005228725");
        strList.add("320281197302185262");
        strList.add("420111199705193847");
        strList.add("331100198707271859");
        strList.add("610721197606277094");
        strList.add("420116200411053360");
        strList.add("42011620041105892X");
        List<String> errorList = new LinkedList<>();
        List<String> resultList = new LinkedList<>();
        for (String idCardStr : strList) {
            Map<String, Object> map = analysisIdCard(idCardStr);
            if (Objects.equals("200", map.get("code"))) {
                System.out.println(map.get("code") + " ---- " + map.get("msg") + " ---- " + map.get("address") + " ---- " + map.get("sex") + " ---- " + map.get("birthday"));
                if (map.get("address").toString().indexOf("湖北省武汉市") != -1) {
                    resultList.add(idCardStr);
                }
            } else {
                System.out.println(map.get("code") + " ---- " + map.get("msg"));
                errorList.add(idCardStr);
            }
        }
        System.out.println("武汉人身份证号码 = " + resultList);
        System.out.println("错误身份证号码 = " + errorList);
    }
}
