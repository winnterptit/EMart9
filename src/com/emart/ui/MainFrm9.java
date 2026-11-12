package com.emart.ui;

import com.emart.dao.UserDAO9;
import com.emart.model.User9;
import javax.swing.*;
import java.awt.*;

/** Man hinh dang nhap (MainFrm9). */
public class MainFrm9 extends JFrame {
    private final JTextField txtUser = new JTextField(18);
    private final JPasswordField txtPass = new JPasswordField(18);

    public MainFrm9() {
        setTitle("QLST DIEN MAY - DANG NHAP");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(520, 300);
        setLocationRelativeTo(null);

        JLabel title = new JLabel("HE THONG QUAN LY SIEU THI DIEN MAY", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setForeground(new Color(0, 51, 153));
        title.setBorder(BorderFactory.createEmptyBorder(16, 0, 16, 0));

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(8, 8, 8, 8);
        g.anchor = GridBagConstraints.WEST;
        g.gridx = 0; g.gridy = 0; form.add(new JLabel("Ten dang nhap:"), g);
        g.gridx = 1; form.add(txtUser, g);
        g.gridx = 0; g.gridy = 1; form.add(new JLabel("Mat khau:"), g);
        g.gridx = 1; form.add(txtPass, g);

        JButton btnLogin = new JButton("Dang nhap");
        JButton btnExit = new JButton("Thoat");
        JPanel buttons = new JPanel();
        buttons.add(btnLogin);
        buttons.add(btnExit);

        add(title, BorderLayout.NORTH);
        add(form, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        btnLogin.addActionListener(e -> doLogin());
        btnExit.addActionListener(e -> System.exit(0));
        getRootPane().setDefaultButton(btnLogin);
    }

    private void doLogin() {
        String u = txtUser.getText().trim();
        String p = new String(txtPass.getPassword());
        if (u.isEmpty() || p.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui long nhap day du ten dang nhap va mat khau!");
            return;
        }
        User9 user = new UserDAO9().checkLogin(u, p);
        if (user == null) {
            JOptionPane.showMessageDialog(this, "Sai ten dang nhap hoac mat khau!");
            return;
        }
        String role = user.getRole();
        if ("Customer".equalsIgnoreCase(role)) {
            new CustomerMainFrm9(user).setVisible(true);
            dispose();
        } else if ("WarehouseStaff".equalsIgnoreCase(role)) {
            new WarehouseMainFrm9(user).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Vai tro '" + role + "' chua duoc cai dat trong pham vi demo (2 module).");
        }
    }
}
