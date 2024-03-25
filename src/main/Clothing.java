package main;

import java.io.Serializable;

public class Clothing extends Product implements Serializable {
    private String size;
    private String color;

    public Clothing(String productID, String productName, String category, int availableItems, double price, String info, String size, String color) {
        super(productID, productName, category, availableItems, price, info);
        this.size = size;
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
