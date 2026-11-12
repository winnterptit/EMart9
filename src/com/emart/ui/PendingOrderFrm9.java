package com.emart.ui;

import com.emart.dao.InvoiceDAO9;
import com.emart.dao.OrderDAO9;
import com.emart.model.Invoice9;
import com.emart.model.Order9;
import com.emart.model.OrderDetail9;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/** Man hinh duyet don + xuat kho (PendingOrderFrm9). */
public class PendingOrderFrm9 extends JFrame {
    private final OrderDAO9 orderDao = new OrderDAO9();
    private final InvoiceDAO9 invoiceDao = new InvoiceDAO9();
    private List<Order9> orders;

    private final DefaultTableModel orderModel = new DefaultTableModel(
            new Object[]{"Ma don", "Ngay dat", "Khach hang", "Tong tien", "NV giao hang", "Trang thai"}, 0) {
        public boolean isCellEditable(int r, int c) { return false; }
    };
    private final JTable orderTable = new JTable(orderModel);

    private final DefaultTableModel detailModel = new DefaultTableModel(
            new Object[]{"Mat hang", "Hang", "So luong", "Don gia"}, 0) {
        public boolean isCellEditable(int r, int c) { return false; }
    };
    private final JTable detailTable = new JTable(detailModel);

    public PendingOrderFrm9(JFrame parent) {
        setTitle("DUYET DON + XUAT KHO");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(900, 560);
        setLocationRelativeTo(null);

        JLabel lbl1 = new JLabel("  Danh sach don chua xuat:");
        lbl1.setFont(new Font("Arial", Font.BOLD, 13));
        JLabel lbl2 = new JLabel("  Chi tiet don da chon:");
        lbl2.setFont(new Font("Arial", Font.BOLD, 13));

        JPanel center = new JPanel(new GridLayout(2, 1, 0, 6));
        JPanel p1 = new JPanel(new BorderLayout());
        p1.add(lbl1, BorderLayout.NORTH);
        p1.add(new JScrollPane(orderTable), BorderLayout.CENTER);
        JPanel p2 = new JPanel(new BorderLayout());
        p2.add(lbl2, BorderLayout.NORTH);
        p2.add(new JScrollPane(detailTable), BorderLayout.CENTER);
        center.add(p1);
        center.add(p2);

        JButton btnAssign = new JButton("Chon NV giao hang");
        JButton btnUpdate = new JButton("Cap nhat da xuat");
        JButton btnPrint = new JButton("In hoa don");
        JButton btnBack = new JButton("Quay lai");
        JPanel bottom = new JPanel();
        bottom.add(btnAssign);
        bottom.add(btnUpdate);
        bottom.add(btnPrint);
        bottom.add(btnBack);

        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        orderTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) showDetailOfSelected();
        });
        btnAssign.addActionListener(e -> {
            Order9 o = getSelectedOrder();
            if (o != null) new DeliveryStaffListFrm9(this, o).setVisible(true);
        });
        btnUpdate.addActionListener(e -> {
            Order9 o = getSelectedOrder();
            if (o == null) return;
            if (o.getDeliveryStaff() == null) {
                JOptionPane.showMessageDialog(this, "Hay chon nhan vien giao hang truoc khi xuat!");
                return;
            }
            if (orderDao.updateStatus(o.getId(), "Da xuat")) {
                JOptionPane.showMessageDialog(this, "Da cap nhat trang thai: Da xuat.");
                reloadOrders();
            }
        });
        btnPrint.addActionListener(e -> printInvoice());
        btnBack.addActionListener(e -> dispose());

        reloadOrders();
    }

    public final void reloadOrders() {
        orderModel.setRowCount(0);
        detailModel.setRowCount(0);
        orders = orderDao.getPendingOrders();
        for (Order9 o : orders) {
            orderModel.addRow(new Object[]{
                    o.getId(), o.getOrderDate(),
                    o.getCustomer() != null ? o.getCustomer().getFullName() : "",
                    String.format("%,.0f d", o.getTotalAmount()),
                    o.getDeliveryStaff() != null ? o.getDeliveryStaff().getFullName() : "(chua chon)",
                    o.getStatus()});
        }
    }

    private Order9 getSelectedOrder() {
        int row = orderTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui long chon mot don hang.");
            return null;
        }
        return orders.get(row);
    }

    private void showDetailOfSelected() {
        detailModel.setRowCount(0);
        int row = orderTable.getSelectedRow();
        if (row < 0) return;
        Order9 o = orders.get(row);
        List<OrderDetail9> details = orderDao.getOrderDetail(o.getId());
        o.setListOrderDetail(details);
        for (OrderDetail9 d : details) {
            detailModel.addRow(new Object[]{
                    d.getProduct().getName(), d.getProduct().getBrand(),
                    d.getQuantity(), String.format("%,.0f d", d.getPrice())});
        }
    }

    private void printInvoice() {
        Order9 o = getSelectedOrder();
        if (o == null) return;
        o.setListOrderDetail(orderDao.getOrderDetail(o.getId()));
        Invoice9 inv = invoiceDao.createInvoice(o);
        new InvoiceFrm9(o, inv).setVisible(true);
    }
}
