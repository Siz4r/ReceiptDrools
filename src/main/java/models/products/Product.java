package models.products;


import models.Basket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Product  {
    private final String name;

    public Product(String name) {
        this.name = name;
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
            basket.addProduct(this);
        });

        minus.setText("-");
        product.add("addButton", add);
        product.add("productName", name);
        product.add("minusButton", minus);
        minus.addActionListener(e -> System.out.println("Siema"));
        return product;
    }
}