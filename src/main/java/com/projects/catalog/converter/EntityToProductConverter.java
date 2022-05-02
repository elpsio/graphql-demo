package com.projects.catalog.converter;

import com.google.common.collect.ImmutableList;
import com.projects.catalog.entity.CategoryEntity;
import com.projects.catalog.entity.ItemEntity;
import com.projects.catalog.entity.ProductEntity;
import com.projects.catalog.model.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class EntityToProductConverter implements Converter<ProductEntity, Product> {

    @Override
    public Product convert(ProductEntity source) {
        return Product.builder()
                .id(source.getId())
                .productId(source.getProductId())
                .description(source.getDescription())
                .items(source.getItemEntityList() != null
                        ? source.getItemEntityList().stream()
                        .map(ItemEntity::getId)
                        .collect(Collectors.toList())
                        : ImmutableList.of())
                .category(source.getCategoryEntity() != null
                        ? source.getCategoryEntity().getId()
                        : null)
                .build();
    }
}
