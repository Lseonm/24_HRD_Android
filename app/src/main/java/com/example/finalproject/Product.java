package com.example.finalproject;

import java.io.Serializable;

public class Product implements Serializable {
    private long id;
    private String title;
    private String price;
    private String description;
    private boolean isFree;
    private String category;

    // ID를 제외한 생성자
    public Product(String title, String price, String description, boolean isFree, String category) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.isFree = isFree;
        this.category = category;
    }

    public Product(long id, String title, String price, String description, boolean isFree, String category) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.isFree = isFree;
        this.category = category;
    }

    // Getter and Setter
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
