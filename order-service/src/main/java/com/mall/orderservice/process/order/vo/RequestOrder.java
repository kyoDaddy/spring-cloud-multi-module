package com.mall.orderservice.process.order.vo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RequestOrder {
    private String itemId;
    private Integer qty;
    private Integer unitPrice;
}
