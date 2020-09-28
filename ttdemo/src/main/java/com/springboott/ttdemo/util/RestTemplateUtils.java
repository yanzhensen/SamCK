package com.springboott.ttdemo.util;

import com.alibaba.fastjson.JSON;
import com.springboott.ttdemo.common.enums.ErrorCodeEnum;
import com.springboott.ttdemo.common.exception.ApiAssert;
import com.springboott.ttdemo.common.exception.ApiException;
import com.springboott.ttdemo.common.exception.CustomRestClientException;
import com.springboott.ttdemo.common.response.CustomResponseErrorHandler;
import com.springboott.ttdemo.common.spring.ApplicationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Component
public class RestTemplateUtils {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 不进行加密解密的项目路径
     */
    private static final String[] INGORE_PATH = {};


    /**
     * get请求
     *
     * @param uri 访问路径
     * @param map 路径参数
     * @return
     */
    public String get(String uri, @Nullable MultiValueMap<String, String> map) {
        return get(uri, map, false);
    }

    /**
     * get请求 是否需要token
     *
     * @param uri       访问路径
     * @param map       路径参数
     * @param needToken 是否是要toekn
     * @return
     */
    public String get(String uri, @Nullable MultiValueMap<String, String> map, boolean needToken) {
        //追加uri的参数
        uri = appendUriParameter(uri, map);
        //发送请求
        String responseBody = doExchange(uri, HttpMethod.GET, null, needToken);
        //响应体解密
        return responseBody;
    }

    /**
     * post请求
     *
     * @param uri         访问路径
     * @param map         路径参数
     * @param requestBody
     * @return
     */
    public String post(String uri, @Nullable MultiValueMap<String, String> map, @Nullable Object requestBody) {
        return post(uri, map, requestBody, false);
    }

    /**
     * post请求 是否需要token
     *
     * @param uri         访问路径
     * @param map         路径参数
     * @param requestBody
     * @param needToken
     * @return
     */
    public String post(String uri, @Nullable MultiValueMap<String, String> map, @Nullable Object requestBody, boolean needToken) {
        //追加uri的参数
        uri = appendUriParameter(uri, map);
        //请求体加密 requestBody
        //发送请求
        String responseBody = doExchange(uri, HttpMethod.POST, requestBody, needToken);
        //响应体解密 responseBody
        return responseBody;
    }

    /**
     * put请求
     *
     * @param uri         访问路径
     * @param map         路径参数
     * @param requestBody
     * @return
     */
    public String put(String uri, @Nullable MultiValueMap<String, String> map, @Nullable Object requestBody) {
        return put(uri, map, requestBody, false);
    }

    /**
     * put请求 是否需要token
     *
     * @param uri         访问路径
     * @param map         路径参数
     * @param requestBody
     * @param needToken
     * @return
     */
    public String put(String uri, @Nullable MultiValueMap<String, String> map, @Nullable Object requestBody, boolean needToken) {
        //追加uri的参数
        uri = appendUriParameter(uri, map);
        //请求体加密 requestBody
        //发送请求
        String responseBody = doExchange(uri, HttpMethod.PUT, requestBody, needToken);
        //响应体解密 responseBody
        return responseBody;
    }

    /**
     * delete请求
     *
     * @param uri         访问路径
     * @param map         路径参数
     * @param requestBody
     * @return
     */
    public String delete(String uri, @Nullable MultiValueMap<String, String> map, @Nullable Object requestBody) {
        return delete(uri, map, requestBody, false);
    }

    /**
     * delete请求 是否需要token
     *
     * @param uri         访问路径
     * @param map         路径参数
     * @param requestBody
     * @param needToken
     * @return
     */
    public String delete(String uri, @Nullable MultiValueMap<String, String> map, @Nullable Object requestBody, boolean needToken) {
        //追加uri的参数
        uri = appendUriParameter(uri, map);
        //请求体加密 requestBody
        //发送请求
        String responseBody = doExchange(uri, HttpMethod.DELETE, requestBody, needToken);
        //响应体解密 responseBody
        return responseBody;
    }


    /**
     * 发送请求
     *
     * @param url       访问路径
     * @param method    请求方式
     * @param body      请求体
     * @param needToken 是否需要token
     * @return
     */
    public String doExchange(String url, HttpMethod method, @Nullable Object body, boolean needToken) {
        //设置请求头媒体类型为application/json
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (needToken) {
            headers.set("Authorization", ApplicationUtils.getRequest().getHeader("Authorization"));
        }
        //发送请求
        ResponseEntity<String> exchange = null;
        try {
            restTemplate.setErrorHandler(new CustomResponseErrorHandler());
            exchange = restTemplate.exchange(url, method, new HttpEntity<>(body, headers), String.class);
        } catch (CustomRestClientException e) {
            System.out.println("e.getBody = " + e.getBody());
            System.out.println("msg = " + JSON.parseObject(e.getBody()).getString("msg"));
            System.out.println("e.getRestClientException() = " + e.getRestClientException());
            //日志 / 异常处理
        }
        return exchange.getBody();
    }

    /**
     * 追加uri的参数
     *
     * @param uri 访问路径
     * @param map 路径参数
     * @return
     */
    private String appendUriParameter(String uri, MultiValueMap<String, String> map) {
        return map != null ? UriComponentsBuilder.fromUriString(uri).queryParams(map).toUriString() : uri;
    }


}
