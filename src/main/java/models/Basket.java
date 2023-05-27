package models;

import models.products.Product;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Basket {
    public final Integer productsQuantity = 5;

    public List<Product> getProductList() {
        return productList;
    }

    private List<Product> productList;

    public JPanel getReceiptContainer() {
        return receiptContainer;
    }

    public void setReceiptContainer(JPanel receiptContainer) {
        this.receiptContainer = receiptContainer;
    }

    private JPanel receiptContainer;
    public Basket() {
        productList = new ArrayList<>();
    }
    public final Integer taxesTypes = 2;
    public Basket(JPanel receiptContainer) {
        this.receiptContainer = receiptContainer;
        productList = new ArrayList<>();
    }
    public void addProduct(Product product) {
        productList.add(product);
        System.out.println(productList);
        updateReceipt();
    }
    private void updateReceipt() {
        JPanel receipt = Receipt.generateReceipt(this);
        System.out.println("aaaaa"+receipt);
        receiptContainer.removeAll();
        receiptContainer.add(receipt);
        receiptContainer.revalidate();
        receiptContainer.repaint();
    }
}