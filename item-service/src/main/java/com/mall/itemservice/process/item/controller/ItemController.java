package com.mall.itemservice.process.item.controller;

import com.mall.common.utils.ObjectMapperUtils;
import com.mall.itemservice.config.exception.ApiException;
import com.mall.itemservice.config.exception.ExceptionEnum;
import com.mall.itemservice.process.item.entity.ItemEntity;
import com.mall.itemservice.process.item.service.ItemService;
import com.mall.itemservice.process.item.vo.ResponseItem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Api(tags = {"상품 관련"})
public class ItemController {

    private final Environment env;
    private final ItemService itemService;

    @GetMapping("/health-check")
    public String status(HttpServletRequest request) {
        return String.format("It's Working in Catalog Service, port(local.server.port) --> %s, port(server.port) --> %s", env.getProperty("local.server.port"), env.getProperty("server.port"));
    }

    @GetMapping("/welcome")
    public String welcome() {
        return env.getProperty("server.application.name");
    }


    @GetMapping(value = "/items", produces = { "application/hal+json" })
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "상품 목록", notes = "<big>상품 전체 목록</big>을 반환한다.")
    public CollectionModel<ResponseItem> getItems() {

        Collection<ResponseItem> collections = new ArrayList<>();
        for (ItemEntity itemEntity : itemService.getAllItems()) {
            Link selfLink = linkTo(methodOn(this.getClass()).getItems())
                    .slash(itemEntity.getId())
                    .withSelfRel();

            ResponseItem item = ObjectMapperUtils.map(itemEntity, ResponseItem.class).add(selfLink);
            collections.add(item);
        }

        CollectionModel<ResponseItem> model = CollectionModel.of(collections);
        model.add(linkTo(methodOn(this.getClass()).getItems()).withSelfRel());
        return model;
    }

    @GetMapping(value = "/items/{itemId}", produces = { "application/hal+json" })
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "상품 정보", notes = "상품 한 건에 대한 정보이다.")
    public EntityModel<ResponseItem> getItem(@PathVariable("itemId") Long itemId) {

        Optional<ItemEntity> itemEntity = itemService.getItem(itemId);
        if (itemEntity.isEmpty()) {
            throw new ApiException(ExceptionEnum.NOT_FOUND_EXCEPTION_01);
        }

        ResponseItem item = ObjectMapperUtils.map(itemEntity.get(), ResponseItem.class);

        EntityModel<ResponseItem> model = EntityModel.of(item);
        model.add(linkTo(methodOn(this.getClass()).getItem(itemId)).withSelfRel());
        return model;
    }





}
