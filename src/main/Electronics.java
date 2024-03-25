package main;
import java.io.Serializable;

public class Electronics extends Product implements Serializable {
    private String brand;
    private int warrantyPeriod;

    public Electronics(String productID, String productName, String category, int availableItems, double price, String info, String brand, int warrantyPeriod) {
        super(productID, productName, category, availableItems, price, info);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }
    // Getters and Setters specific to main.Electronics
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

}
