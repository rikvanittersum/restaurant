package com.example.gebruiker.restaurant;

import java.io.Serializable;

public class MenuItem implements Serializable {

    private String  name, description, imageUrl, category;
    private int price;

    public MenuItem(String name, String description, String imageUrl, int price, String category){
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }
}
