package com.projects.catalog.converter;

import com.projects.catalog.entity.ProductEntity;
import com.projects.catalog.model.Product;
import com.projects.catalog.repository.CategoryRepository;
import com.projects.catalog.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductToEntityConverter implements Converter<Product, ProductEntity> {

    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public ProductToEntityConverter(CategoryRepository categoryRepository, ItemRepository itemRepository) {
        this.categoryRepository = categoryRepository;
        this.itemRepository = itemRepository;
    }


    @Override
    public ProductEntity convert(Product source) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(source.getId());
        productEntity.setProductId(source.getProductId());
        productEntity.setDescription(source.getDescription());
        if (source.getCategory() != null) {
            productEntity.setCategoryEntity(categoryRepository.getById(source.getCategory()));
        }
        if (source.getItems() != null && !source.getItems().isEmpty()) {
            productEntity.setItemEntityList(itemRepository.findAllById(source.getItems()));
        }
        return productEntity;
    }
}
