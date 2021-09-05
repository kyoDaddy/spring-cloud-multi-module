package com.mall.orderservice.process.order.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter @Setter
@Entity
@Table(name = "orders")
// 직렬화를 목적은 객체 네트워크 전송하거나 데이터베이스 저장하기 위해서, 마샬링,언마샬링해주기 위해 사용하는 것
public class OrderEntity implements Serializable {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false, name = "ITEM_ID")
    private String itemId;

    @Column(nullable = false)
    private Integer qty;
    @Column(nullable = false)
    private Integer unitPrice;
    @Column(nullable = false)
    private Integer totalPrice;

    @Column(nullable = false)
    private String userId;
    @Column(nullable = false, unique = true)
    private String orderId;

    @Column(nullable = false, updatable = false, insertable = false)
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

}
