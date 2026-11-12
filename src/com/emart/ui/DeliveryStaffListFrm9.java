package com.emart.ui;

import com.emart.dao.DeliveryStaffDAO9;
import com.emart.dao.OrderDAO9;
import com.emart.model.DeliveryStaff9;
import com.emart.model.Order9;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/** Man hinh danh sach + chon nhan vien giao hang (DeliveryStaffListFrm9). */
public class DeliveryStaffListFrm9 extends JFrame {
    private final PendingOrderFrm9 parent;
    private final Order9 order;
    private final DeliveryStaffDAO9 dao = new DeliveryStaffDAO9();
    private final OrderDAO9 orderDao = new OrderDAO9();
    private List<DeliveryStaff9> staffs;

    private final DefaultTableModel model = new DefaultTableModel(
            new Object[]{"Ma", "Ho ten", "So dien thoai"}, 0) {
        public boolean isCellEditable(int r, int c) { return false; }
    };
    private final JTable table = new JTable(model);

    public DeliveryStaffListFrm9(PendingOrderFrm9 parent, Order9 order) {
        this.parent = parent;
        this.order = order;
        setTitle("CHON NHAN VIEN GIAO HANG - Don #" + order.getId());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(560, 380);
        setLocationRelativeTo(null);

        JButton btnChoose = new JButton("Chon");
        JButton btnBack = new JButton("Quay lai");
        JPanel bottom = new JPanel();
        bottom.add(btnChoose);
        bottom.add(btnBack);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        btnChoose.addActionListener(e -> choose());
        btnBack.addActionListener(e -> dispose());

        loadStaff();
    }

    private void loadStaff() {
        model.setRowCount(0);
        staffs = dao.getAllDeliveryStaff();
        for (DeliveryStaff9 s : staffs) {
            model.addRow(new Object[]{s.getId(), s.getFullName(), s.getPhone()});
        }
    }

    private void choose() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui long chon mot nhan vien giao hang.");
            return;
        }
        DeliveryStaff9 s = staffs.get(row);
        if (orderDao.assignDeliveryStaff(order.getId(), s.getId())) {
            order.setDeliveryStaff(s);               // cap nhat vao doi tuong don
            JOptionPane.showMessageDialog(this,
                    "Da gan '" + s.getFullName() + "' cho don #" + order.getId());
            parent.reloadOrders();                   // lam moi danh sach don ben PendingOrderFrm9
            dispose();
        }
    }
}
