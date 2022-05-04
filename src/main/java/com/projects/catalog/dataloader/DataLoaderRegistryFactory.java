package com.projects.catalog.dataloader;

import com.projects.catalog.model.Item;
import com.projects.catalog.repository.ItemRepository;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderFactory;
import org.dataloader.DataLoaderRegistry;
import org.dataloader.MappedBatchLoader;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
public class DataLoaderRegistryFactory {

    public static final String ITEM_DATA_LOADER = "ITEM_DATA_LOADER";

    private final ItemRepository itemRepository;
    private final ConversionService conversionService;

    public DataLoaderRegistryFactory(ItemRepository itemRepository,
                                     ConversionService conversionService) {
        this.itemRepository = itemRepository;
        this.conversionService = conversionService;
    }

    public DataLoaderRegistry create() {
        DataLoaderRegistry dataLoaderRegistry = new DataLoaderRegistry();
        dataLoaderRegistry.register(ITEM_DATA_LOADER, createItemDataLoader());
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

}
