package com.mall.orderservice.process.order.controller;

import com.mall.common.utils.ObjectMapperUtils;
import com.mall.orderservice.process.order.dto.OrderDto;
import com.mall.orderservice.process.order.entity.OrderEntity;
import com.mall.orderservice.process.order.service.OrderService;
import com.mall.orderservice.process.order.vo.RequestOrder;
import com.mall.orderservice.process.order.vo.ResponseOrder;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/")
@Slf4j
@RequiredArgsConstructor
public class OrderController {

    private final Environment env;
    private final OrderService orderService;

    @GetMapping("/health_check")
    public String status() {
        return "It's Working in Order Service"
                + ", port(local.server.port)=" + env.getProperty("local.server.port")
                + ", port(server.port)=" + env.getProperty("server.port");
    }

    @GetMapping("/welcome")
    public String welcome() {
        return env.getProperty("server.application.name");
    }


    /**TODO 주문 (주문 상태로 생성 후 -> 상품 재고 수 차감)
     *
     */
    @PostMapping(value = "/{userId}/orders", produces = { "application/hal+json" })
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "사용자 주문")
    public EntityModel<ResponseOrder> createOrder(@PathVariable("userId") String userId,
                                                  @RequestBody RequestOrder requestOrder) {

        OrderDto orderDto = ObjectMapperUtils.map(requestOrder, OrderDto.class);
        orderDto.setUserId(userId);

        OrderDto createDto = orderService.createOrder(orderDto);
        ResponseOrder returnValue = ObjectMapperUtils.map(createDto, ResponseOrder.class);

        EntityModel<ResponseOrder> model = EntityModel.of(returnValue);
        model.add(linkTo(methodOn(this.getClass()).createOrder(userId, requestOrder)).withSelfRel());
        return model;
    }


    /**TODO 주문 취소 (주문 취소 상태로 변경 후 -> 상품 재고 수 원복)
    */
    /*
    @PostMapping("/{userId}/cancel")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "사용자 주문 취소")
    public Mono<ResponseOrderCancel> cancelOrder(@PathVariable("userId") String userId,
                                           @RequestBody RequestOrderCancel requestOrderCancel) {
        return orderService.cancelOrder(requestOrderCancel);
    }
    */


    // 사용자별 주문 이력
    @GetMapping(value = "/{userId}/orders", produces = { "application/hal+json" })
    public CollectionModel<ResponseOrder> getOrder(@PathVariable("userId") String userId) {
        Collection<ResponseOrder> collections = new ArrayList<>();
        for (OrderEntity orderEntity : orderService.getOrdersByUserId(userId)) {
            Link selfLink = linkTo(methodOn(this.getClass()).getOrder(userId))
                    .slash(orderEntity.getItemId())
                    .withSelfRel();

            ResponseOrder item = ObjectMapperUtils.map(orderEntity, ResponseOrder.class).add(selfLink);
            collections.add(item);
        }

        log.info("=========> input");

        CollectionModel<ResponseOrder> model = CollectionModel.of(collections);
        model.add(linkTo(methodOn(this.getClass()).getOrder(userId)).withSelfRel());
        return model;
    }

    // 사용자별 주문 이력
    @GetMapping(value = "/{userId}/orders2", produces = { "application/json" })
    public List<ResponseOrder> getOrders(@PathVariable("userId") String userId) {
        List<ResponseOrder> list = new ArrayList<>();
        Iterable<OrderEntity> orderLists = orderService.getOrdersByUserId(userId);
        for (OrderEntity orderEntity : orderLists) {
            list.add(ObjectMapperUtils.map(orderEntity, ResponseOrder.class));
        }
        log.info("=========> input");
        return list;
    }


    /**TODO 주문번호별 상세 이력
     */




}
