package com.projects.catalog.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projects.catalog.model.Category;
import com.projects.catalog.service.CategoryService;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class CatalogQuery implements GraphQLQueryResolver {

    private final CategoryService categoryService;

    @Autowired
    public CatalogQuery(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public Category getCategory(Integer id) {
        return categoryService.getCategory(id);
    }

    public List<Category> getCategoryPath(Integer id) {
        return categoryService.getCategoryPath(id);
    }

//    public List<Category> getCategoryTree(Integer id) {
//        return categoryService.getCategoryTree(id);
//    }
}
