package com.projects.catalog.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.projects.catalog.model.Category;
import com.projects.catalog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class CatalogMutation implements GraphQLMutationResolver {

    private final CategoryService categoryService;

    @Autowired
    public CatalogMutation(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public Category createCategory(Category category) {
        return categoryService.createOrUpdateCategory(category);
    }

    public Category updateCategory(Category category) {
        return categoryService.createOrUpdateCategory(category);
    }


}
