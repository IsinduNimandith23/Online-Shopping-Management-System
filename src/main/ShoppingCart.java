package main;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private int quantity;
    private ArrayList<Product> products;
    public ShoppingCart(){
        this.products = new ArrayList<>();
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    // Method to add a product to the cart
    public void addProduct(Product product) {
        products.add(product);
    }

    // Method to remove a product from the cart
    public void removeProduct(Product product){
        products.remove((product));
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
    double percentageCost = 0;
    double percentage_twenty_Cost = 0;

    // Method to calculate the total cost of items in the cart
    public double calculateTotalCost(){
        double totalCost = 0;
        for (Product product : products) {
            totalCost += product.getPrice() * product.getQuantity();
        }
        return totalCost;
    }

    public double calculate10Percent(){
        double totalCost = 0;
        for (Product product : products) {
            totalCost += product.getPrice() * product.getQuantity();
        }
        percentageCost = (totalCost * 0.10);
        return percentageCost;
    }

    public double calculate20Percent(){
        double totalCost = 0;
        for (Product product : products) {
            totalCost += product.getPrice() * product.getQuantity();
        }
        percentage_twenty_Cost = totalCost * 0.20;
        return percentage_twenty_Cost;
    }
    public double calculateFinalCost(){
        double totalProduct = 0;
        for (Product product : products) {
            totalProduct += product.getPrice() * product.getQuantity();
        }
        totalProduct = totalProduct - percentageCost - percentage_twenty_Cost;
        return totalProduct;
    }
}
