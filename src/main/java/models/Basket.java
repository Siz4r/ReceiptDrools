package models;

import models.products.Product;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Basket {
    public final Integer productsQuantity = 5;
    private static Basket basket;

    public List<Product> getProductList() {
        return productList;
    }

    private final List<Product> productList = new ArrayList<>();

    public JPanel getReceiptContainer() {
        return receiptContainer;
    }

    public void setReceiptContainer(JPanel receiptContainer) {
        this.receiptContainer.add(receiptContainer);
    }

    private final JPanel receiptContainer = new JPanel();
    private Basket() {
    }

    public static Basket getBasket() {
        if (basket == null) {
            return (basket = new Basket());
        }

        return basket;
    }

    public final Integer taxesTypes = 2;

    public void addProduct(Product product) {
        productList.add(product);
        updateReceipt();
    }
    private void updateReceipt() {
        JPanel receipt = Receipt.generateReceipt(this);

        getReceiptContainer().removeAll();
        getReceiptContainer().add(receipt);
        getReceiptContainer().revalidate();
        getReceiptContainer().repaint();
    }
}