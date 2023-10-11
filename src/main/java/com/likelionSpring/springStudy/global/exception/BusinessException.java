package com.likelionSpring.springStudy.global.exception;

//부모타입이 RunTimeException
public class BusinessException extends RuntimeException{
    public BusinessException(String message) {
        super(message);
    }
}
