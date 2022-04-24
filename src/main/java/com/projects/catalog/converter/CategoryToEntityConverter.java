package com.projects.catalog.converter;

import com.projects.catalog.entity.CategoryEntity;
import com.projects.catalog.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CategoryToEntityConverter implements Converter<Category, CategoryEntity> {

    private final ConversionService conversionService;

    @Autowired
    public CategoryToEntityConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public CategoryEntity convert(Category source) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(source.getId());
        categoryEntity.setName(source.getName());
        categoryEntity.setParent(source.getParent());
        categoryEntity.setChildren(source.getChildren());
//        categoryEntity.setProductEntityList(source.getProductEntityList().stream()
//                .map(p -> conversionService.convert(p, ProductEntity.class))
//                .collect(Collectors.toList()));
        return categoryEntity;
    }
}
