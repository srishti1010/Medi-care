package com.example.hospital;

public class Medicine {
    private String name;
    private double price;
    private double discount;
    private String imageUrl;

    public Medicine() {
    }

    public Medicine(String name, double price, double discount, String imageUrl) {
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
