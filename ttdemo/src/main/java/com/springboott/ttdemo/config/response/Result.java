package com.springboott.ttdemo.config.response;

import java.io.Serializable;

public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer code;
    private String msg;
    private T body;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public Result() {
    }

    public Result(Integer code, String msg, T body) {
        this.setCode(code);
        this.setBody(body);
        this.setMsg(msg);
    }

    @Override
    public String toString() {
        return "Result [code=" + code + ", msg=" + msg + ", body=" + body + "]";
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static Result success() {
        return new Result(200, "成功", null);
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static Result success(String msg, Object obj) {
        return new Result(200, msg, obj);
    }


}
