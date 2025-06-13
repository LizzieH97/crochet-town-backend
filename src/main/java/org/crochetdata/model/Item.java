package org.crochetdata.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    
    private String name;
    private Double difficulty;
    
    @Column(name = "imageURL")
    private String imageURL;
    
    private String category;
    private Double rating;
    
    @Column(name = "favourites_count")
    private int favouritesCount;
    
    private int reviews;
    
    @Column(name = "authorID")
    private int authorId;
    
    @Column(name = "hook_size")
    private double hookSize;
    
    @Column(name = "end_size")
    private double endSize;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "pattern_id", nullable = false)
    private Pattern pattern;

    @ElementCollection
    @CollectionTable(
        name = "item_colours",
        joinColumns = @JoinColumn(name = "item_id")
    )
    @Column(name = "colour")
    private List<String> colours;

    // Required no-args constructor
    protected Item() {
        // JPA required no-arg constructor
    }

    // Update getter/setter names to match new variable names
    public Long getId() {  // Changed from getID
        return id;
    }

    public void setId(Long id) {  // Changed from setID
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Double difficulty) {
        this.difficulty = difficulty;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    // Update other getters/setters to match new variable names
    public int getFavouritesCount() {
        return favouritesCount;
    }

    public void setFavouritesCount(int favouritesCount) {
        this.favouritesCount = favouritesCount;
    }

    public int getReviews() {
        return reviews;
    }

    public void setReviews(int reviews) {
        this.reviews = reviews;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public double getHookSize() {
        return hookSize;
    }

    public void setHookSize(double hookSize) {
        this.hookSize = hookSize;
    }

    public double getEndSize() {
        return endSize;
    }

    public void setEndSize(double endSize) {
        this.endSize = endSize;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public List<String> getColours() {
        return colours;
    }

    public void setColours(List<String> colours) {
        this.colours = colours;
    }

    // Update constructor to use new variable names
    public Item(String name, double difficulty, String imageURL, String category, double rating,
                int favouritesCount, int reviews, int authorId, double hookSize, double endSize,
                List<String> colours, Pattern pattern) {
        this.name = name;
        this.difficulty = difficulty;
        this.imageURL = imageURL;
        this.category = category;
        this.rating = rating;
        this.favouritesCount = favouritesCount;
        this.reviews = reviews;
        this.authorId = authorId;
        this.hookSize = hookSize;
        this.endSize = endSize;
        this.colours = colours;
        this.pattern = pattern;
    }

    // Your existing getters and setters
}