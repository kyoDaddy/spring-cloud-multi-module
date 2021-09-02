package com.mall.userservice.process.user.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
//값이 null 이면 response에서 뺀다.
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseUser {
    private String email;
    private String fullName;
    private String userId;

    private List<ResponseOrder> orders;
}
