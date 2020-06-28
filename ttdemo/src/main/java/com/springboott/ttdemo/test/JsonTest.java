package com.springboott.ttdemo.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.springboott.ttdemo.po.AllTypeEntity;
import com.springboott.ttdemo.util.JacksonUtils;
import com.springboott.ttdemo.util.LocalDateTimeUtils;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Json数据处理
 */
public class JsonTest {

    @Test
    public void test1() {
//        String str2 = "{\"integerBigValue\":70,\"isdh\":379,\"localDateTimeValue\":\"2020-01-03 16:48:00\"}";//无此字段不会映射
//        AllTypeEntity entity1 = JacksonUtils.readValue(str2, AllTypeEntity.class);
//        System.out.println("entity1 = " + entity1);
//        String str3 = "{\"integerBigValue\":70,\"isdh\":379,\"intSmValue\":[\"1\",\"2\",\"19\"]}";//错误字段对应直接返回null
//        AllTypeEntity entity2 = JacksonUtils.readValue(str3, AllTypeEntity.class);
//        System.out.println("entity2 = " + entity2);
//        String str1 = "[{\"intSmValue\":30,\"longSmValue\":40,\"floatSmValue\":50.0,\"doubleSmValue\":60.00,\"integerBigValue\":30,\"longBigValue\":40,\"floatBigValue\":50.0,\"doubleBigValue\":60.00,\"stringOfValue\":\"尽快\",\"localDateTimeOfValue\":\"2020-01-07 16:48:00\"}]";
//        List<AllTypeEntity> list = JacksonUtils.readValue(str1, new TypeReference<List<AllTypeEntity>>(){});//arrayList映射
//        System.out.println("list = " + list);
//        String str4 = "[{\"byteSmValue\":10,\"booleanSmValue\":true,\"shortSmValue\":20,\"charSmValue\":\"helloWorld\",\"intSmValue\":30,\"longSmValue\":40,\"floatSmValue\":50.0,\"doubleSmValue\":60.00,\"byteBigValue\":10,\"booleanBigValue\":true,\"shortBigValue\":20,\"characterBigValue\":\"helloWorld\",\"integerBigValue\":30,\"longBigValue\":40,\"floatBigValue\":50.0,\"doubleBigValue\":60.00,\"stringOfValue\":\"尽快\",\"dateOfValue\":\"2020-01-08 16:48:00\",\"timestampOfValue\":\"1578477277\",\"localDateOfValue\":\"2020-01-08\",\"localDateTimeOfValue\":\"2020-01-07 16:48:00\"}]";
//        List<AllTypeEntity> arrayList = JacksonUtils.readValue(str4, new TypeReference<ArrayList<AllTypeEntity>>(){});
//        System.out.println("arrayList = " + arrayList);
        String str5 = "{\"dateOfValue\":\"2020-09-03\",\"localDateOfValue\":\"2020-09-03\",\"localDateTimeOfValue\":\"2020-01-07 16:48:00\"}";
        AllTypeEntity entity3 = JacksonUtils.readValue(str5, AllTypeEntity.class);
        System.out.println("entity3 = " + entity3);
        String a = "{\"auditStatus\":\"待付款\",\"auditName\":\"管理员\",\"accountPayable\":null,\"debt\":0,\"actualPayment\":null,\"landAccountName\":\"小恐龙\"" +
                ",\"landAccountBank\":\"中国平安银行\",\"landAccountNum\":\"53151381831\",\"reviewName\":\"管理员\",\"operationTime\":\"2020-12-12-12:12:11\"}";
        String b = "";
        JSONObject o = JSON.parseObject(a, Feature.OrderedField);
        o.put("auditStatus", "123");
        o.put("auditStatus", "465");
        o.put("operationTime", LocalDateTimeUtils.getCurrentTime());
        System.out.println(o);
    }

    @Test
    public void test2() {
        String str = "\"{\"path\":\"http://pic-gongkai.fangzhizun.com/Fi8M4wSZ2FroX6dmh8jX7WkSHIIj7721.png\",\"name\":\"aaa.png\"}\"";
//        String str = "{\"path\":\"http://pic-gongkai.fangzhizun.com/Fu-WZbzCO5inJ_KpH0lD-M25CD0Z3505.jpg\",\"name\":\"5b207f27b7e0e.jpg\"}";
        System.out.println("str = " + str.indexOf("{"));
        System.out.println("str = " + str.lastIndexOf("}"));
        System.out.println("str = " + (str.length() - 1));
        System.out.println("old = " + str);
        str = str.substring(str.indexOf("{"), str.lastIndexOf("}") + 1);
        System.out.println("new = " + str);
        JSONArray jsonArray = JSON.parseArray("[" + str + "]");
        System.out.println("jsonArray = " + jsonArray.getJSONObject(0).getString("path"));
    }

    @Test
    public void test3() {
        String str = "\"{\"path\":\"http://pic-gongkai.fangzhizun.com/Fi8M4wSZ2FroX6dmh8jX7WkSHIIj7721.png\"}," +
                "{\"path\":\"\",\"name\":\"aaa.png\"},{\"path\":\"http://pic-gongkai.fangzhizun.com/Fi8M4wSZ2FroX6dmh8jX7WkSHIIj7721.png\",\"name\":\"aaa.png\"}\"";
        str = str.substring(str.indexOf("{"), str.lastIndexOf("}") + 1);
        System.out.println("new = " + str);
        JSONArray jsonArray = JSON.parseArray("[" + str + "]");
        for (int i = 0; i < jsonArray.size(); i++) {
            String path = jsonArray.getJSONObject(i).getString("path");
            String name = jsonArray.getJSONObject(i).getString("name");
            if(StringUtils.isEmpty(path)){
                continue;
            }
            System.out.println("jsonArray = " + path);
            System.out.println("jsonArray = " + (StringUtils.isNotEmpty(name) ? name : "IMG_" + UUID.randomUUID().toString().substring(0, 8)));
        }

    }
}
