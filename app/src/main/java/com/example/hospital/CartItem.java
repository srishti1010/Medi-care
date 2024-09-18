package com.example.hospital;



public class CartItem {
    private String name;
    private double price;
    private int quantity;
    private String imageUrl;

    // No-argument constructor required for Firebase
    public CartItem() {
    }

    public CartItem(String name, double price, int quantity, String imageUrl) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
