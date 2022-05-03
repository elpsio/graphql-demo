package com.projects.catalog.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.projects.catalog.model.Item;
import com.projects.catalog.model.Product;
import com.projects.catalog.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductItemResolver implements GraphQLResolver<Product> {

    private final ItemRepository itemRepository;
    private final ConversionService conversionService;

    @Autowired
    public ProductItemResolver(ItemRepository itemRepository, ConversionService conversionService) {
        this.itemRepository = itemRepository;
        this.conversionService = conversionService;
    }

    public List<Item> items(Product product) {
        return itemRepository.findAllById(product.getItems()).stream()
                .map(i -> conversionService.convert(i, Item.class))
                .collect(Collectors.toList());
    }

}
