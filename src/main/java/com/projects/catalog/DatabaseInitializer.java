package com.projects.catalog;

import com.projects.catalog.entity.CategoryEntity;
import com.projects.catalog.entity.ItemEntity;
import com.projects.catalog.entity.ProductEntity;
import com.projects.catalog.repository.CategoryRepository;
import com.projects.catalog.repository.ItemRepository;
import com.projects.catalog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;

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
        ItemEntity i1 = createItemEntity("555551", new BigDecimal("24.95"), "S");
        ItemEntity i2 = createItemEntity("555552", new BigDecimal("24.95"), "M");
        ItemEntity i3 = createItemEntity("555553", new BigDecimal("24.95"), "L");
        ItemEntity i4 = createItemEntity("555554", new BigDecimal("24.95"), "XL");
        ItemEntity i5 = createItemEntity("555555", new BigDecimal("24.95"), "XXL");
        CategoryEntity root = categoryRepository.save(createCategoryEntity("Root category", null));
        CategoryEntity savedRoot = categoryRepository.save(root);
        CategoryEntity c1 = createCategoryEntity("Simple category", savedRoot.getId());
        ProductEntity product = createProductEntity(List.of(i1, i2, i3, i4, i5), c1);
        productRepository.save(product);
    }

    private static ItemEntity createItemEntity(String itemId, BigDecimal amount, String size) {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setItemId(itemId);
        itemEntity.setPrice(amount);
        itemEntity.setCurrency("EUR");
        itemEntity.setSize(size);
        return itemEntity;
    }

    private static ProductEntity createProductEntity(List<ItemEntity> items, CategoryEntity category) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductId("55555");
        productEntity.setDescription("Simple product");
        productEntity.setItemEntityList(items);
        items.forEach(i -> i.setProductEntity(productEntity));
        productEntity.setCategoryEntity(category);
        category.setProductEntityList(List.of(productEntity));
        return productEntity;
    }

    private static CategoryEntity createCategoryEntity(String name, Integer parent) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(name);
        categoryEntity.setParent(parent);
        return categoryEntity;
    }

}
