package core;

import models.Basket;
import models.products.Milk;
import models.Receipt;

import javax.swing.*;
import java.awt.*;
import models.Basket;
import models.products.Product;
public class MainFrame {
    private Basket basket;
    public MainFrame() {
        initialize();
    }

    private void initialize() {
        JFrame window = new JFrame();
        window.setLayout(new FlowLayout());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("Sondogan");

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        //Tutaj lista dostepnych produktow w aplikacji
        basket = new Basket();
        basket.setReceiptContainer(Receipt.generateReceipt(basket));
        Milk milk = new Milk("Mleko");
        JPanel products = new JPanel(new GridLayout(5, 1, 10, 10));
        products.add(milk.getComponent());
        products.add(milk.getComponent());
        products.add(milk.getComponent());
        products.add(milk.getComponent());
        products.add(milk.getComponent());
        products.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        window.add(products);

        JPanel basket = new JPanel();
        basket.setBorder(BorderFactory.createLineBorder(Color.black));

        //Tutaj tworze koszyk, możesz w innym miejscu. Jak się wepniesz twoja decyzja
        basket.add(this.basket.getReceiptContainer());
        window.add(basket);
        window.pack();
    }
}