package com.projects.catalog.dataloader;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.dataloader.DataLoader;
import org.dataloader.DataLoaderFactory;
import org.dataloader.DataLoaderRegistry;
import org.dataloader.MappedBatchLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import com.projects.catalog.model.Category;
import com.projects.catalog.model.Item;
import com.projects.catalog.repository.CategoryRepository;
import com.projects.catalog.repository.ItemRepository;

@Component
public class DataLoaderRegistryFactory {

  public static final String ITEM_DATA_LOADER = "ITEM_DATA_LOADER";
  public static final String CATEGORY_DATA_LOADER = "CATEGORY_DATA_LOADER";

  private final ItemRepository itemRepository;
  private final CategoryRepository categoryRepository;
  private final ConversionService conversionService;

  @Autowired
  public DataLoaderRegistryFactory(ItemRepository itemRepository,
      CategoryRepository categoryRepository, ConversionService conversionService) {
    this.itemRepository = itemRepository;
    this.categoryRepository = categoryRepository;
    this.conversionService = conversionService;
  }

  public DataLoaderRegistry create() {
    DataLoaderRegistry dataLoaderRegistry = new DataLoaderRegistry();
    dataLoaderRegistry.register(ITEM_DATA_LOADER, createItemDataLoader());
    dataLoaderRegistry.register(CATEGORY_DATA_LOADER, createCategoryDataLoader());
    return dataLoaderRegistry;
  }

  private DataLoader<Integer, List<Item>> createItemDataLoader() {
    MappedBatchLoader<Integer, List<Item>> itemMappedBatchLoader =
        productIds -> CompletableFuture.supplyAsync(
            () -> itemRepository.findAllByProductIdIn(productIds).stream()
                .map(i -> conversionService.convert(i, Item.class))
                .collect(Collectors.groupingBy(Item::getId)));

    return DataLoaderFactory.newMappedDataLoader(itemMappedBatchLoader);
  }

  private DataLoader<Integer, Category> createCategoryDataLoader() {
    MappedBatchLoader<Integer, Category> categoryMappedBatchLoader =
        categoryIds -> CompletableFuture.supplyAsync(
            () -> categoryRepository.findAllById(categoryIds).stream()
                .map(c -> conversionService.convert(c, Category.class))
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(Category::getId, Function.identity())));

    return DataLoaderFactory.newMappedDataLoader(categoryMappedBatchLoader);
  }

}
