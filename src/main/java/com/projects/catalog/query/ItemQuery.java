package com.projects.catalog.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projects.catalog.model.Item;
import com.projects.catalog.service.ItemService;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class ItemQuery implements GraphQLQueryResolver {

    private final ItemService itemService;

    @Autowired
    public ItemQuery(ItemService itemService) {
        this.itemService = itemService;
    }

    public Item getItem(Integer id) {
        return itemService.getItem(id);
    }

    public List<Item> getItems() {
        return itemService.getItems();
    }

}
