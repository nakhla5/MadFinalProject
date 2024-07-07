package com.example.finalapp;

public class NavCategoryDetailedModel {

    String name;
    String image_url;
    String price;
    String type;

    public NavCategoryDetailedModel() {
    }

    public NavCategoryDetailedModel(String name, String image_url, String price, String type) {
        this.name = name;
        this.image_url = image_url;
        this.price = price;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
