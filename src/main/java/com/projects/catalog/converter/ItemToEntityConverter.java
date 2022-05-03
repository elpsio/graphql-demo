package com.projects.catalog.converter;

import com.projects.catalog.entity.ItemEntity;
import com.projects.catalog.model.Item;
import com.projects.catalog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ItemToEntityConverter implements Converter<Item, ItemEntity> {

    @Override
    public ItemEntity convert(Item source) {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setId(source.getId());
        itemEntity.setItemId(source.getItemId());
        itemEntity.setPrice(source.getPrice().amount());
        itemEntity.setCurrency(source.getPrice().currency());
        itemEntity.setSize(source.getSize());
        itemEntity.setProductId(source.getProduct());
        return itemEntity;
    }
}
