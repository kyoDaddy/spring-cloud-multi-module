package com.mall.userservice.config.exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;

/**
 * 스프링 부트에서 공통 에러 처리
 * 3. DefaultErrorAttributes 는 스프링이 자동으로 만들어내는 에러를 담고 있으며, 해당 클래스를 통해 확장처리를 할 수 있다.
 *      @RequestParam 등으로 처리되는 필수체크 등은 스프링 에러에 내용이 담기지 않는다.
 *      해당 클래스를 통해 throwable 의 내용을 담아 사용자에게 메시지를 제대로 전달한다.
 *      그리고 사용자 정의 에러일 경우는 GlobalException 을 통해 따로 처리된다.
 *
 */
@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Map<String, Object> map = super.getErrorAttributes(request, options);

        Throwable throwable = getError(request);
        if (throwable instanceof GlobalException) {
            GlobalException ex = (GlobalException) getError(request);
            map.put("exception", ex.getClass().getSimpleName());
            map.put("message", ex.getMessage());
            map.put("status", ex.getStatus().value());
            map.put("error", ex.getStatus().getReasonPhrase());
            return map;
        }

        map.put("exception", "SystemException");
        map.put("message", throwable.getMessage());
        return map;
    }

}
