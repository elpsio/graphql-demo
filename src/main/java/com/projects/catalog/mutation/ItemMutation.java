package com.projects.catalog.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.projects.catalog.model.Item;
import com.projects.catalog.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ItemMutation implements GraphQLMutationResolver {

    private final ItemService itemService;

    @Autowired
    public ItemMutation(ItemService itemService) {
        this.itemService = itemService;
    }

    public Item createItem(Item item) {
        return itemService.createOrUpdateItem(item);
    }

    public Item updateItem(Item item) {
        return itemService.createOrUpdateItem(item);
    }

}
