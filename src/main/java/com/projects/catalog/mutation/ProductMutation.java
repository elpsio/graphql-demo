package com.projects.catalog.mutation;

import com.projects.catalog.model.Product;
import com.projects.catalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLMutationResolver;

@Component
public class ProductMutation implements GraphQLMutationResolver {

    private final ProductService productService;

    @Autowired
    public ProductMutation(ProductService productService) {
        this.productService = productService;
    }

    public Product createProduct(Product product) {
        return productService.createOrUpdateProduct(product);
    }

    public Product updateProduct(Product product) {
        return productService.createOrUpdateProduct(product);
    }

}
