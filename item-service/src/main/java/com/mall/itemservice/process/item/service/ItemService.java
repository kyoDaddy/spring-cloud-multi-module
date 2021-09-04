package com.mall.itemservice.process.item.service;

import com.mall.itemservice.process.item.entity.ItemEntity;
import com.mall.itemservice.process.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public Iterable<ItemEntity> getAllItems() {
        return itemRepository.findAll();
    }

    public Optional<ItemEntity> getItem(Long itemId) { return itemRepository.findById(itemId); }
}
