package com.projects.catalog.converter;

import com.projects.catalog.entity.ItemEntity;
import com.projects.catalog.model.Item;
import com.projects.catalog.util.MonetaryAmount;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EntityToItemConverter implements Converter<ItemEntity, Item> {

    @Override
    public Item convert(ItemEntity source) {
        return Item.builder()
                .id(source.getId())
                .itemId(source.getItemId())
                .price(new MonetaryAmount(source.getPrice(), source.getCurrency()))
                .size(source.getSize())
                .product(source.getProductEntity() != null
                        ? source.getProductEntity().getId()
                        : null)
                .build();
    }
}
