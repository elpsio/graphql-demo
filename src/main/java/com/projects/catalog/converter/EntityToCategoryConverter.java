package com.projects.catalog.converter;

import com.google.common.collect.ImmutableList;
import com.projects.catalog.entity.CategoryEntity;
import com.projects.catalog.entity.ProductEntity;
import com.projects.catalog.model.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class EntityToCategoryConverter implements Converter<CategoryEntity, Category> {

    @Override
    public Category convert(CategoryEntity source) {
        return Category.builder()
                .id(source.getId())
                .name(source.getName())
                .parent(source.getParent())
                .children(source.getChildren())
                .products(source.getProductEntityList() != null
                        ? source.getProductEntityList().stream()
                        .map(ProductEntity::getId)
                        .collect(Collectors.toList())
                        : ImmutableList.of())
                .build();
    }
}
