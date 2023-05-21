package models.products;


import javax.swing.*;
import java.awt.*;

public abstract class Product  {
    private final String name;

    public Product(String name) {
        this.name = name;
    }

    public JPanel getComponent() {
        JPanel product = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        product.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        JLabel name = new JLabel(this.name);
        JButton add = new JButton();
        add.setPreferredSize(new Dimension(40, 40));
        add.setText("+");
        JButton minus = new JButton();
        minus.setPreferredSize(new Dimension(40, 40));
        minus.setText("-");
        product.add("addButton", add);
        product.add("productName", name);
        product.add("minusButton", minus);
        return product;
    }
}
