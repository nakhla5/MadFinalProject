package com.example.finalapp;

public class NavCategoryModel {
    String name;
    String description;
    String image_url;
    String discount;

    String type;

    public NavCategoryModel() {
    }


    public NavCategoryModel(String name, String description, String image_url, String discount, String type) {
        this.name = name;
        this.description = description;
        this.image_url = image_url;
        this.discount = discount;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}
