package main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int choice;

        while (true){

            Scanner scanner = new Scanner(System.in);

            System.out.println("===== MAIN MENU =====");
            System.out.println("1. Admin console");
            System.out.println("2. main GUI");
            System.out.println("3. Exit");
            System.out.println("=====================");

            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                // Check if the choice is within the valid range
                if (choice >= 1 && choice <= 3) {
                    switch (choice) {
                        case 1:
                            // Go to Admin console
                            WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
                            shoppingManager.loadData();
                            shoppingManager.firstPurchaseLoad();
                            shoppingManager.Start();
                            break;

                        case 2:
                            // Go to main.GUI
                            GUI gui = new GUI();
                            gui.GUI();
                            break;

                        case 3:
                            // Exit
                            System.out.println("Exiting...");
                            System.exit(0);
                            break;
                    }
                } else {
                    // Invalid choice, display error message
                    System.out.println("Select a valid option.");
                }
            } else {
                // Invalid input, display error message
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Consume the invalid input
            }
        }
    }
}