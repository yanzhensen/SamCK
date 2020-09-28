package com.springboott.ttdemo.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.springboott.ttdemo.po.User;
import com.springboott.ttdemo.po.UserClone;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class test {
    private final Logger logger = LoggerFactory.getLogger(test.class);

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

//        String a = DateUtils.dayAddSub("2019-9-19",30);
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
//        String date1 = "2019-08-30";
//        String date2 = "2020-02-29";
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Calendar date = Calendar.getInstance();
//        Date dt1 = sdf.parse(date2);
//        date.setTime(dt1);
//        date.add(Calendar.DAY_OF_MONTH, 1);
//        System.out.println(sdf.format(date.getTime()));
//        date2 = DateUtils.dayAddSub(date2,1);
//        Period period = Period.between(LocalDate.parse(date1), LocalDate.parse(sdf.format(date.getTime())));
//        Period period = Period.between(LocalDate.parse(date1), LocalDate.parse(date2));
//        StringBuffer sb = new StringBuffer();
//        sb.append(period.getYears()).append(",")
//                .append(period.getMonths()).append(",")
//                .append(period.getDays());
//        System.out.println(sb.toString());

        //DateUtils.getCyclesAndDays(date1,date2,1);

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

//        System.out.println(DateUtils.getCurrentDateSecond());

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

        /*System.out.println("System.getProperty(\"user.dir\") = " + System.getProperty("user.dir"));
        System.out.println("StringUtils.isEmpty(\"\") = " + StringUtils.isEmpty(""));
        System.out.println("StringUtils.isEmpty(\" \") = " + StringUtils.isEmpty(" "));
        System.out.println("StringUtils.isBlank(\"\") = " + StringUtils.isBlank(""));
        System.out.println("StringUtils.isBlank(\" \") = " + StringUtils.isBlank(" "));*/
        /*JSONObject js = new JSONObject();
        js.put("toUserId","1");
        js.put("toUserType","face");
        js.put("wxOpenid","");
        js.put("scene","4");
        js.put("firstValue","尊敬的****");
        js.put("keyValue1","张三");//姓名
        js.put("keyValue2",date1);//时间
        js.put("keyValue3","已到校");
        js.put("remarkValue","感谢您的关注");
        Map<String, String> mapSm = new HashMap<String, String>();
        mapSm.put("co", String.valueOf(1));
        mapSm.put("coId", String.valueOf(2));
        mapSm.put("jtm", JSON.toJSONString(js));
        System.out.println("mapSm = " + mapSm);*/
        /*
        List<User> list = new ArrayList<>();
        if(list!=null&&list.size()>0){
            System.out.println("list.size() = " + list.size());
        }

        String err = "failure remote invoke: {\"code\":\"WO_EXP-120\",\"msg\":\"cardEnable type is undefined\",\"result\":0}";
        System.out.println("err.indexOf(\"{\") = " + err.indexOf("{"));
        System.out.println("err.indexOf(\"}\") = " + err.indexOf("}"));
        String errstr = err.substring(err.indexOf("{"),err.indexOf("}")+1);
        System.out.println(errstr);
        Integer maxInteger = 2147483647;
        */

        //获取当前时间格式化
//        String nowdate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        System.out.println("nowdate = " + nowdate);

        /*
        String fo ="2019-11-06 18:52:52";
        LocalDateTime localDateTime = LocalDateTime.parse(fo,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("localDateTime = " + localDateTime);
        System.out.println(localDateTime.toLocalDate()+" "+localDateTime.toLocalTime());;
        ApiAssert.isTrue(ErrorCodeEnum.NOT_FOUND,true);
        System.out.println("LocalDateTime.now() = " + LocalDateTime.now());
        System.out.println("LocalDateTimeUtils.formatNow(\"yyyy-MM-dd HH:mm:ss\") = " + LocalDateTimeUtils.formatNow("yyyy-MM-dd HH:mm:ss"));
        */

        /*String jsonA = "[{'number':'xn143','jcdId':716,'jcdHouseAddress':'田贝新村 81栋 0703'}" +
                ",{'number':'xn144','jcdId':716,'jcdHouseAddress':'田贝新村 81栋 0703'}]";
        JSONArray jsonArray = JSONArray.parseArray(jsonA);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject js = jsonArray.getJSONObject(i);
            System.out.println("js.getString(\"number\") = " + js.getString("number"));
        }*/

        /*System.out.println(LocalDate.now());
        System.out.println(LocalDate.now().plusDays(40));
        //1<2
        if (LocalDate.now().isBefore(LocalDate.now().plusDays(40))) {
            System.out.println("before: true");
        }
        if (LocalDate.now().isAfter(LocalDate.now().plusDays(40))) {
            System.out.println("after: true");
        }
        if (LocalDate.now().isEqual(LocalDate.now())) {
            System.out.println("now: true");
        }*/

        /*String randomNumber = String.valueOf((Math.random() * 1000000));
        System.out.println("randomNumber = " + randomNumber);
        int r1 = (int) (Math.random() * 1000000);
        System.out.println("r1 = " + r1);*/

//        Map<String, String> map1 = ApiUtils.getEntityType(AllTypeEntity.class);
//        System.out.println("map1 = " + map1);
       /* DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String s = "2020-02-17";
        LocalDate date = LocalDate.parse(s);
        System.out.println("date = " + date);
        String sss = "2020-02-17 09:05:18";
//        String sss = "2019-04-03 9:32:16";//报错
        LocalDateTime dateTime = LocalDateTime.parse(sss, df);
        System.out.println("dateTime = " + dateTime);
        StringBuilder sb3 = new StringBuilder();
        StringBuilder sd5 = new StringBuilder();
        System.out.println("sb = " + sb3);
        System.out.println("Objects.isNull(sb) = " + Objects.isNull(sb3));
        System.out.println("StringUtils.isEmpty(sb) = " + StringUtils.isEmpty(sb3.toString()));
        sb3.append("241");
        sd5.append("gvhd" + sb3);
        System.out.println("sd = " + sd5.deleteCharAt(sd5.length()-1));*/


//        System.out.println((int)(Math.random()*9*10000000)+"");
    }

    @Test
    public void uuu() {
        //线上路径
        String p1 = System.getProperty("user.dir") + "/shield/ShieldingWords.txt";
        String p2 = System.getProperty("user.dir") + "/src/main/resources/ShieldingWords.txt";
        String p3 = "./src/main/resources/ShieldingWords.txt";
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        String str = "{\"jsonVal\":[{\"agent\":\"\",\"endDate\":\"2021-01-07\",\"contractNo\":\"EleCon20200411142138792\",\"business_license_number\":\"74321654642\",\"signingDate\":\"2020-04-11\",\"rentalAddress\":\"深圳市 罗湖区 智能声控公寓 A座 1701\",\"rentType\":\"月付\",\"deliveryDay\":\"2020-10-08\",\"remark\":\"\",\"Truce1\":\"1\",\"Truce2\":\"0\",\"company_address\":\"测试公寓6栋66号\",\"Signer1\":\"396ba0e0f09d410ea00ddfcc1b314db1\",\"Signer2\":\"b6836a5a64432d97220e3675dfebdfe3\",\"capitalAmount\":\"陆仟陆佰元整\",\"email\":\"\",\"squareMeter\":100,\"serviceFee\":\"100\",\"gasNum\":0,\"renant\":\"测试测试2\",\"houseType\":\"2房2厅1卫\",\"lowercaseAmount\":\"6600\",\"electricityNum\":0,\"company_tel\":\"15012341565\",\"beginDate\":\"2020-10-08\",\"waterNum\":0,\"mailingAddress\":\"\",\"idcard\":\"45378618468410\",\"telphone\":\"13058040157\",\"company_name\":\"演示公寓旗舰版\",\"salesman\":\"管理员\",\"paymentDate\":11}],\"insertData\":{\"jrrPaymentMethod\":\"月付\",\"jcdId\":178,\"laId\":245,\"advanceMode\":\"1\",\"renterId\":393,\"jrrServerPayment\":\"月付\",\"houseDeposit\":\"6500\",\"jrrTypeOfContract\":\"2\",\"jrrMoney\":\"6600\",\"jrrSignedTime\":\"2020-04-11\",\"numberMode\":\"1\",\"jrrTheTerm\":\"0年3月0日\",\"jrrServerCost\":\"100\",\"jrrBeginTime\":\"2020-10-08\",\"jrrContractType\":\"续签合同\",\"jrrTheContract\":\"续签\",\"jrrRentalType\":\"正常\",\"jcdContractPrefix\":\"EleCon\",\"jrrEndTime\":\"2021-01-07\",\"jcdContractNumber\":\"20200411142138792\",\"userId\":\"4\",\"jrrManagePayment\":\"月付\",\"jrrManageCost\":\"100\",\"hrId\":549,\"jrrInAdvancePay\":11,\"jrrRenewalCoding\":\"A004\",\"hsId\":1540,\"jcdHouseAddress\":\"智能声控公寓 A座 1701\"}}";
        JSONObject object = JSONObject.parseObject(str);
        String string = object.getJSONArray("jsonVal").getJSONObject(0).getString("contractNo");
        System.out.println("string = " + string);


    }

    @Test
    public void publicTest() throws Exception {
        String str = "{'water':{'lastReading':'82.0','thisReading':[]},'electrit':{'lastReading':'3900.0','thisReading':[]},'gas':{'lastReading':'0.0','thisReading':[]},'hotwater':{'lastReading':'37.1','thisReading':[]},'hotair':{'lastReading':'0.0','thisReading':[]}}";
        JSONObject object = JSONObject.parseObject(str);
        String string = object.getJSONObject("water").getString("lastReading");
        System.out.println("string = " + string);

//        LocalDate jrrBe = LocalDate.parse("2020-08-29");
//        LocalDate jrrEn = LocalDate.parse("2021-02-28");
        LocalDate jrrBe = LocalDate.parse("2020-08-28");
        LocalDate jrrEn = LocalDate.parse("2021-02-27");
        int[] rs = new int[3];
        rs[0] = jrrBe.until(jrrEn).getYears();
        rs[1] = jrrBe.until(jrrEn).getMonths();
        rs[2] = jrrBe.until(jrrEn).getDays();
        for (int r : rs) {
            System.out.println(r);
        }
        Period per = Period.between(jrrBe, jrrEn.plusDays(1));
        System.out.println("per = " + per);

    }

    @Test
    public void getLocalAllDay() {
        //获取这个月所有的日期
        String month = "2020-05";
        LocalDate start = LocalDate.of(Integer.parseInt(month.split("-")[0]), Integer.parseInt(month.split("-")[1]), 1);
//        LocalDate start = LocalDate.parse(month, DateTimeFormatter.ofPattern("yyyy-MM"));
        LocalDate end = start.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("end = " + end);
        //iterate从start开始迭代 每次++1天 limit限制条数 31条  map里面做了localDate转换string Collectors.toList()转List集合
        List<String> dates = Stream.iterate(start, date -> date.plusDays(1))
                .limit(ChronoUnit.DAYS.between(start, end.plusDays(1))).map(e -> e.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .collect(Collectors.toList());
        System.out.println("dates = " + dates);
        System.out.println("start.lengthOfMonth() = " + start.lengthOfMonth());
    }

    @Test
    public void Weedken() {
        //获取该年的周六日
        int year = 2020;
        List<String> dateList = new ArrayList<String>();
        SimpleDateFormat simdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = new GregorianCalendar(year, 0, 1);
        int i = 1;
        while (calendar.get(Calendar.YEAR) < year + 1) {
            calendar.set(Calendar.WEEK_OF_YEAR, i++);
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            if (calendar.get(Calendar.YEAR) == year) {
                System.out.println("周日：" + simdf.format(calendar.getTime()));
                dateList.add(simdf.format(calendar.getTime()));
            }
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
            if (calendar.get(Calendar.YEAR) == year) {
                System.out.println("周六：" + simdf.format(calendar.getTime()));
                dateList.add(simdf.format(calendar.getTime()));
            }
        }
    }


    @Test
    public void ChekcNull() {
        User user = new User();
        System.out.println("user = " + Objects.isNull(user));
        System.out.println(LocalDate.parse("1111-11-11"));
        System.out.println(LocalDateTime.parse("1111-11-11 11:11:11", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println(false && false);

        user.setAge(20);
        User b = new User();
        BeanUtils.copyProperties(user, b);
        System.out.println(" = " + user.equals(b));
        b.setUsername("222");
        b.setAge(30);
        System.out.println("a = " + user);
        System.out.println("b = " + b);
        System.out.println(" = " + user.equals(b));

        LocalDateTime dateTime = LocalDateTime.parse("2020-04-22 15:56:58", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("dateTime = " + dateTime);
    }

    @Test
    public void MultiValueMapTest() {
        MultiValueMap<String, String> stringMultiValueMap = new LinkedMultiValueMap<>();
        stringMultiValueMap.add("早班 9:00-11:00", "周一");
        stringMultiValueMap.add("早班 9:00-11:00", "周二");
        stringMultiValueMap.add("中班 13:00-16:00", "周三");
        stringMultiValueMap.add("早班 9:00-11:00", "周四");
        stringMultiValueMap.add("测试1天2次 09:00 - 12:00", "周五");
        stringMultiValueMap.add("测试1天2次 09:00 - 12:00", "周六");
        stringMultiValueMap.add("中班 13:00-16:00", "周日");
        stringMultiValueMap.set("早班 9:00-11:00", "123");
        //打印所有值
        Set<String> keySet = stringMultiValueMap.keySet();
        for (String key : keySet) {
            List<String> values = stringMultiValueMap.get(key);
            System.out.println(StringUtils.join(values.toArray(), " ") + ":" + key);

        }
    }

    @Test
    public void ba() {
        StringBuilder messageNote = new StringBuilder();
        as(messageNote);
        System.out.println("messageNote = " + messageNote);
        System.out.println(Double.parseDouble("0.00"));
        //System.out.println(Double.parseDouble(null));报错 NullPointerException
        System.out.println(Math.abs(200) > (1e-6));
        System.out.println(0.1 <= (1e-6));//0.000001
        System.out.println(-0.000001 >= (-1e-6));//-0.000001
    }

    public void as(StringBuilder messageNote) {
        messageNote.append("1351235");
    }

    //list地址引用
    @Test
    public void ListTe() {
        User u1 = new User();
        u1.setUsername("aa1");
        u1.setAge(50);
        u1.setAddress("广东深圳");
        User u2 = new User();
        u2 = u1;
        u2.setUsername("aa2");
        u2.setAge(20);
        System.out.println("u1 = " + u1);
        System.out.println("u2 = " + u2);
    }

    //对象地址引用
    @Test
    public void objectTe() {
        User u1 = new User();
        u1.setUsername("aa1");
        u1.setAge(50);
        u1.setAddress("广东深圳");
        User u2 = u1;
        u2.setUsername("aa2");
        u2.setAge(20);
        System.out.println("u1 = " + u1);
        System.out.println("u2 = " + u2);
        //u1 = User(id=null, username=aa2, password=null, age=20, telephone=null, address=广东深圳, remark=null, otherUser=null)
        //u2 = User(id=null, username=aa2, password=null, age=20, telephone=null, address=广东深圳, remark=null, otherUser=null)

        //解决办法
        User u3 = new User();
        u3.setUsername("aa1");
        u3.setAge(50);
        u3.setAddress("广东深圳");
        User u4 = new User();
        BeanUtils.copyProperties(u3, u4);
        u4.setUsername("aa2");
        u4.setAge(20);
        System.out.println("u3 = " + u3);
        System.out.println("u4 = " + u4);
        int n = 10;
        for (int i = 0; i < n; i++) {
            if (i == 9) {
                n++;
            }
        }
        System.out.println("n = " + n);
    }

    @Test
    public void cloneTe() {
        try {
            UserClone userA = new UserClone();
            UserClone userC = new UserClone();
            userC.setUsername("Lucy");
            UserClone userD = userC;
            userA.setUsername("petter");
            UserClone userB = (UserClone) userA.clone();
            System.out.println(userA == userB);
            System.out.println(userC == userD);
            System.out.println(userB);//Clone A 与 B 是两个独立的对象
            System.out.println(userD);// 而普通的复制 就直接指向有的内存
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
