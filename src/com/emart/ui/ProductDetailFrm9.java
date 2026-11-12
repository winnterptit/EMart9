package com.emart.ui;

import com.emart.model.Product9;
import javax.swing.*;
import java.awt.*;

/** Man hinh chi tiet mat hang (ProductDetailFrm9). */
public class ProductDetailFrm9 extends JFrame {

    public ProductDetailFrm9(Product9 p) {
        setTitle("CHI TIET MAT HANG");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(520, 420);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(8, 10, 8, 10);
        g.anchor = GridBagConstraints.WEST;

        addRow(panel, g, 0, "Ma mat hang:", String.valueOf(p.getId()));
        addRow(panel, g, 1, "Ten:", p.getName());
        addRow(panel, g, 2, "Danh muc:", p.getCategory());
        addRow(panel, g, 3, "Hang san xuat:", p.getBrand());
        addRow(panel, g, 4, "Don vi tinh:", p.getUnit());
        addRow(panel, g, 5, "Don gia:", String.format("%,.0f d", p.getPrice()));
        addRow(panel, g, 6, "Trang thai:", p.getStatus());
        addRow(panel, g, 7, "Mo ta:", p.getDescription());

        JButton btnBack = new JButton("Quay lai");
        JPanel bottom = new JPanel();
        bottom.add(btnBack);
        btnBack.addActionListener(e -> dispose());

        add(panel, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }

    private void addRow(JPanel panel, GridBagConstraints g, int row, String label, String value) {
        g.gridx = 0; g.gridy = row;
        JLabel l = new JLabel(label);
        l.setFont(new Font("Arial", Font.BOLD, 13));
        panel.add(l, g);
        g.gridx = 1;
        panel.add(new JLabel(value == null ? "" : value), g);
    }
}
