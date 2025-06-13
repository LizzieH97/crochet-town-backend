package org.crochetdata;

import org.crochetdata.model.Item;
import org.crochetdata.service.ItemService;
import org.crochetdata.utils.CrochetData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ItemSeeder implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ItemService itemService;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        ensureItemTableExists();

        if (!itemService.getAllItems().isEmpty()) {
            System.out.println("🌱 Skipping seed — items already exist.");
            return;
        }

        System.out.println("🌱 Seeding crochet items...");

        List<Item> items = new CrochetData().getData();
        itemService.createItems(items);

        System.out.println("✅ Seeded " + items.size() + " items!");
    }

    private void ensureItemTableExists() {
        try {
            jdbcTemplate.execute("""
            CREATE TABLE IF NOT EXISTS pattern (
                id BIGINT PRIMARY KEY AUTO_INCREMENT,
                step_images JSON
            );
            """);
            System.out.println("✅ Ensured 'pattern' table exists.");

            jdbcTemplate.execute("""
            CREATE TABLE IF NOT EXISTS item (
                id BIGINT PRIMARY KEY AUTO_INCREMENT,
                name VARCHAR(255),
                difficulty DOUBLE,
                image_url VARCHAR(255),
                category VARCHAR(255),
                rating DOUBLE,
                favourites_count INT,
                reviews INT,
                author_id INT,
                hook_size DOUBLE,
                end_size DOUBLE,
                pattern_id BIGINT,
                FOREIGN KEY (pattern_id) REFERENCES pattern(id)
            );
        """
            );
            System.out.println("✅ Ensured 'item' table exists.");
        } catch (Exception e) {
            System.err.println("❌ Failed to check/create 'item' table: " + e.getMessage());
        }
    }
}