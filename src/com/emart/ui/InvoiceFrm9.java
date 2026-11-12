package com.emart.ui;

import com.emart.model.Invoice9;
import com.emart.model.Order9;
import com.emart.model.OrderDetail9;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/** Man hinh hoa don (InvoiceFrm9). */
public class InvoiceFrm9 extends JFrame {

    public InvoiceFrm9(Order9 order, Invoice9 invoice) {
        setTitle("HOA DON XUAT KHO");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(640, 480);
        setLocationRelativeTo(null);

        JLabel title = new JLabel("HOA DON BAN HANG", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setForeground(new Color(0, 51, 153));
        title.setBorder(BorderFactory.createEmptyBorder(12, 0, 12, 0));

        JPanel head = new JPanel(new GridLayout(0, 2, 8, 4));
        head.setBorder(BorderFactory.createEmptyBorder(0, 16, 8, 16));
        head.add(new JLabel("Ma hoa don: " + (invoice != null ? invoice.getId() : "")));
        head.add(new JLabel("Ma don: " + order.getId()));
        head.add(new JLabel("Khach hang: "
                + (order.getCustomer() != null ? order.getCustomer().getFullName() : "")));
        head.add(new JLabel("NV giao hang: "
                + (order.getDeliveryStaff() != null ? order.getDeliveryStaff().getFullName() : "")));
        head.add(new JLabel("Ngay xuat: " + (invoice != null ? invoice.getExportDate() : "")));
        head.add(new JLabel("Tong tien: " + String.format("%,.0f d", order.getTotalAmount())));

        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"Mat hang", "Hang", "So luong", "Don gia"}, 0);
        if (order.getListOrderDetail() != null) {
            for (OrderDetail9 d : order.getListOrderDetail()) {
                model.addRow(new Object[]{d.getProduct().getName(), d.getProduct().getBrand(),
                        d.getQuantity(), String.format("%,.0f d", d.getPrice())});
            }
        }
        JTable table = new JTable(model);

        JButton btnPrint = new JButton("In");
        JButton btnBack = new JButton("Quay lai");
        JPanel bottom = new JPanel();
        bottom.add(btnPrint);
        bottom.add(btnBack);
        btnPrint.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Da gui hoa don toi may in (gia lap)."));
        btnBack.addActionListener(e -> dispose());

        JPanel north = new JPanel(new BorderLayout());
        north.add(title, BorderLayout.NORTH);
        north.add(head, BorderLayout.CENTER);

        add(north, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }
}
