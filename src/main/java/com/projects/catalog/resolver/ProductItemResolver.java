package com.projects.catalog.resolver;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.dataloader.DataLoader;
import org.springframework.stereotype.Component;

import com.projects.catalog.dataloader.DataLoaderRegistryFactory;
import com.projects.catalog.model.Item;
import com.projects.catalog.model.Product;

import graphql.kickstart.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;

@Component
public class ProductItemResolver implements GraphQLResolver<Product> {

  public CompletableFuture<List<Item>> items(Product product, DataFetchingEnvironment dfe) {

    DataLoader<Integer, List<Item>> dataLoader = dfe.getDataLoaderRegistry()
        .getDataLoader(DataLoaderRegistryFactory.ITEM_DATA_LOADER);

    return dataLoader.load(product.getId());
  }



}
