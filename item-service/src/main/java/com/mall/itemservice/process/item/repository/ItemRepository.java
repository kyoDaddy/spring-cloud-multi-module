package com.mall.itemservice.process.item.repository;

import com.mall.itemservice.process.item.entity.ItemEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<ItemEntity, Long> {

    ItemEntity findById(String id);

}
