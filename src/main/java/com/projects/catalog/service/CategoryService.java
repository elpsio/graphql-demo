package com.projects.catalog.service;

import com.projects.catalog.entity.CategoryEntity;
import com.projects.catalog.model.Category;
import com.projects.catalog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import graphql.com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ConversionService conversionService;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, ConversionService conversionService) {
        this.categoryRepository = categoryRepository;
        this.conversionService = conversionService;
    }

    public Category createOrUpdateCategory(Category category) {
        CategoryEntity categoryEntity = conversionService.convert(category, CategoryEntity.class);
        return conversionService.convert(categoryRepository.save(Objects.requireNonNull(categoryEntity)), Category.class);
    }

    public Category getCategory(Integer id) {
        return conversionService.convert(categoryRepository.getById(id), Category.class);
    }

    public List<Category> getCategoryPath(Integer id) {
        List<Category> categories = categoryRepository.findAll().stream()
                .map(c -> conversionService.convert(c, Category.class))
                .collect(Collectors.toList());

        Optional<Category> root = categories.stream()
                .filter(c -> Objects.equals(id, c.getId()))
                .findFirst();

        List<Category> list = new ArrayList<>();
        return root.map(r -> {
                    Category category = conversionService.convert(categoryRepository.getById(r.getId()), Category.class);
                    if (category != null) {
                        list.add(category);
                        while (category != null && category.getParent() != null) {
                            category = conversionService.convert(categoryRepository.getById(category.getParent()), Category.class);
                            if (category != null) {
                                list.add(category);
                            }
                        }
                    }
                    return list;
                })
                .orElse(ImmutableList.of());
    }

}
