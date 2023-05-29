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
    private JLabel discountLabel;

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

        Basket basket1 = Basket.getBasket();
        basket1.setReceiptContainer(Receipt.generateReceipt(basket1));
        basket1.promotion();

        window.add(getBasketComponent(basket1));
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
        Product sugar = new Product( "Cukier", TaxType.B, 5.0);
        Product eggs = new Product( "Jaja ", TaxType.C, 15.50);
        Product oil = new Product( "Olej", TaxType.C, 7.99);
        Product bread = new Product( "Chleb", TaxType.B, 3.89);
        JPanel products = new JPanel(new GridLayout(5, 1, 10, 10));

        products.add(milk.getComponent(Basket.getBasket()));
        products.add(sugar.getComponent(Basket.getBasket()));
        products.add(eggs.getComponent(Basket.getBasket()));
        products.add(oil.getComponent(Basket.getBasket()));
        products.add(bread.getComponent(Basket.getBasket()));

        return products;
    }
}