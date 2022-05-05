package com.projects.catalog;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projects.catalog.entity.CategoryEntity;
import com.projects.catalog.entity.ItemEntity;
import com.projects.catalog.entity.ProductEntity;
import com.projects.catalog.repository.CategoryRepository;
import com.projects.catalog.repository.ItemRepository;
import com.projects.catalog.repository.ProductRepository;

import graphql.com.google.common.collect.ImmutableList;

@Component
public class DatabaseInitializer {

    private final ItemRepository itemRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public DatabaseInitializer(ItemRepository itemRepository, ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.itemRepository = itemRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @PostConstruct
    public void init() {
//        List<ProductEntity> productEntities = new ArrayList<>();
//        for (int i = 0; i < 30; i++) {
//            productEntities.add(createProduct(i + 1, 5));
//        }
//        createCategories(productEntities, 3);
    }

    private void createCategories(List<ProductEntity> productEntities, int itemsPerCategory) {
        CategoryEntity root = categoryRepository.save(createCategoryEntity(ImmutableList.of(), "Root category", null));
        CategoryEntity savedRoot = categoryRepository.save(root);

        for (int i = 0; i + itemsPerCategory - 1 < productEntities.size(); i = i + itemsPerCategory) {
            List<Integer> productIds = new ArrayList<>();
            List<ProductEntity> productsInCategory = new ArrayList<>();
            for (int j = 0; j < itemsPerCategory; j++) {
                productIds.add(productEntities.get(i + j).getId());
                productsInCategory.add(productEntities.get(i + j));
            }
            CategoryEntity category = createCategoryEntity(productIds, "Simple category", savedRoot.getId());
            CategoryEntity savedCategory = categoryRepository.save(category);
            productsInCategory.forEach(p -> p.setCategoryId(savedCategory.getId()));
            productRepository.saveAll(productsInCategory);
        }
    }

    private ProductEntity createProduct(int productId, int amountOfItems) {
        //init items
        List<String> sizes = ImmutableList.of("S", "M", "L", "XL", "XXL");
        List<BigDecimal> prices = ImmutableList.of(
                new BigDecimal("19.95"),
                new BigDecimal("20.95"),
                new BigDecimal("21.95"),
                new BigDecimal("22.95"),
                new BigDecimal("23.95"));

        if (amountOfItems > 5) {
            throw new IllegalStateException("Max 5 items per product supported");
        }


        List<ItemEntity> items = new ArrayList<>();
        for (int i = 0; i < amountOfItems - 1; i++) {
            String itemId = productId + String.valueOf(i);
            ItemEntity itemEntity = createItemEntity(itemId, prices.get(i), sizes.get(i));
            items.add(itemEntity);
        }
        List<ItemEntity> savedItems = itemRepository.saveAll(items);
        List<Integer> savedItemIds = savedItems.stream()
                .map(ItemEntity::getId)
            .collect(Collectors.toList());

        //init product
        ProductEntity product = createProductEntity(productId, savedItemIds);
        ProductEntity savedProduct = productRepository.save(product);

        //update item product relation
        savedItems.forEach(i -> i.setProductId(savedProduct.getId()));
        itemRepository.saveAll(items);

        return savedProduct;
    }

    private static ItemEntity createItemEntity(String itemId, BigDecimal amount, String size) {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setItemId(itemId);
        itemEntity.setPrice(amount);
        itemEntity.setCurrency("EUR");
        itemEntity.setSize(size);
        return itemEntity;
    }

    private static ProductEntity createProductEntity(int productId, List<Integer> itemIds) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductId(String.valueOf(productId));
        productEntity.setDescription("Simple product");
        productEntity.setItemIds(itemIds);
        return productEntity;
    }

    private static CategoryEntity createCategoryEntity(List<Integer> productIds, String name, Integer parent) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(name);
        categoryEntity.setParent(parent);
        categoryEntity.setProductIds(productIds);
        return categoryEntity;
    }

}
