package com.springboott.ttdemo.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

public class RandJsonTest {

    //读取json文件
    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Test
    public void main() {
        String json = readJsonFile("D:\\Desktop\\目标.json");
        JSONObject object = JSONObject.parseObject(json);
        System.out.println("object = " + object);
        String data = object.getString("data");
        JSONObject dataObj = JSONObject.parseObject(data);

        //随机数
        Random random = new Random();

        //型号
        List<String> brandList = Arrays.asList("Reno4Pro", "FindX2Pro", "Reno4", "Ace2", "A92s", "RenoAce", "A52", "FindX2", "Reno3Pro", "K5", "A11", "Reno3", "A91", "Reno4Pro", "A11x", "A5", "A8", "Reno2", "Ace2", "Reno", "Ace2", "FindX", "Reno2Z", "A92s", "A9", "Reno3", "K3", "Ace2", "Reno4", "Reno4Pro", "Reno", "K5", "FindX2", "RenoZ", "A11", "K1", "Reno", "A92s", "A9", "A7", "A11", "A9x", "Reno3Pro", "RenoAce", "Reno3", "A11", "Reno3Pro", "K5", "A8", "K3");
        int n1 = random.nextInt(brandList.size());

        //ADF6910374919639
        String serial = dataObj.getString("build.SERIAL");
        //7d327119acb924c0（随机更换其中3个数字或字母）
        String androidid = dataObj.getString("build.ANDROIDID");
        //todo 多替换
        String incremental = dataObj.getString("build.VERSION.INCREMENTAL");
        //(开头86后面的数字随机更换3个)
        String deviceid = dataObj.getString("phone.DeviceId");
        //(46000后面的数字随机更换0-1)
        String subscriberid = dataObj.getString("phone.SubscriberId");
        //(开头86后面的数字随机更换1个)
        String imei = dataObj.getString("phone.Imei");
        //(开头8后面的数字随机更换1个)
        String simserialnumber = dataObj.getString("phone.SimSerialNumber");
        //todo "00:00:46:C5:A0:C4"(随机替换其中3个数字和2个字母)
        String bluetoothmac = dataObj.getString("phone.BlueToothMAC");
        //todo"192.168.231.88"(168后面的231随机改变1-255的任意数字,88随机改变1-255中的任意数字)
        String wifiip = dataObj.getString("phone.WifiIP");
        //todo"04:e0:b0:86:51:16"(随机替换其中3个数字3个字母)
        String bssid = dataObj.getString("phone.BSSID");
        //"867581029730679"(开头86后面的数字随机更换4个)
        String imei2 = dataObj.getString("phone.Imei2");
        //todo"9c:21:6a:49:88:85,(随机替换其中3个数字2个字母)
        String wifi_macs = dataObj.getString("phone.wifi_macs");
        //"11010030309070473867581026521097"（110100303090后面的数字随机更换3个）
        String sdcardId = dataObj.getString("hardware.sdcardId");
        //268.9410095214844,(开头9后面的数字随机更换3个)
        String xdpi = dataObj.getString("hardware.xdpi");
        //268.6940002441406(开头6后面的数字随机更换3个)
        String ydpi = dataObj.getString("hardware.ydpi");

        dataObj.put("build.BRAND", brandList.get(n1));
        dataObj.put("build.VERSION.RELEASE", randomVersion());
        dataObj.put("build.SERIAL", randomNum("ADF6", serial, 3));
        dataObj.put("build.ANDROIDID", randomNumOrEng(null, androidid, 3));
        dataObj.put("build.VERSION.INCREMENTAL", randomNumOrEng(null, androidid, 3));
        dataObj.put("phone.DeviceId", randomNum("86", deviceid, 3));
        dataObj.put("phone.subscriberid", randomNum("46000", subscriberid, random.nextInt(2)));
        dataObj.put("phone.Imei", randomNum("86", imei, 1));
        dataObj.put("phone.SimSerialNumber", randomNum("8", simserialnumber, 1));
        //todo
//        dataObj.put("phone.BlueToothMAC", randomNum("8", bluetoothmac, 1));
//        dataObj.put("phone.WifiIP", randomNum("8", wifiip, 1));
//        dataObj.put("phone.BSSID", randomNum("8", bssid, 1));

        dataObj.put("phone.Imei2", randomNum("86", imei2, 4));
        //todo
//        dataObj.put("phone.wifi_macs", randomNum("86", wifi_macs, 4));

        dataObj.put("hardware.sdcardId", randomNum("110100303090", sdcardId, 3));
        dataObj.put("hardware.xdpi", randomNum("9", xdpi, 3));
        dataObj.put("hardware.ydpi", randomNum("6", ydpi, 3));

        //"phone.Line1Number":"17859832936"（真实手机号段内随机变换不限号段越多越好）
        //"phone.NetworkOperatorName":"中国移动"（移动，联通，电信最好根据上面的那个电话号段改变）
        //"phone.SimOperator":"46000","phone.SimOperatorName":"中国移动"（移动，联通，电信最好根据上面的那个电话号段改变）
        //随机生成手机号
        dataObj.put("phone.Line1Number", getTel());
        dataObj.put("phone.NetworkOperatorName", validateMobile(getTel()));
        dataObj.put("phone.SimOperator", getTel());
        object.put("data", dataObj);
        System.out.println("object = " + object);


    }

