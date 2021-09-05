package com.mall.itemservice.process.item.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
//값이 null 이면 response에서 뺀다.
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseItem extends RepresentationModel<ResponseItem> {

    private String itemId;
    private String itemName;

    private int stockQuantity;
    private int unitPrice;

    private LocalDateTime createdAt;

}
