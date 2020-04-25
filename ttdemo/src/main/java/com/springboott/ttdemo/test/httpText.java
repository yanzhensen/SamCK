package com.springboott.ttdemo.test;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.http.HttpEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用httpClient模拟发送http Post请求
 */
public class httpText {
    private static HttpClient httpClient = new DefaultHttpClient();
    private static HttpPost httppost;
    private static HttpGet httpget;
    private static HttpResponse response;
    private HttpEntity entity;
    private String postResult = null;

    @Test
    public void t2(){
        String loginURL = "https://www.sojson.com/open/api/lunar/json.shtml";
        final String userAgent = "Mozilla/5.0(Windows NT 10.0; Win64; x64) AppleWebKit/53a7.36 (KHTML, like Gecko) Chrome/69.0.3497.92 Safari/537.36";
        // 创建一个httpget请求
//        URI uri = new URIBuilder(loginURL).setParameter("date","2019-09-13").build();
//        httpget = new HttpGet(uri);
        httpget = new HttpGet(loginURL);
        try {
//            List<NameValuePair> list = new ArrayList<>();
//            BasicNameValuePair bv = new BasicNameValuePair("date","2019-09-13");
//            list.add(bv);
            httpget.setHeader("User-Agent",userAgent);
            response = httpClient.execute(httpget);
            String strResult = EntityUtils.toString(response.getEntity());
            JSONObject object = JSONObject.parseObject(strResult);
            String status = object.getString("status");
            System.out.println(object.getString("status"));
            if(status.equals("200")){
                System.out.println("查看返回的结果：" + strResult);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        httpget.releaseConnection();
    }
    @Test
    public void t1(){
        String loginURL = "http://localhost:8082/jiekou/wxgzh/renterCompanyVar";
        // 创建一个httppost请求
        httppost = new HttpPost(loginURL);
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("company", "qjgy");
//        jsonParam.put("password","e10adc3949ba59abbe56e057f20f883e");
        try {
            System.out.println(jsonParam.toString());
//            StringEntity entity = new StringEntity("company='qjgy'", "utf-8");// 解决中文乱码问题
            //post传值  后台使用request.getParameter就能获取
            List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
            BasicNameValuePair b1 = new BasicNameValuePair("company","qjgy");
//            BasicNameValuePair b2 = new BasicNameValuePair("hrId","5");
            list.add(b1);
//            list.add(b2);
            UrlEncodedFormEntity enti = new UrlEncodedFormEntity(list);
            httppost.setEntity(enti);
            System.out.println(enti);
//            entity.setContentEncoding("UTF-8");
//            entity.setContentType("application/x-www-form-urlencoded");
//            entity.setContentType("application/json");
//            httppost.setEntity(entity);
            response = httpClient.execute(httppost);
            String strResult = EntityUtils.toString(response.getEntity());
            System.out.println("查看返回的结果：" + strResult);

        } catch (Exception e) {
            e.printStackTrace();
        }

        httppost.releaseConnection();
    }
    public static void main(String[] args) {

    }

}
