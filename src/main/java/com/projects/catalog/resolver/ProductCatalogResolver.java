package com.projects.catalog.resolver;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.dataloader.DataLoader;
import org.springframework.stereotype.Component;

import com.projects.catalog.dataloader.DataLoaderRegistryFactory;
import com.projects.catalog.model.Category;
import com.projects.catalog.model.Product;

import graphql.kickstart.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;

@Component
public class ProductCatalogResolver implements GraphQLResolver<Product> {

  public CompletableFuture<Category> category(Product product, DataFetchingEnvironment dfe) {

    DataLoader<Integer, Category> dataLoader = dfe.getDataLoaderRegistry()
        .getDataLoader(DataLoaderRegistryFactory.CATEGORY_DATA_LOADER);

    return dataLoader.load(product.getCategory());
  }

}
