package org.crochetdata.utils;

import org.crochetdata.model.Item;
import org.crochetdata.model.Pattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CrochetData {
    private final ArrayList<Item> data;

    public CrochetData() {
        data = new ArrayList<>();

        Item sakuraBag = new Item("Sakura Market Bag", 3,
                "/uploads/sakura-main.png",
                "Clothing", 0.0, 5, 0, 1, 4.0, 32,
                new ArrayList<>(Arrays.asList("lime green")),
                new Pattern());

        Pattern sakuraPattern = new Pattern();
        sakuraPattern.setStepImages(Arrays.asList(
                "/uploads/sakura-pattern1.png",
                "/uploads/sakura-pattern2.png",
                "/uploads/sakura-pattern3.png",
                "/uploads/sakura-pattern4.png",
                "/uploads/sakura-visual.png",
                "/uploads/sakura-visual-2.png"
        ));


        sakuraBag.setPattern(sakuraPattern);

        Pattern whalePattern = new Pattern();
        whalePattern.setStepImages(Arrays.asList("/uploads/mini-whale-pattern.png"));
        Item miniWhale = new Item("Mini Whale", 1,
                "/uploads/miniwhale.png",
                "Toys", 0.0, 0, 0, 2, 3.5, 10,
                new ArrayList<>(Arrays.asList("blue", "white")),
                whalePattern);

        // Similar for other items...
        Pattern lemonPattern = new Pattern();
        lemonPattern.setStepImages(Arrays.asList("/uploads/lemon-pattern.png"));
        Item lemonSquare = new Item("Lemon Granny Square", 7,
                "/uploads/lemonsquare.png",
                "Granny Square", 0.0, 0, 0, 2, 4.0, 13,
                new ArrayList<>(Arrays.asList("yellow", "lemon", "pistachio", "dark green")),
                lemonPattern);

        Pattern stitchPattern = new Pattern();
        stitchPattern.setStepImages(Arrays.asList("/uploads/stitch-pattern.png"));
        Item stitchPlush = new Item("Stitch Plush Toy", 2,
                "/uploads/stitch.png",
                "Plush Toy", 0.0, 0, 0, 2, 4.0, 44,
                new ArrayList<>(Arrays.asList("blue", "green")),
                stitchPattern);
        Pattern babyPattern = new Pattern();
        babyPattern.setStepImages(Arrays.asList("/uploads/baby-pattern.png"));
        Item babyBlanket = new Item("Baby Blanket", 3,
                "/uploads/babyblanket.png",
                "Baby", 0.0, 0, 0, 4, 6.5, 35,
                new ArrayList<>(Arrays.asList("baby pink")),
                babyPattern);

        data.add(sakuraBag);
        data.add(miniWhale);
        data.add(lemonSquare);
        data.add(stitchPlush);
        data.add(babyBlanket);
    }

    public ArrayList<Item> getData() {
        return data;
    }
}
