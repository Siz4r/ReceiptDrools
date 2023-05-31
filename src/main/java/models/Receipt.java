package models;

import models.products.Product;
import models.products.TaxType;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.stream.Collectors;

public class Receipt {
    public static JPanel generateReceipt(Basket basket) {
        JPanel receipt = new JPanel();
        receipt.setLayout(new BorderLayout(10, 10));
        receipt.setOpaque(true);
        receipt.setBackground(Color.WHITE);
        receipt.setBorder(
                BorderFactory.createTitledBorder("Paragon fiskalny")
        );

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        JPanel productsList = new JPanel(new GridLayout(basket.getProductList().size(), 4, 5, 5));
        for (Product p : basket.getProductList().stream().filter(p -> p.getAmount() > 0).collect(Collectors.toSet())) {
            productsList.add(new JLabel(p.getName()));
            productsList.add(new JLabel(p.getTaxType().name()));
            String discount = p.getDiscount() != 0 ?
                    " - " + decimalFormat.format(p.getDiscount()) + " = " +
                            (decimalFormat.format(p.getPrize() * p.getAmount() - p.getDiscount())) :
                    "";
            String builder = p.getAmount() +
                    " x " +
                    p.getPrize() +
                    " " +
                    decimalFormat.format(p.getAmount() * p.getPrize()) +
                    p.getTaxType().name() + discount;
            JLabel disc = new JLabel(builder);
            disc.setMaximumSize(new Dimension(1000, 80));
            disc.setText("<html>" + builder.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
            productsList.add(disc);

        }
        JPanel taxes = new JPanel(new GridLayout(basket.getAmountOfTaxes().size() * 2, 2, 40, 5));
        taxes.setBackground(Color.WHITE);

        for (Map.Entry<TaxType, Double> tax: basket.getAmountOfTaxes().entrySet()){
            taxes.add(new JLabel("Sprzedaż opodatkowana " + tax.getKey().name()));
            taxes.add(new JLabel(String.valueOf(decimalFormat.format(tax.getValue() * (1.0 / tax.getKey().getInterest())))));
            taxes.add(new JLabel("PTU " + tax.getKey().name() + " " + tax.getKey().getInterest() + " %"));
            taxes.add(new JLabel(decimalFormat.format(tax.getValue())));
        }
        taxes.setAlignmentX(SwingConstants.RIGHT);
        productsList.setBackground(Color.WHITE);
        receipt.add(productsList, BorderLayout.PAGE_START);
        receipt.add(taxes, BorderLayout.CENTER);
        JLabel sum = new JLabel("SUMA PLN: " + decimalFormat.format(basket.getTotalAmount()));
        sum.setFont(new Font("Serif", Font.BOLD, 18));
        sum.setHorizontalAlignment(SwingConstants.RIGHT);
        receipt.add(sum, BorderLayout.PAGE_END);
        return receipt;
    }

    private Receipt() {}
}