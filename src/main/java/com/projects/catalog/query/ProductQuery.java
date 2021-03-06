package com.projects.catalog.query;

import com.projects.catalog.model.Product;
import com.projects.catalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class ProductQuery implements GraphQLQueryResolver {

    private final ProductService productService;

    @Autowired
    public ProductQuery(ProductService productService) {
        this.productService = productService;
    }

    public Product getProduct(Integer id) {
        return productService.getProduct(id);
    }

    public List<Product> getProducts() {
        return productService.getProducts();
    }

    public List<Product> getLimitedProducts(int limit) {
        return productService.getProducts(limit);
    }

}
