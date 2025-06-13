package org.crochetdata.controller;

import org.crochetdata.model.Item;
import org.crochetdata.service.ItemService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @PostMapping("/upload")
    public ResponseEntity<Item> uploadItemWithImages(
            @RequestParam("mainImage") MultipartFile mainImage,
            @RequestParam("patternFiles") List<MultipartFile> patternFiles,
            @RequestParam("name") String name,
            @RequestParam("difficulty") Double difficulty,
            @RequestParam("category") String category,
            @RequestParam("hook_size") double hookSize
    ) throws IOException {
        Item savedItem = itemService.createItem(mainImage, patternFiles, name, difficulty, category, hookSize);
        return ResponseEntity.ok(savedItem);
    }


}

