package com.springboott.ttdemo.util;

import com.baomidou.mybatisplus.annotation.TableName;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;

public class AnnotationUtils {

    /**
     * TableName注解值修改
     *
     * @param annonKey   注解字段（源）
     * @param annonValue 注解值（新）
     * @param clazz      类（哪个类上的）
     * @param <T>
     */
    public <T> void setTableNameValue(String annonKey, String annonValue, Class<T> clazz) {
        try {
            TableName annotation = clazz.getAnnotation(TableName.class);
            System.out.println("修改前：" + annotation.value());
            InvocationHandler invocationHandler = Proxy.getInvocationHandler(annotation);
            Field value = invocationHandler.getClass().getDeclaredField("memberValues");
            value.setAccessible(true);
            Map<String, Object> memberValues = (Map<String, Object>) value.get(invocationHandler);
            memberValues.put(annonKey, annonValue);
            System.out.println("修改后：" + annotation.value());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
