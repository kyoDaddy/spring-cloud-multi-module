package com.mall.userservice.process.user.client;

import com.mall.userservice.config.base.FeignResponseDecoderConfig;
import com.mall.userservice.process.user.vo.ResponseOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 1. feign-client 기본 메소드는 public
 * 2. 커스텀 옵션 설정가능
 *      -> @FeignClient(name="ORDER-SERVICE", configuration = FeignErrorDecoder.class)
 */
@FeignClient(name = "ORDER-SERVICE", configuration = FeignResponseDecoderConfig.class)
public interface OrderServiceClient {

    @GetMapping("/{userId}/orders2")
    List<ResponseOrder> getOrder(@PathVariable(value = "userId") String userId);

}

