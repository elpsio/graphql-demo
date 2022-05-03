package com.projects.catalog.converter;

import com.projects.catalog.entity.CategoryEntity;
import com.projects.catalog.model.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryToEntityConverter implements Converter<Category, CategoryEntity> {

    @Override
    public CategoryEntity convert(Category source) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(source.getId());
        categoryEntity.setName(source.getName());
        categoryEntity.setParent(source.getParent());
        categoryEntity.setProductIds(source.getProducts());
        return categoryEntity;
    }
}
