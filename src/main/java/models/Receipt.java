package models;

import javax.swing.*;
import java.awt.*;

public class Receipt {
    public static JPanel generateReceipt(Basket basket) {
        JPanel receipt = new JPanel();
        receipt.setLayout(new BorderLayout(10, 10));
        receipt.setOpaque(true);
        receipt.setBackground(Color.WHITE);
        receipt.setBorder(
                BorderFactory.createTitledBorder("Paragon fiskalny")
        );

        JPanel productsList = new JPanel(new GridLayout(basket.productsQuantity, 3, 5, 5));
        for (int i = 0; i < basket.productsQuantity; i++) {
            productsList.add(new JLabel("Nazwa produktu"));
            productsList.add(new JLabel("Jaki typ opodatkowania: (A || B || C)"));
            productsList.add(new JLabel(basket.getProductList().toString()));
        }

        JPanel taxes = new JPanel(new GridLayout(basket.taxesTypes * 2, 2, 5, 5));
        taxes.setBackground(Color.WHITE);

        taxes.add(new JLabel("Sprzedaż opodatkowana A"));
        taxes.add(new JLabel("Suma produktow z podatkiem A"));
        taxes.add(new JLabel("PTU A 23.00 %"));
        taxes.add(new JLabel("Suma podatku A"));
        taxes.add(new JLabel("Sprzedaż opodatkowana B"));
        taxes.add(new JLabel("Suma produktow z podatkiem B"));
        taxes.add(new JLabel("PTU B 8.00 %"));
        taxes.add(new JLabel("Suma podatku B"));

        productsList.setBackground(Color.WHITE);
        receipt.add(productsList, BorderLayout.PAGE_START);
        receipt.add(taxes, BorderLayout.CENTER);
        JLabel sum = new JLabel("SUMA PLN: 15.99");
        sum.setFont(new Font("Serif", Font.BOLD, 18));
        sum.setHorizontalAlignment(SwingConstants.RIGHT);
        receipt.add(sum, BorderLayout.PAGE_END);
        return receipt;
    }

    private Receipt() {}
}