package com.springboott.ttdemo.test;

import com.springboott.ttdemo.po.User;
import com.springboott.ttdemo.po.UserClone;
import io.swagger.models.auth.In;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jdt.internal.compiler.batch.FileSystem;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

public class test {
    public static void main(String[] args) throws ParseException {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
//        Date d = format.parse(new Date().toString());
//        System.out.println(d);
//        String formatDate =format.getDateInstance().format(new Date());
//        System.out.println(formatDate);
//        System.out.println(new Date().getTime());

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String formatDate =sdf.getDateInstance().format(new Date());
//        Date startDate = sdf.parse(formatDate);
//        Date endDate = sdf.parse("2019-08-03");
//        long betweenDate = (endDate.getTime() - startDate.getTime())/(60*60*24*1000);
//        System.out.println(betweenDate);
//        SimpleDateFormat oldFormatter = new SimpleDateFormat("yyyy-MM-dd");
//        Date date1 = new Date();
//        System.out.println(oldFormatter.format(date1));
//
//        //JAVA8 后
//        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate date2 = LocalDate.now();
//        System.out.println(date2.format(newFormatter));

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Calendar date = Calendar.getInstance();
//        date.add(Calendar.DATE, 5);
//        System.out.println(date.getTime());
//        date.add(2019-5-27, 5);//sdf.parse("2019-5-24")
//        System.out.println(sdf.format(date.getTime()));

//        String a = DateUtil.dayAddSub("2019-9-19",30);
//        System.out.println(a);
//        for(int i = 0; i<5; i++){
//            for(int j = 0; j<6; j++){
//                if(i == 3){
//                    if(j==4){
//                        System.out.println("j=4的时候");
//                        continue;
//                    }
//                    System.out.println(i+" "+j+" 1");
//                }
//                System.out.println("2");
//            }
//            System.out.println("3");
//        }
        //assert断言 需要修改一下 run as 里面的VM_Option 加个-ea
//        String s = null;
//        boolean b = s!=null?true:false;
//        assert b :"返回数据为空";
//        System.out.println("end");
        String date1 = "2019-08-30";
        String date2 = "2020-02-29";
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Calendar date = Calendar.getInstance();
//        Date dt1 = sdf.parse(date2);
//        date.setTime(dt1);
//        date.add(Calendar.DAY_OF_MONTH, 1);
//        System.out.println(sdf.format(date.getTime()));
//        date2 = DateUtil.dayAddSub(date2,1);
//        Period period = Period.between(LocalDate.parse(date1), LocalDate.parse(sdf.format(date.getTime())));
//        Period period = Period.between(LocalDate.parse(date1), LocalDate.parse(date2));
//        StringBuffer sb = new StringBuffer();
//        sb.append(period.getYears()).append(",")
//                .append(period.getMonths()).append(",")
//                .append(period.getDays());
//        System.out.println(sb.toString());

        //DateUtil.getCyclesAndDays(date1,date2,1);

//        Integer a = 10;
//        Integer b = 10;
//        Integer c = new Integer(10);
//        System.out.println(a==b);//true
//        System.out.println(a==c.intValue());//true
//        System.out.println(b==c);//false

        //set去重
//        Set s = new HashSet();
//        s.add(1);
//        s.add(2);
//        s.add(3);
//        s.add(4);
//        s.add(1);
//        s.add(1);
//        s.add(2);
//        System.out.println(s);
//        for (Iterator iterator = s.iterator(); iterator.hasNext(); ) {
//            Object next =  iterator.next();
//            System.out.println(next);
//        }

//        try{
//            Map<String,String> map1 = new HashMap<>();
//            map1.put("key",null);
//            String aa1 = map1.get("key");
//            String bb1 = null;
//            if(aa1.equals(bb1)){
//                System.out.println("123");
//            }else {
//                System.out.println("456");
//            }
//        }catch (Exception e){
//            System.out.println("789");
//            e.printStackTrace();
//        }

//        System.out.println(DateUtil.getCurrentDateSecond());

        //随机数
//        System.out.println((int)(Math.random()*100));
//        StringBuffer sb= new StringBuffer();
//        for (int i = 0; i < 9; i++) {
//            sb.append((int)(Math.random()*10));
//        }
//        System.out.println(sb);
//        System.out.println((int)(Math.random()*9*100000000));

        //lambda表达式
//        new Thread(()-> {
//            for (int i = 0; i < 10; i++) {
//                System.out.println(i + "helloWorld");
//            }
//        }).start();

//        byte vr1 = 0;//-128到127
//        for (int i = 0; i < 128; i++) {
//            vr1++;
//        }
//        System.out.println(vr1);
//        int b = 456;
//        byte vr2 = (byte) b;
//        System.out.println(vr2);

//        String text1 = "hello[123] 你好啊";
//        String text2 = text1.replace("[123]","123");
//        System.out.println(text2);

        /*try {
            UserClone userA = new UserClone();
            UserClone userC = new UserClone();
            userC.setUsername("Lucy");
            UserClone userD = userC;
            userA.setUsername("petter");
            UserClone userB = (UserClone) userA.clone();
            System.out.println(userA==userB);
            System.out.println(userC==userD);
            System.out.println(userB);//Clone A 与 B 是两个独立的对象
            System.out.println(userD);// 而普通的复制 就直接指向有的内存
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }*/
        /*String jstr = "\"{\"path\":\"http://pic-gongkai.fangzhizun.com/FuQ39_G_Zk6FKVHfi65mhyptClOb9242.jpg\",\"name\":\"0fefcd97e8417fd1a82e205b38031afa.jpg\"}" +
                ",{\"path\":\"http://pic-gongkai.fangzhizun.com/Fkvorv7dqj7KZW-dAH4jSZ79Yu6P5536.jpg\",\"name\":\"5d5bbda02283b.jpg\"}\"";
        String jstr2 = "{\"path\":\"http://pic-gongkai.fangzhizun.com/FuQ39_G_Zk6FKVHfi65mhyptClOb9242.jpg\",\"name\":\"0fefcd97e8417fd1a82e205b38031afa.jpg\"}" +
                ",{\"path\":\"http://pic-gongkai.fangzhizun.com/Fkvorv7dqj7KZW-dAH4jSZ79Yu6P5536.jpg\",\"name\":\"5d5bbda02283b.jpg\"}";
//        jstr=jstr2;
        System.out.println(jstr);
        int a = jstr.indexOf("\"");
        System.out.println(a);
        if(a==0){
            jstr = jstr.substring(1,jstr.length()-1);
            System.out.println("123"+jstr);
        }
        String sss = "["+ jstr + "]";
        System.out.println(jstr);
        JSONArray jay = JSONArray.fromObject(sss);
        String img = jay.getString(0);
//        Object a =jay.get(0);
        System.out.println(img);

        List list = new ArrayList();
        ArrayList arrayList = new ArrayList();
        arrayList.trimToSize();
        System.out.println(arrayList);*/
//        int[] ids = new int[] {1,3,6,9};
//        for (int id : ids) {
//            System.out.println(id);
//        }
//        String str = "[1,2,3]";
//        String[] ssd = str.substring(1,str.length()-1).split(",");
//        for (String s : ssd) {
//            System.out.println(s);
//        }
        /*System.out.println(LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE));
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));*/
        System.out.println("System.getProperty(\"user.dir\") = " + System.getProperty("user.dir"));
        System.out.println("StringUtils.isEmpty(\"\") = " + StringUtils.isEmpty(""));
        System.out.println("StringUtils.isEmpty(\" \") = " + StringUtils.isEmpty(" "));
        System.out.println("StringUtils.isBlank(\"\") = " + StringUtils.isBlank(""));
        System.out.println("StringUtils.isBlank(\" \") = " + StringUtils.isBlank(" "));

    }
}
