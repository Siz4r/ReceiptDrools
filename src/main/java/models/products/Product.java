package models.products;


import models.Basket;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Product  {
    private final String name;
    private final TaxType taxType;
    private Double prize;
    private Integer amount = 0;
    private double discount = 0.0;
    public Product( String name, TaxType taxType, Double prize) {

        this.name = name;
        this.taxType = taxType;
        this.prize = prize;
    }
    public String getName() {
        return name;
    }
    public JPanel getComponent(Basket basket) {
        JPanel product = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        product.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        JLabel name = new JLabel(this.name);

        JButton add = new JButton();
        add.setPreferredSize(new Dimension(40, 40));
        add.setText("+");

        JButton minus = new JButton();
        minus.setPreferredSize(new Dimension(40, 40));

        add.addActionListener(e -> {
            basket.applyRules(this);
            basket.addProduct(this);
        });

        minus.setText("-");
        product.add("addButton", add);
        product.add("productName", name);
        product.add("minusButton", minus);
        minus.addActionListener(e -> basket.removeProduct(this));

        return product;
    }

    public TaxType getTaxType() {
        return taxType;
    }

    public Double getPrize() {
        return prize;
    }
    public void setPrize(double prize) {
        this.prize=prize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, taxType, prize);

    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}