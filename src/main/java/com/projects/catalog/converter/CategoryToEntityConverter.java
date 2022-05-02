package com.projects.catalog.converter;

import com.projects.catalog.entity.CategoryEntity;
import com.projects.catalog.model.Category;
import com.projects.catalog.repository.CategoryRepository;
import com.projects.catalog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryToEntityConverter implements Converter<Category, CategoryEntity> {

    private final ProductRepository productRepository;

    @Autowired
    public CategoryToEntityConverter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public CategoryEntity convert(Category source) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(source.getId());
        categoryEntity.setName(source.getName());
        categoryEntity.setParent(source.getParent());
        categoryEntity.setChildren(source.getChildren());
        if (source.getProducts() != null && !source.getProducts().isEmpty()) {
            categoryEntity.setProductEntityList(productRepository.findAllById(source.getProducts()));
        }
        return categoryEntity;
    }
}
