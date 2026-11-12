package com.emart.dao;

import com.emart.model.Invoice9;
import com.emart.model.Order9;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

/** DAO hoa don - tao/in hoa don tu don hang da duyet. */
public class InvoiceDAO9 extends DAO9 {

    /** Tao hoa don tu 1 don hang da duyet; tra ve Invoice9 vua tao. */
    public Invoice9 createInvoice(Order9 order) {
        // Neu don da co hoa don thi lay lai
        Invoice9 existed = getInvoiceByOrder(order.getId());
        if (existed != null) return existed;

        String sql = "INSERT INTO tblInvoice9 (exportDate, status, totalAmount, note, Order9id, Customer9id) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            ps.setString(2, "Da xuat");
            ps.setDouble(3, order.getTotalAmount());
            ps.setString(4, "Hoa don xuat kho + giao hang");
            ps.setInt(5, order.getId());
            ps.setInt(6, order.getCustomer() != null ? order.getCustomer().getId() : 0);
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            int newId = 0;
            if (keys.next()) newId = keys.getInt(1);
            Invoice9 inv = new Invoice9(newId, String.valueOf(new Timestamp(System.currentTimeMillis())),
                    "Da xuat", order.getTotalAmount(), "Hoa don xuat kho + giao hang",
                    order, order.getCustomer());
            return inv;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /** Lay hoa don theo don hang. */
    public Invoice9 getInvoiceByOrder(int orderId) {
        String sql = "SELECT * FROM tblInvoice9 WHERE Order9id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Invoice9 inv = new Invoice9();
                inv.setId(rs.getInt("id"));
                inv.setExportDate(rs.getString("exportDate"));
                inv.setStatus(rs.getString("status"));
                inv.setTotalAmount(rs.getDouble("totalAmount"));
                inv.setNote(rs.getString("note"));
                return inv;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
