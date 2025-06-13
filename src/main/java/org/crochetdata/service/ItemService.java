package org.crochetdata.service;

import org.crochetdata.model.Item;
import org.crochetdata.model.Pattern;
import org.crochetdata.repository.ItemRepository;
import org.crochetdata.repository.PatternRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final PatternRepository patternRepository;

    private final String uploadDirectory = "src/main/resources/static/uploads";

    @Autowired
    public ItemService(ItemRepository itemRepository, PatternRepository patternRepository) {
        this.itemRepository = itemRepository;
        this.patternRepository = patternRepository;
    }

    // 🌱 For seeding multiple items from CrochetData
    @Transactional
    public List<Item> createItems(List<Item> items) {
        List<Item> savedItems = new ArrayList<>();

        for (Item item : items) {
            // Save each item individually to ensure proper pattern association
            if (item.getPattern() != null) {
                Pattern pattern = item.getPattern();
                // Ensure pattern is saved first
                Pattern savedPattern = patternRepository.save(pattern);
                // Create a new item with the saved pattern
                item.setPattern(savedPattern);
                // Save the item
                savedItems.add(itemRepository.save(item));
            } else {
                throw new IllegalArgumentException("Pattern cannot be null for item: " + item.getName());
            }
        }

        return savedItems;
    }



// 🌐 For creating one item with image + pattern steps (called by controller)
@Transactional
public Item createItem(MultipartFile mainImage,
                     List<MultipartFile> patternFiles,
                     String name,
                     Double difficulty,
                     String category,
                     double hookSize) throws IOException {

    // Ensure upload folder exists
    File uploadDir = new File(uploadDirectory);
    if (!uploadDir.exists()) {
        uploadDir.mkdirs();
    }

    if (mainImage == null || mainImage.isEmpty()) {
        throw new IllegalArgumentException("Main image is required");
    }
    if (name == null || name.trim().isEmpty()) {
        throw new IllegalArgumentException("Name is required");
    }

    // Save main image
    String mainImageFilename = UUID.randomUUID() + "_" + mainImage.getOriginalFilename();
    File mainImageFile = new File(uploadDirectory, mainImageFilename);
    mainImage.transferTo(mainImageFile);
    String mainImagePath = "/uploads/" + mainImageFilename;

    // Create and save Pattern first
    Pattern pattern = new Pattern();
    pattern.setStepImages(new ArrayList<>());

    for (MultipartFile file : patternFiles) {
        String patternFilename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        File patternFile = new File(uploadDirectory, patternFilename);
        file.transferTo(patternFile);
        pattern.getStepImages().add("/uploads/" + patternFilename);
    }

    // Save the pattern first
    try {
        Pattern savedPattern = patternRepository.save(pattern);

        // Build and save Item with the saved pattern
        Item item = new Item(name, difficulty.intValue(), mainImagePath, category,
                0.0, 0, 0, 1, hookSize, 0,
                List.of(), // default empty colours
                savedPattern);  // Use the saved pattern

        return itemRepository.save(item);
    } catch (Exception e) {
        // Clean up any uploaded files
        mainImageFile.delete();
        patternFiles.forEach(file -> new File(uploadDirectory, file.getName()).delete());
        throw new RuntimeException("Failed to save pattern: " + e.getMessage(), e);
    }
}

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
// Before inserting/updating an item, verify the pattern exists
@Transactional
public Item saveItem(Item item) {
    if (item.getPattern() == null) {
        throw new IllegalArgumentException("Pattern cannot be null");
    }

    Pattern pattern = item.getPattern();
    if (pattern.getId() != null) {
        // If pattern has ID, verify it exists
        if (!patternRepository.existsById(pattern.getId())) {
            throw new IllegalArgumentException("Pattern with ID " + pattern.getId() + " does not exist");
        }
    } else {
        // If pattern has no ID, save it as new
        pattern = patternRepository.save(pattern);
        item.setPattern(pattern);
    }

    return itemRepository.save(item);
}
}