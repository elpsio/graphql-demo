package com.projects.catalog.converter;

import com.projects.catalog.entity.ProductEntity;
import com.projects.catalog.model.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EntityToProductConverter implements Converter<ProductEntity, Product> {

    @Override
    public Product convert(ProductEntity source) {
        return Product.builder()
                .id(source.getId())
                .productId(source.getProductId())
                .description(source.getDescription())
                .items(source.getItemIds())
                .category(source.getCategoryId())
                .build();
    }
}
