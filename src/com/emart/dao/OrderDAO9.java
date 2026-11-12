package com.emart.dao;

import com.emart.model.Customer9;
import com.emart.model.DeliveryStaff9;
import com.emart.model.Order9;
import com.emart.model.OrderDetail9;
import com.emart.model.Product9;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/** DAO don hang - phuc vu module Duyet don + xuat kho. */
public class OrderDAO9 extends DAO9 {

    /** Lay danh sach don "Chua xuat". */
    public List<Order9> getPendingOrders() {
        List<Order9> list = new ArrayList<>();
        String sql = "SELECT o.*, c.fullName AS cName, c.phone AS cPhone, c.email AS cEmail, "
                + "d.fullName AS dName "
                + "FROM tblOrder9 o "
                + "JOIN tblCustomer9 c ON o.Customer9id = c.id "
                + "LEFT JOIN tblDeliveryStaff9 d ON o.DeliveryStaff9id = d.id "
                + "WHERE o.status = 'Chua xuat'";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer9 c = new Customer9(rs.getInt("Customer9id"),
                        rs.getString("cName"), rs.getString("cPhone"), rs.getString("cEmail"));
                DeliveryStaff9 d = null;
                int dId = rs.getInt("DeliveryStaff9id");
                if (!rs.wasNull()) {
                    d = new DeliveryStaff9(dId, rs.getString("dName"), null);
                }
                Order9 o = new Order9(rs.getInt("id"), rs.getString("orderDate"),
                        rs.getString("status"), rs.getDouble("totalAmount"), c, d);
                list.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /** Lay chi tiet cac dong hang cua 1 don. */
    public List<OrderDetail9> getOrderDetail(int orderId) {
        List<OrderDetail9> list = new ArrayList<>();
        String sql = "SELECT od.*, p.name, p.category, p.brand, p.unit, p.status AS pStatus, p.description "
                + "FROM tblOrderDetail9 od JOIN tblProduct9 p ON od.Product9id = p.id "
                + "WHERE od.Order9id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product9 p = new Product9(rs.getInt("Product9id"), rs.getString("name"),
                        rs.getString("category"), rs.getString("brand"), rs.getString("unit"),
                        rs.getDouble("price"), rs.getString("description"), rs.getString("pStatus"));
                list.add(new OrderDetail9(rs.getInt("id"), rs.getInt("quantity"),
                        rs.getDouble("price"), p));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /** Gan nhan vien giao hang cho don. */
    public boolean assignDeliveryStaff(int orderId, int deliveryStaffId) {
        String sql = "UPDATE tblOrder9 SET DeliveryStaff9id = ? WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, deliveryStaffId);
            ps.setInt(2, orderId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /** Cap nhat trang thai don (vd "Da xuat"). */
    public boolean updateStatus(int orderId, String status) {
        String sql = "UPDATE tblOrder9 SET status = ? WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, orderId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
