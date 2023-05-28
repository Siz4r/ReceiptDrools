package core;

import models.Basket;
import models.Receipt;
import models.products.Product;
import models.products.TaxType;

import javax.swing.*;
import java.awt.*;

public class MainFrame {
    public MainFrame() {
        initialize();
    }

    private void initialize() {
        JFrame window = new JFrame();
        window.setLayout(new FlowLayout());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("Paragon");

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        JPanel products = getProducts();
        products.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        window.add(products);

        Basket basket = Basket.getBasket();
        basket.setReceiptContainer(Receipt.generateReceipt(basket));

        window.add(getBasketComponent(basket));
        window.pack();
    }

    private static Component getBasketComponent(Basket basket) {
        JPanel basketComponent = new JPanel();
        basketComponent.setBorder(BorderFactory.createLineBorder(Color.black));
        basketComponent.add(basket.getReceiptContainer());

        return basketComponent;
    }

    private static JPanel getProducts() {
        Product milk = new Product("Mleko", TaxType.A, 2.99);
        JPanel products = new JPanel(new GridLayout(5, 1, 10, 10));

        products.add(milk.getComponent(Basket.getBasket()));
        products.add(milk.getComponent(Basket.getBasket()));
        products.add(milk.getComponent(Basket.getBasket()));
        products.add(milk.getComponent(Basket.getBasket()));
        products.add(milk.getComponent(Basket.getBasket()));

        return products;
    }
}