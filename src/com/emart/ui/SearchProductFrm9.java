package com.emart.ui;

import com.emart.dao.ProductDAO9;
import com.emart.model.Product9;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/** Man hinh tim kiem mat hang (SearchProductFrm9). */
public class SearchProductFrm9 extends JFrame {
    private final JTextField txtKeyword = new JTextField(18);
    private final JComboBox<String> cboCategory = new JComboBox<>(
            new String[]{"Tat ca", "Tivi", "Tu lanh", "May giat", "Dieu hoa", "Laptop"});
    private final DefaultTableModel model = new DefaultTableModel(
            new Object[]{"Ma", "Ten mat hang", "Danh muc", "Hang", "Don gia", "Trang thai"}, 0) {
        public boolean isCellEditable(int r, int c) { return false; }
    };
    private final JTable table = new JTable(model);
    private final ProductDAO9 dao = new ProductDAO9();

    public SearchProductFrm9(JFrame parent) {
        setTitle("TIM KIEM MAT HANG");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(820, 480);
        setLocationRelativeTo(null);

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 10));
        top.add(new JLabel("Tu khoa:"));
        top.add(txtKeyword);
        top.add(new JLabel("Danh muc:"));
        top.add(cboCategory);
        JButton btnSearch = new JButton("Tim kiem");
        JButton btnReset = new JButton("Lam moi");
        top.add(btnSearch);
        top.add(btnReset);

        JButton btnDetail = new JButton("Xem chi tiet mat hang");
        JButton btnBack = new JButton("Quay lai");
        JPanel bottom = new JPanel();
        bottom.add(btnDetail);
        bottom.add(btnBack);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        btnSearch.addActionListener(e -> doSearch());
        btnReset.addActionListener(e -> {
            txtKeyword.setText("");
            cboCategory.setSelectedIndex(0);
            model.setRowCount(0);
        });
        btnDetail.addActionListener(e -> openDetail());
        btnBack.addActionListener(e -> dispose());

        doSearch(); // nap san toan bo danh sach
    }

    private void doSearch() {
        model.setRowCount(0);
        List<Product9> list = dao.searchProducts(
                txtKeyword.getText().trim(), (String) cboCategory.getSelectedItem());
        for (Product9 p : list) {
            model.addRow(new Object[]{p.getId(), p.getName(), p.getCategory(),
                    p.getBrand(), String.format("%,.0f d", p.getPrice()), p.getStatus()});
        }
        if (list.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Khong tim thay mat hang nao khop tu khoa.");
        }
    }

    private void openDetail() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui long chon mot mat hang.");
            return;
        }
        int id = (int) model.getValueAt(row, 0);
        Product9 p = dao.getProductById(id);
        if (p != null) new ProductDetailFrm9(p).setVisible(true);
    }
}
