package main;
import java.io.*;
import java.util.*;

public class WestminsterShoppingManager implements ShoppingManager {
    static List<Product> inventory = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    String productTypeStr;

    static boolean first_purchase;

    public void Start() {
        int choice;

        while (true) {
            System.out.println("===== Westminster Shopping Manager Menu =====");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. Print List");
            System.out.println("4. Save Products");
            System.out.println("5. Load Products");
            System.out.println("6. Exit");
            System.out.println("=============================================");

            System.out.println("Enter option: ");

            try {
                // Getting the menu option from the manager.
                choice = scanner.nextInt();
                scanner.nextLine();
                System.out.println();
                // If manager enters 6, it will go back to main menu.
                if (choice == 6) {
                    System.out.println("You're returning to Main menu..");
                    break;
                }

                switch (choice) {  // Cases for manager's menu option
                    case 1:
                        addProduct();
                        break;
                    case 2:
                        removeProduct();
                        break;
                    case 3:
                        printProducts();
                        break;
                    case 4:
                        storeData();
                        break;
                    case 5:
                        loadData();
                        break;
                    default:
                        System.out.println("Option out of bounds. Try again.");
                        break;
                }
                // Showing errors for manager input mistakes.
            } catch (InputMismatchException e) {
                System.out.println("Error! Enter an integer");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("An error occurred.");
                scanner.nextLine();
            }
        }
    }

    @Override
    public void addProduct() {
        // Check if the inventory size is less than 50 before adding
        if (inventory.size() < 50) {
            // Add a new main.Product
            System.out.print("Enter product type (1.Electronics, 2.Clothing): ");
            int productType = scanner.nextInt();
            if (productType == 1) {
                productTypeStr = "Electronics";
            } else if (productType == 2) {
                productTypeStr = "Clothing";
            }
            scanner.nextLine(); // Consume the newline character

            System.out.print("Enter product ID: ");
            String productId = scanner.nextLine();

            System.out.print("Enter product Name: ");
            String productName = scanner.nextLine();

            System.out.print("Enter available items: ");
            int availableItems = scanner.nextInt();

            System.out.println("Enter price: ");
            double price = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline character

            if (productType == 1) {
                // Electronics
                System.out.print("Enter brand: ");
                String brand = scanner.nextLine();

                System.out.print("Enter warranty period (in months): ");
                int warrantyPeriod = scanner.nextInt();

                String productInfo = brand + ", " + warrantyPeriod;

                Electronics newElectronics = new Electronics(productId, productName, productTypeStr, availableItems, price, productInfo, brand, warrantyPeriod);
                inventory.add(newElectronics);
            } else if (productType == 2) {
                // Clothing
                System.out.print("Enter size: ");
                String size = scanner.nextLine();

                System.out.println("Enter color");
                String color = scanner.nextLine();

                String productInfo = size + ", " + color;

                Clothing newClothing = new Clothing(productId, productName, productTypeStr, availableItems, price, productInfo, size, color);
                inventory.add(newClothing);
            } else {
                System.out.println("Invalid product type.");
            }
            System.out.println();
            System.out.println("Product added successfully!");
            System.out.println();
        } else {
            System.out.println("Maximum number of products reached. Cannot add more products.");
        }

    }

    @Override
    public void removeProduct() {
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("\nEnter Product ID to be removed:");
            String productId = input.nextLine();

            // Checking if product ID entered is in the inventory
            for (int i = 0; i < inventory.size(); i++) {
                if (inventory.get(i).getProductID().equals(productId)) {
                    System.out.println("\nThe product with ID " + productId + " and name " +
                            inventory.get(i).getProductName() + " has been removed.");
                    inventory.remove(i);
                    System.out.println("\nProduct removed successfully!");
                    return;  // Exit the loop if the product is found and removed
                }
            }
            System.out.println("\nProduct with ID " + productId + " not found in the inventory.");
        } catch (Exception e) {
            System.out.println("\nIncorrect Input...Try Again");
        }
    }

    @Override
    public void printProducts() {
        // Sort the inventory alphabetically by product ID
        Collections.sort(inventory, Comparator.comparing(Product::getProductID));

        System.out.println("===== List of Products in the System =====");
        for (Product product : inventory) {
            if (product instanceof Electronics) {
                System.out.println("Electronics: " + product.getProductID() + ", " +
                        product.getProductName() + ", Brand: " + ((Electronics) product).getBrand() +
                        ", Warranty: " + ((Electronics) product).getWarrantyPeriod() + " months");
            } else if (product instanceof Clothing) {
                System.out.println("Clothing: " + product.getProductID() + ", " +
                        product.getProductName() + ", Size: " + ((Clothing) product).getSize() +
                        ", Color: " + ((Clothing) product).getColor());
            }
        }
        System.out.println("=============================================");
    }

    @Override
    public void storeData() {
        try {
            // Creates a new file to save data entered by the user
            FileOutputStream fileOut = new FileOutputStream("FileData.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            // Write the inventory ArrayList to the file
            out.writeObject(inventory);

            // Close the ObjectOutputStream and FileOutputStream
            out.close();
            fileOut.close();

            System.out.println("\nThe Data has been stored successfully!!!");

        } catch (Exception e) {
            e.printStackTrace(); // Print the exception details for debugging
            System.out.println("\nError occurred while storing data. Try Again");
        }
    }


    public void loadData() {
        try {
            FileInputStream fileIn = new FileInputStream("FileData.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            inventory = (ArrayList) in.readObject();
            in.close();
            fileIn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void firstPurchaseStore() {
        try {
            // Creates new file to save data entered by the user
            FileOutputStream fileOut = new FileOutputStream("FirstPurchase.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            System.out.println("store" + first_purchase);
            // Serialize the DataStore instance
            out.writeObject(first_purchase);

            out.close();
            fileOut.close();
            System.out.println("\nThe Data has been stored Successfully!!!");
        } catch (Exception e) {
            System.out.println("\n Incorrect Input...Try Again");
            e.printStackTrace();
        }
    }



    public void firstPurchaseLoad(){
        try{
            FileInputStream fileIn = new FileInputStream("FirstPurchase.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            first_purchase = (Boolean) in.readObject();
            in.close();
            fileIn.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
