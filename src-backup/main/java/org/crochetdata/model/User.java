package org.crochetdata.model;

public class User {
    private int ID;
    private int reviewCount;
    private String[] favourites;
    private int postCount;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String[] getFavourites() {
        return favourites;
    }

    public void setFavourites(String[] favourites) {
        this.favourites = favourites;
    }

    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }
}
