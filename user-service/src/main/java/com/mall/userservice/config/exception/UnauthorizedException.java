package com.mall.userservice.config.exception;

import org.springframework.http.HttpStatus;

/**
 * 스프링 부트에서 공통 에러 처리
 * 2. 일반적으로 사용하는 에러클래스는 GlobalException을 상속받아 만들어주고 필요시 아래처럼 throw 처리한다.
 *
 *      try {
 *          // something
 *      } catch (Exception e) {
 *          log.error("JWT ERROR", e);
 *          throw new UnauthorizedException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.ERROR_NO_AUTH, e);
 *      }
 *
 */
public class UnauthorizedException extends GlobalException{

    private static final long serialVersionUID = -1L;

    public UnauthorizedException(HttpStatus status) {
        super(status);
    }

    public UnauthorizedException(HttpStatus status, String message) {
        super(status, message);
    }

    public UnauthorizedException(HttpStatus status, String message, Throwable e) {
        super(status, message, e);
    }
}
