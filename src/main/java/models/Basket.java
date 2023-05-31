package models;

import core.RulesService;
import models.products.Product;
import models.products.TaxType;
import org.kie.api.KieServices;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket {
    public final Integer productsQuantity = 5;
    private static Basket basket;
    private final List<Product> productList = new ArrayList<>();
    private final JPanel receiptContainer = new JPanel();
    private final Map<TaxType, Double> amountOfTaxes = new HashMap<>();
    private double totalAmount = 0;

    private Basket() {
    }

    public static Basket getBasket() {
        if (basket == null) {
            return (basket = new Basket());
        }

        return basket;
    }

    public void applyRules(Product product) {
        RulesService.initializeEngine(KieServices.Factory.get());
        RulesService.getKsession().insert(product);
        RulesService.getKsession().insert(basket);
        RulesService.getKsession().fireAllRules();

        updateReceipt();
    }

    public void addProduct(Product product) {
        Product p = getProduct(product.getName());

        if (p == null) {
            product.setAmount(1);
            productList.add(product);
            totalAmount += product.getAmount();
        } else {
            p.setAmount(p.getAmount() + 1);
            p.setDiscount(p.getDiscount());
            totalAmount += p.getPrize();
        }
        updateReceipt();
    }

    public void removeProduct(Product product) {
        Product p = getProduct(product.getName());

        if (p != null) {
            if (p.getAmount() > 1) {
                p.setAmount(p.getAmount() - 1);
            } else {
                productList.remove(p);
            }

            amountOfTaxes.merge(product.getTaxType(), -product.getTaxType().getInterest() * product.getPrize(), Double::sum);
            totalAmount -= product.getPrize();
            updateReceipt();
        }
    }

    public void promotion() {
//        Map<Product, Integer> productList = basket.getProductList();
//        for (Map.Entry<Product, Integer> entry : productList.entrySet()) {
//            Product product = entry.getKey();
//            int quantity = entry.getValue();
//            if (product.getName().equals("Mleko") && quantity >= 3) {
//                double discountedPrice = product.getPrize() * 0.5;
//                double totalDiscount = (product.getPrize() - discountedPrice) * quantity;
////                basket.setDiscount(basket.getDiscount() + totalDiscount);
//
//                product.setPrize(discountedPrice);
//            }
//
//            productList.put(product, quantity);
//        }
//
//        basket.updateReceipt();
    }

    private void updateReceipt() {
        JPanel receipt = Receipt.generateReceipt(this);

        getReceiptContainer().removeAll();
        getReceiptContainer().add(receipt);
        getReceiptContainer().revalidate();
        getReceiptContainer().repaint();
    }


    public Product getProduct(String name) {
        return productList.stream()
                .filter(p -> p.getName().equals(name))
                .findAny().orElse(new Product("milk", TaxType.A, 2.0));
    }

    public int countProducts(String name) {
        return getProduct(name).getAmount();
    }

    public List<Product> getProductList() {
        return productList;
    }

    public JPanel getReceiptContainer() {
        return receiptContainer;
    }
    public void setReceiptContainer(JPanel receiptContainer) {
        this.receiptContainer.add(receiptContainer);
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public Map<TaxType, Double> getAmountOfTaxes() {
        return amountOfTaxes;
    }
}