    @Test
    public void aaa() {
        for (int i = 0; i < 10; i++) {
            String s = randomNum("ADF6", "ADF6910374919639", 3);
            System.out.println(s);
        }
    }

    private String randomSplit(String pre, String str, String split, int size) {
        if (pre != null && pre.length() > 0) {
            str = str.substring(str.indexOf(pre) + pre.length());
        }
        Random random = new Random();
        String[] sts = str.split(split);
        String newStr = "";
        for (String st : sts) {
            int num = random.nextInt(size);
            newStr += num + split;
        }
        if (pre != null && pre.length() > 0) {
            return pre + newStr;
        }
        return newStr;
    }


    /**
     * 随机替换数字或英文
     *
     * @param pre  前缀
     * @param str  串
     * @param nums 替换个数
     * @return
     */
    private String randomNumOrEng(String pre, String str, int nums) {
        StringBuilder newStr = new StringBuilder(str);
        if (pre != null && pre.length() > 0) {
            newStr = new StringBuilder(str.substring(str.indexOf(pre) + pre.length()));
        }
        Random random = new Random();
        for (int i = 0; i < nums; i++) {
            //随机获取替换索引
            int repIndex = random.nextInt(newStr.length());
            //0 数字  1 字母
            String num = random.nextInt(10) + "";
            String zimu = (char) ((Math.random() * 26) + 97) + "";
            String s = random.nextInt(2) == 0 ? num : zimu;
            newStr.replace(repIndex, repIndex, s);
        }
        if (pre != null && pre.length() > 0) {
            return pre + newStr.toString();
        }
        return newStr.toString();
    }

    /**
     * 随机替换数字
     *
     * @param pre  前缀
     * @param str  串
     * @param nums 替换个数
     * @return
     */
    private String randomNum(String pre, String str, int nums) {
        StringBuilder newStr = new StringBuilder(str);
        if (pre != null && pre.length() > 0) {
            newStr = new StringBuilder(str.substring(str.indexOf(pre) + pre.length()));
        }
        Random random = new Random();
        for (int i = 0; i < nums; i++) {
            String num = random.nextInt(10) + "";//0-9的值
            int repIndex = random.nextInt(newStr.length());
            newStr.replace(repIndex, repIndex, num);
        }
        if (pre != null && pre.length() > 0) {
            return pre + newStr.toString();
        }
        return newStr.toString();
    }


    //10.1.1（第一位10可变换8.9.10,  第二位1可变0.1.2,第三位1可变 0.1.2.3）
    private String randomVersion() {
        Random random = new Random();
        String a = random.nextInt(3) + 8 + "";
        String b = random.nextInt(3) + "";
        String c = random.nextInt(4) + "";
        return a + "." + b + "." + c;
    }


    /**
     * 返回手机号码
     */
    private String[] telFirst = "134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");

    private int getNum(int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }

    public String getTel() {
        int index = getNum(0, telFirst.length - 1);
        String first = telFirst[index];
        String second = String.valueOf(getNum(1, 888) + 10000).substring(1);
        String third = String.valueOf(getNum(1, 9100) + 10000).substring(1);
        return first + second + third;
    }

    /**
     * 判断传入的参数号码为哪家运营商
     *
     * @param mobile
     * @return 运营商名称
     */
    public static String validateMobile(String mobile) {
        String returnString = "";
        if (mobile == null || mobile.trim().length() != 11) {
            return "-1";        //mobile参数为空或者手机号码长度不为11，错误！
        }
        if (mobile.trim().substring(0, 3).equals("134") || mobile.trim().substring(0, 3).equals("135") ||
                mobile.trim().substring(0, 3).equals("136") || mobile.trim().substring(0, 3).equals("137")
                || mobile.trim().substring(0, 3).equals("138") || mobile.trim().substring(0, 3).equals("139") || mobile.trim().substring(0, 3).equals("150") ||
                mobile.trim().substring(0, 3).equals("151") || mobile.trim().substring(0, 3).equals("152")
                || mobile.trim().substring(0, 3).equals("157") || mobile.trim().substring(0, 3).equals("158") || mobile.trim().substring(0, 3).equals("159")
                || mobile.trim().substring(0, 3).equals("187") || mobile.trim().substring(0, 3).equals("188")) {
            returnString = "1";    //中国移动
        }
        if (mobile.trim().substring(0, 3).equals("130") || mobile.trim().substring(0, 3).equals("131") ||
                mobile.trim().substring(0, 3).equals("132") || mobile.trim().substring(0, 3).equals("156")
                || mobile.trim().substring(0, 3).equals("185") || mobile.trim().substring(0, 3).equals("186")) {
            returnString = "2";    //中国联通
        }
        if (mobile.trim().substring(0, 3).equals("133") || mobile.trim().substring(0, 3).equals("153") ||
                mobile.trim().substring(0, 3).equals("180") || mobile.trim().substring(0, 3).equals("189")) {
            returnString = "3";    //中国电信
        }
        if (returnString.trim().equals("")) {
            returnString = "0";    //未知运营商
        }
        return returnString;
    }
}
