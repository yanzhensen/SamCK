package com.springboott.ttdemo.common.exception;

import org.springframework.web.client.RestClientException;

public class CustomRestClientException extends RestClientException {

    private RestClientException restClientException;

    private String body;

    public RestClientException getRestClientException() {
        return restClientException;
    }

    public void setRestClientException(RestClientException restClientException) {
        this.restClientException = restClientException;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public CustomRestClientException(String msg, RestClientException restClientException, String body) {
        super(msg);
        this.restClientException = restClientException;
        this.body = body;
    }

}
