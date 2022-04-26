package com.projects.catalog.service;

import com.projects.catalog.entity.ProductEntity;
import com.projects.catalog.model.Product;
import com.projects.catalog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ConversionService conversionService;

    @Autowired
    public ProductService(ProductRepository productRepository, ConversionService conversionService) {
        this.productRepository = productRepository;
        this.conversionService = conversionService;
    }


    public Product createOrUpdateProduct(Product product) {
        ProductEntity productEntity = conversionService.convert(product, ProductEntity.class);
        return conversionService.convert(productRepository.save(Objects.requireNonNull(productEntity)), Product.class);
    }

    public Product getProduct(Integer id) {
        return conversionService.convert(productRepository.getById(id), Product.class);
    }

    public List<Product> getProducts() {
        return productRepository.findAll().stream()
                .map(p -> conversionService.convert(p, Product.class))
                .collect(Collectors.toList());
    }

}
