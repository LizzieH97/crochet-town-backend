package org.crochetData.controller;

import org.crochetData.model.Item;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.crochetData.service.ItemService;

import java.util.List;

@RestController

public class ItemController {

    private final ItemService itemService;

    // Constructor injection
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // GET /items
    @GetMapping
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }
}
