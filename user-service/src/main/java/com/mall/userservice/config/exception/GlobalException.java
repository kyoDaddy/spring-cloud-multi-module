package com.mall.userservice.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * 스프링 부트에서 공통 에러 처리
 * 1. ResponseStatusException을 상속하여 공통 처리를 받을 수 있는 에러처리 클래스를 만들어 준다.
 */
public class GlobalException extends ResponseStatusException {

    public GlobalException(HttpStatus status) {
        super(status);
    }

    public GlobalException(HttpStatus status, String message) {
        super(status, message);
    }

    public GlobalException(HttpStatus status, String message, Throwable e) {
        super(status, message, e);
    }

}
