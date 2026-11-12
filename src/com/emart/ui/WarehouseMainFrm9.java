package com.emart.ui;

import com.emart.model.User9;
import javax.swing.*;
import java.awt.*;

/** Trang chu nhan vien kho (WarehouseMainFrm9). */
public class WarehouseMainFrm9 extends JFrame {
    private final User9 currentUser;

    public WarehouseMainFrm9(User9 user) {
        this.currentUser = user;
        setTitle("TRANG CHU NHAN VIEN KHO");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(560, 360);
        setLocationRelativeTo(null);

        JLabel title = new JLabel("QUAN LY KHO - " + (user != null ? user.getFullName() : ""),
                SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setForeground(new Color(0, 51, 153));
        title.setBorder(BorderFactory.createEmptyBorder(16, 0, 16, 0));

        JButton btnApprove = new JButton("Duyet don + xuat kho");
        JButton btnLogout = new JButton("Dang xuat");
        btnApprove.setPreferredSize(new Dimension(240, 50));
        btnLogout.setPreferredSize(new Dimension(240, 50));

        JPanel center = new JPanel(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(10, 10, 10, 10);
        g.gridx = 0; g.gridy = 0; center.add(btnApprove, g);
        g.gridy = 1; center.add(btnLogout, g);

        add(title, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);

        btnApprove.addActionListener(e -> new PendingOrderFrm9(this).setVisible(true));
        btnLogout.addActionListener(e -> {
            new MainFrm9().setVisible(true);
            dispose();
        });
    }
}
