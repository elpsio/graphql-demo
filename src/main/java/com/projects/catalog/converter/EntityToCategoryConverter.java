package com.projects.catalog.converter;

import com.projects.catalog.entity.CategoryEntity;
import com.projects.catalog.model.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EntityToCategoryConverter implements Converter<CategoryEntity, Category> {

    @Override
    public Category convert(CategoryEntity source) {
        return Category.builder()
                .id(source.getId())
                .name(source.getName())
                .parent(source.getParent())
                .products(source.getProductIds())
                .build();
    }
}
