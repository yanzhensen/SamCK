package com.springboott.ttdemo.exception;

/**
 * <p>
 * CustomException异常类
 * </p>
 *
 * @author Caratacus
 */
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CustomException(String message) {
        super(message);
    }

    public CustomException(Throwable throwable) {
        super(throwable);
    }

    public CustomException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
