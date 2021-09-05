package com.mall.orderservice.process.order.service;

import com.mall.common.utils.ObjectMapperUtils;
import com.mall.orderservice.process.order.dto.OrderDto;
import com.mall.orderservice.process.order.entity.OrderEntity;
import com.mall.orderservice.process.order.repository.OrderRepository;
import com.mall.orderservice.process.order.vo.RequestOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderDto createOrder(OrderDto orderDto) {
        orderDto.setOrderId(UUID.randomUUID().toString());
        orderDto.setTotalPrice(orderDto.getQty() * orderDto.getUnitPrice());

        OrderEntity orderEntity = ObjectMapperUtils.map(orderDto, OrderEntity.class);
        OrderEntity save = orderRepository.save(orderEntity);

        return ObjectMapperUtils.map(save, OrderDto.class);
    }

    public OrderDto getOrderByOrderId(String orderId) {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        return ObjectMapperUtils.map(orderEntity, OrderDto.class);
    }

    public Iterable<OrderEntity> getOrdersByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }

}
