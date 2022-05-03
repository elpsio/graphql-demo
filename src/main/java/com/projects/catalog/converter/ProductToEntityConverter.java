package com.projects.catalog.converter;

import com.projects.catalog.entity.ProductEntity;
import com.projects.catalog.model.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductToEntityConverter implements Converter<Product, ProductEntity> {

    @Override
    public ProductEntity convert(Product source) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(source.getId());
        productEntity.setProductId(source.getProductId());
        productEntity.setDescription(source.getDescription());
        productEntity.setCategoryId(source.getCategory());
        productEntity.setItemIds(source.getItems());
        return productEntity;
    }
}
