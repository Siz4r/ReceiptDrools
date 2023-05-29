package models;

import models.products.Product;
import models.products.TaxType;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket {
    public final Integer productsQuantity = 5;
    private static Basket basket;
    private final Map<Product, Integer> productList = new HashMap<>();
    private final JPanel receiptContainer = new JPanel();
    private Map<TaxType, Double> amountOfTaxes = new HashMap<>();
    private double discount;

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    private double totalAmount = 0;

    public double getTotalAmount() {
        return totalAmount;
    }

    private Basket() {
    }

    public static Basket getBasket() {
        if (basket == null) {
            return (basket = new Basket());
        }

        return basket;
    }

    public Map<TaxType, Double> getAmountOfTaxes() {
        return amountOfTaxes;
    }

    public void addProduct(Product product) {
        productList.merge(product, 1, Integer::sum);
        amountOfTaxes.merge(product.getTaxType(), product.getTaxType().getInterest() * product.getPrize(), Double::sum);
        totalAmount += product.getPrize();
        updateReceipt();

    }
    public void removeProduct(Product product) {
        Integer quantity = productList.get(product);
        if (quantity != null) {
            if (quantity > 1) {
                productList.put(product, quantity - 1);
            } else {
                productList.remove(product);
            }
            amountOfTaxes.merge(product.getTaxType(), -product.getTaxType().getInterest() * product.getPrize(), Double::sum);
            totalAmount -= product.getPrize();
            updateReceipt();
        }
    }
    public void promotion() {

        Map<Product, Integer> productList = basket.getProductList();
        System.out.println("dzialasz kurwa??????????");
        System.out.println(productList);
        System.out.println(productList.isEmpty());
        for (Map.Entry<Product, Integer> entry : productList.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            System.out.println(quantity);
            if (product.getName().equals("Mleko") && quantity >= 3) {
                System.out.println("mlekoooo");
                double discountedPrice = product.getPrize() * 0.5;
                double totalDiscount = (product.getPrize() - discountedPrice) * quantity;
                basket.setDiscount(basket.getDiscount() + totalDiscount);

                product.setPrize(discountedPrice);
            }

            productList.put(product, quantity);
        }
        System.out.println(productList);
        basket.updateReceipt();
        System.out.println(productList);
    }

    private void updateReceipt() {
        JPanel receipt = Receipt.generateReceipt(this);

        getReceiptContainer().removeAll();
        getReceiptContainer().add(receipt);
        getReceiptContainer().revalidate();
        getReceiptContainer().repaint();
    }
    public Map<Product, Integer> getProductList() {
        return productList;
    }

    public JPanel getReceiptContainer() {
        return receiptContainer;
    }

    public void setReceiptContainer(JPanel receiptContainer) {
        this.receiptContainer.add(receiptContainer);
    }
}