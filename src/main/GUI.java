package main;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI extends WestminsterShoppingManager {
    static ArrayList<Product> shoppingCartList = new ArrayList<>();
    Object[][] inventoryList = new Object[inventory.size()][5];
    JFrame secondFrame;
    JPanel detailsPanel;
    JPanel labelsPanel;
    JTable productTable;
    JButton addToCartButton;
    ShoppingCart shoppingCart;
    private JTable shoppingCartTable;
    JLabel totalLabel;
    JLabel firstDiscount;
    JLabel secondDiscount;
    JLabel finalCost;
    Object[][] shoppingTableData = new Object[10][3]; // Initializing shopping table data
    int electronics = 0;
    int clothing = 0;

    public void GUI() {
        shoppingCart = new ShoppingCart();
        this.inventory = inventory;
        JFrame mainFrame = new JFrame();

        mainFrame.setTitle("Westminster Shopping Centre"); // Sets title of frame
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit out of application
        mainFrame.setLayout(new BorderLayout());

        mainFrame.setVisible(true);

        JPanel productPanel = new JPanel();

        productPanel.setLayout(new BorderLayout());
        productPanel.setOpaque(false);

        // Creating a Label
        JLabel label = new JLabel("Select Product Category");

        // Creating a combobox
        String[] Products = {"All", "Electronics", "Clothing"};
        JComboBox<String> comboBox = new JComboBox<>(Products);
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the selection change event
                String selectedCategory = comboBox.getSelectedItem().toString();
                updateProductTable(selectedCategory);
            }
        });

        // Create and initialize the button
        JButton shoppingCartButton = new JButton("Shopping Cart");

        // Create a panel for the label and combobox
        JPanel categoryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 70, 25));
        categoryPanel.setOpaque(false);
        categoryPanel.add(label);
        categoryPanel.add(comboBox);

        // Create a panel for the button to right-align
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        buttonPanel.setOpaque(false);
        buttonPanel.add(shoppingCartButton);

        // Create a panel to evenly distribute space between categoryPanel and buttonPanel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setOpaque(false);
        mainPanel.add(categoryPanel, BorderLayout.WEST);
        mainPanel.add(buttonPanel, BorderLayout.EAST);

        // Add the main panel to the ProductPanel
        productPanel.add(mainPanel, BorderLayout.CENTER);

        // Add ActionListener to the button
        shoppingCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create and display the new window when the button is clicked
                secondFrame.setVisible(true);
            }
        });

        // =============================================== PRODUCT TABLE ===============================================

        JPanel productTablePanel = new JPanel();

        productTablePanel.setLayout(new BorderLayout());
        productTablePanel.setBorder(new EmptyBorder(0, 25, 0, 25));
        productTablePanel.setOpaque(false);


        // Create a table model with 5 columns and 6 rows
        String[] productColumnNames = {"Product ID", "Name", "Category", "Price($)", "Info"};
        Object[][] productData = new Object[10][5];
        for (int i = 1; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                productData[i][j] = "";
            }
        }

        // Creating a TableModel
        DefaultTableModel productTableModel = new DefaultTableModel(productData, productColumnNames);

        // Create the table
        productTable = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        if (productTable.isEditing()) {
            productTable.getCellEditor().cancelCellEditing();
        }

        productTable.setModel(productTableModel);

        productTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // To handle the selection change event
                    int selectedRow = productTable.getSelectedRow();
                    if (selectedRow != -1) {
                        TableModel model = productTable.getModel();
                        String productId = model.getValueAt(selectedRow, 0).toString();
                        String name = model.getValueAt(selectedRow, 1).toString();
                        String category = model.getValueAt(selectedRow, 2).toString();
                        String price = model.getValueAt(selectedRow, 3).toString();
                        String info = model.getValueAt(selectedRow, 4).toString();

                        String[] electronicAttri = info.split(", ");
                        String brand = electronicAttri[0];
                        String warranty = electronicAttri[1];

                        String[] clothingAttri = info.split(", ");
                        String size = clothingAttri[0];
                        String colour = clothingAttri[1];

                        updateSelectedItemPanel(productId, name, category, price, brand,
                                warranty, size, colour);
                    }
                }
            }
        });

        productTable.setRowHeight(35);
        for (int j = 0; j < inventoryList.length; j++) {
            productTableModel.setValueAt(inventory.get(j).getProductID(), j, 0);
            productTableModel.setValueAt(inventory.get(j).getProductName(), j, 1);
            productTableModel.setValueAt(inventory.get(j).getCategory(), j, 2);
            productTableModel.setValueAt(inventory.get(j).getPrice(), j, 3);
            productTableModel.setValueAt(inventory.get(j).getInfo(), j, 4);
        }

        JScrollPane productScrollPane = new JScrollPane(productTable);
        productScrollPane.setPreferredSize(new Dimension(1000, 200));

        // Access the table header and set its background color
        JTableHeader tableHeader1 = productTable.getTableHeader();
        tableHeader1.setBackground(new Color(183, 144, 255));

        // Add the table to the Panel
        productTablePanel.add(productScrollPane, BorderLayout.CENTER);

        // Creating the tables Panel
        detailsPanel = new JPanel();

        detailsPanel.setLayout(new BorderLayout());
        detailsPanel.setBackground(new Color(205, 221, 238));
        detailsPanel.setPreferredSize(new Dimension(250, 300));

        // Panel for labels
        labelsPanel = new JPanel();
        labelsPanel.setBackground(new Color(205, 221, 238));
        labelsPanel.setLayout(null);
        labelsPanel.setBounds(100,200,400,600);

        mainFrame.add(labelsPanel);

        // Button Panel
        JPanel buttonPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel1.setOpaque(false);

        // Add to Shopping Cart Button
        addToCartButton = new JButton("Add to Shopping Cart");

        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToCart();
            }
        });

        buttonPanel1.add(addToCartButton);

        // Add components to the DetailsPanel
        detailsPanel.add(buttonPanel1, BorderLayout.SOUTH);
        detailsPanel.add(labelsPanel);

        BorderLayout mainLayout = new BorderLayout();
        mainLayout.setVgap(35);

        // Create a container to hold both productPanel and tablePanel
        Container mainContainer = new Container();
        mainContainer.setLayout(mainLayout);

        mainContainer.add(productPanel, BorderLayout.NORTH);
        mainContainer.add(productScrollPane, BorderLayout.CENTER);
        mainContainer.add(detailsPanel, BorderLayout.SOUTH);

        mainFrame.add(mainContainer, BorderLayout.NORTH);

        mainFrame.setVisible(true);
        mainFrame.setResizable(true);
        mainFrame.getContentPane().setBackground(new Color(173, 215, 225));

        mainFrame.pack();

        // ============================================= SHOPPING CART WINDOW ==============================================

        secondFrame = new JFrame();
        JPanel totalPanel = new JPanel();

        secondFrame.setTitle("Shopping Cart");
        secondFrame.setLayout(new BorderLayout());
        secondFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        totalPanel.setLayout(new GridLayout(4, 1));
        totalPanel.setOpaque(false);
        totalPanel.setBorder(new EmptyBorder(10, 10, 10, 10));


        // Create a table model with 10 rows and 3 columns
        String[] columnNames2 = {"Product", "Quantity", "Price"};
        DefaultTableModel tableModel2 = new DefaultTableModel(shoppingTableData, columnNames2);

        // Create the table
        this.shoppingCartTable = new JTable(tableModel2) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        if (shoppingCartTable.isEditing()) {
            shoppingCartTable.getCellEditor().cancelCellEditing();
        }
        shoppingCartTable.setRowHeight(30);

        // Creating an EmptyBorder for the JScrollPane
        EmptyBorder scrollPaneBorder = new EmptyBorder(10, 10, 10, 10);

        // Adding a ScrollPane
        JScrollPane cartScrollPane = new JScrollPane(shoppingCartTable);
        cartScrollPane.setBorder(scrollPaneBorder); // Apply the EmptyBorder
        cartScrollPane.setPreferredSize(new Dimension(1000, 300));

        // Accessing the table header and setting its background color
        JTableHeader tableHeader = shoppingCartTable.getTableHeader();
        tableHeader.setBackground(new Color(183, 144, 255));

        // Adding the table to the Frame
        secondFrame.add(cartScrollPane, BorderLayout.CENTER);

        // Instantiate TotalPanel and add it to the Frame
        totalPanel = new JPanel();
        totalPanel.setLayout(new BoxLayout(totalPanel, BoxLayout.PAGE_AXIS));
        totalPanel.setBorder(BorderFactory.createEmptyBorder(10,200,10,200));
        secondFrame.add(totalPanel, BorderLayout.SOUTH);

        secondFrame.pack();
        secondFrame.setLocationRelativeTo(null); // Centering the frame on the screen
        secondFrame.setResizable(true);
        secondFrame.setVisible(false);

        totalLabel = new JLabel("Total: ");
        firstDiscount = new JLabel("First Purchase Discount (10%): ");

        secondDiscount = new JLabel("Three Items in same Category Discount (20%): ");

        finalCost = new JLabel("Final Total: ");

        JButton placeOrder = new JButton("Place Order");
        placeOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Order Successfully Purchased");
                first_purchase = true;
                firstPurchaseStore();
                System.exit(1);
            }
        });

        totalPanel.add(totalLabel);
        totalPanel.add(firstDiscount);
        totalPanel.add(secondDiscount);
        totalPanel.add(finalCost);

        totalPanel.add(placeOrder);

    }
    private void updateProductTable(String selectedCategory) {
        DefaultTableModel productTableModel = (DefaultTableModel) productTable.getModel();
        productTableModel.setRowCount(0); // Clear existing rows

        // Iterate through the inventory and add rows to the table based on the selected category
        for (Product product : inventory) {
            if (selectedCategory.equals("All") || product.getCategory().equals(selectedCategory)) {
                Object[] rowData = {
                        product.getProductID(),
                        product.getProductName(),
                        product.getCategory(),
                        product.getPrice(),
                        product.getInfo()
                };
                productTableModel.addRow(rowData);
            }
        }
    }
    private void updateSelectedItemPanel(String productID, String productName, String category,
                                         String price, String brand, String warrantyPeriod,
                                         String size, String color) {
        labelsPanel.removeAll();

        JLabel selectedTitle = new JLabel("Selected Product - Details");
        selectedTitle.setFont(new Font("ROBOTO", Font.BOLD, 13));
        selectedTitle.setBounds(30, 30, 200, 20);
        labelsPanel.add(selectedTitle);

        JLabel productIDLabel = new JLabel("Product ID: " + productID);
        productIDLabel.setFont(new Font("ROBOTO", Font.PLAIN, 13));
        productIDLabel.setBounds(30, 60, 200, 20);
        labelsPanel.add(productIDLabel);

        JLabel categoryLabel = new JLabel("Category: " + category);
        categoryLabel.setFont(new Font("ROBOTO", Font.PLAIN, 13));
        categoryLabel.setBounds(30, 120, 200, 20);
        labelsPanel.add(categoryLabel);

        JLabel productNameLabel = new JLabel("Product Name: " + productName);
        productNameLabel.setFont(new Font("ROBOTO", Font.PLAIN, 13));
        productNameLabel.setBounds(30, 90, 200, 20);
        labelsPanel.add(productNameLabel);

        JLabel priceLabel = new JLabel("Price: " + price + " £");
        priceLabel.setFont(new Font("ROBOTO", Font.PLAIN, 13));
        priceLabel.setBounds(30, 150, 200, 20);
        labelsPanel.add(priceLabel);

        if (category.equals("Clothing")) {
            JLabel clothSize = new JLabel("Size: " + size);
            clothSize.setFont(new Font("ROBOTO", Font.PLAIN, 13));
            clothSize.setBounds(30, 180, 300, 20);
            labelsPanel.add(clothSize);

            JLabel clothColor = new JLabel("Color: " + color);
            clothColor.setFont(new Font("ROBOTO", Font.PLAIN, 13));
            clothColor.setBounds(30, 210, 300, 20);
            labelsPanel.add(clothColor);

        } else {
            JLabel electronicBrand = new JLabel("Brand: " + brand);
            electronicBrand.setFont(new Font("ROBOTO", Font.PLAIN, 13));
            electronicBrand.setBounds(30, 180, 300, 20);
            labelsPanel.add(electronicBrand);

            JLabel electronicWarranty = new JLabel("Warranty in months: " + warrantyPeriod);
            electronicWarranty.setFont(new Font("ROBOTO", Font.PLAIN, 13));
            electronicWarranty.setBounds(30, 210, 300, 20);
            labelsPanel.add(electronicWarranty);
        }
        labelsPanel.revalidate();
        labelsPanel.repaint();
    }

    private JLabel createLabel1(String text) {
        JLabel label = new JLabel(text);
        label.setBorder(BorderFactory.createEmptyBorder(15, 50, 0, 0));
        return label;
    }

    private JLabel createLabel2(String text) {
        JLabel label = new JLabel(text);
        label.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 0));
        return label;
    }

    private void addToCart() {
        ListSelectionModel selectionModel = productTable.getSelectionModel();

        if (!selectionModel.isSelectionEmpty() && !selectionModel.getValueIsAdjusting()) {
            int selectedRow = productTable.getSelectedRow();

            if (selectedRow != -1) {
                // Retrieve data for the selected row
                int items = inventory.get(selectedRow).getAvailableItems();
                Object productId = productTable.getValueAt(selectedRow, 0);
                Object productName = productTable.getValueAt(selectedRow, 1);
                Object category = productTable.getValueAt(selectedRow, 2);
                Object price = productTable.getValueAt(selectedRow, 3);
                Object info = productTable.getValueAt(selectedRow, 4);

                double pri = (double) price;

                Product currentProduct = setProduct(shoppingCart.getProducts(), (String) productId);

                if (currentProduct == null) {
                    // New item, add to the shopping cart
                    shoppingCart.addProduct(new Product(productId.toString(), productName.toString(), category.toString(), pri, info.toString()));
                } else {
                    // Existing item, update the quantity
                    currentProduct.setQuantity(currentProduct.getQuantity() + 1);
                }

                // Update the shopping cart table when the button is clicked
                updateShoppingCartTable();

                double total = shoppingCart.calculateTotalCost();

                totalLabel.setText("Total: " + total);
                totalLabel.repaint();
                totalLabel.removeAll();
                totalLabel.revalidate();

                firstPurchaseLoad();
                if (!first_purchase){
                    double tenPercent = shoppingCart.calculate10Percent();

                    firstDiscount.setText("First Purchase Discount (10%): " + tenPercent);
                    firstDiscount.repaint();
                    firstDiscount.removeAll();
                    firstDiscount.revalidate();
                } else {
                    firstDiscount.setText("First Purchase Discount (10%): " + "Not Applicable");
                    firstDiscount.repaint();
                    firstDiscount.removeAll();
                    firstDiscount.revalidate();
                }
                if ((electronics > 3) || (clothing > 3)) {
                    double twentyPercent = shoppingCart.calculate20Percent();

                    secondDiscount.setText("Three Items in same Category Discount (20%): " + twentyPercent);
                    secondDiscount.repaint();
                    secondDiscount.removeAll();
                    secondDiscount.revalidate();
                } else {
                    secondDiscount.setText("Three Items in same Category Discount (20%): " + "Not Applicable");
                    secondDiscount.repaint();
                    secondDiscount.removeAll();
                    secondDiscount.revalidate();
                }
                double finalT = shoppingCart.calculateFinalCost();

                finalCost.setText("Final Total: " + finalT);
                finalCost.repaint();
                finalCost.removeAll();
                finalCost.revalidate();
            }
        }
    }
    private Product setProduct(ArrayList<Product> shoppingCartList,String productId){
        for(Product item : shoppingCartList){
            if(item.getProductID().equals(productId)){
                return item;
            }
        }
        return null;
    }

    private void updateShoppingCartTable() {
        DefaultTableModel shoppingCartTableModel = (DefaultTableModel) shoppingCartTable.getModel();
        shoppingCartTableModel.setRowCount(0); // Clear existing rows

        // Add products from shoppingCartList to the shopping cart table
        for (Product product : shoppingCart.getProducts()) {
            Object[] rowData = {product.getProductName(), product.getQuantity(), product.getPrice()};
            shoppingCartTableModel.addRow(rowData);
            if (product.getCategory().equals("Electronics")) {
                electronics = electronics + product.getQuantity();
            } else if (product.getCategory().equals("Clothing")) {
                clothing = clothing + product.getQuantity();
            }
        }
    }


    private Product findItemByID(String productId) {
        for (Product product : shoppingCartList) {
            if (product.getProductID().equals(productId)) {
                return product;
            }
        } return null;
    }
}