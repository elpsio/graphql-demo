package com.projects.catalog.service;

import com.projects.catalog.entity.ItemEntity;
import com.projects.catalog.model.Item;
import com.projects.catalog.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ConversionService conversionService;

    @Autowired
    public ItemService(ItemRepository itemRepository, ConversionService conversionService) {
        this.itemRepository = itemRepository;
        this.conversionService = conversionService;
    }


    public Item createOrUpdateItem(Item item) {
        ItemEntity itemEntity = conversionService.convert(item, ItemEntity.class);
        return conversionService.convert(itemRepository.save(Objects.requireNonNull(itemEntity)), Item.class);
    }

    public Item getItem(Integer id) {
        return conversionService.convert(itemRepository.getById(id), Item.class);
    }

    public List<Item> getItems() {
        return itemRepository.findAll().stream()
                .map(p -> conversionService.convert(p, Item.class))
                .collect(Collectors.toList());
    }

}
