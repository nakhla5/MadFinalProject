package com.example.finalapp;

public class RecommendedModel {

    String name;
    String price;
    String rating;
    String description;
    String image_url;
    public RecommendedModel() {
    }

    public RecommendedModel(String name, String price, String rating, String description, String image_url) {
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.description = description;
        this.image_url = image_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
