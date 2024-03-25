package main;

import java.io.Serializable;

public class Product implements Serializable {
    private String productID;
    private String productName;
    private String category;
    private int availableItems;
    private double price;
    private int quantity;
    private String info;
    public Product(String productID, String productName, String category , int availableItems, double price, String info) {
        this.productID = productID;
        this.productName = productName;
        this.category = category;
        this.availableItems = availableItems;
        this.price = price;
        this.info = info;
    }

    public Product(String productID, String productName, String category , double price, String info) {
        this.productID = productID;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.info = info;
        this.quantity = 1;
    }

    // Getters and Setters
    public String getProductID() {
        return productID;
    }
    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public int getAvailableItems() {
        return availableItems;
    }
    public void setAvailableItems(int availableItems) {
        this.availableItems = availableItems;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID='" + productID + '\'' +
                ", productName='" + productName + '\'' +
                ", category='" + category + '\'' +
                ", availableItems=" + availableItems +
                ", price=" + price +
                ", info='" + info + '\'' +
                '}';
    }
}
