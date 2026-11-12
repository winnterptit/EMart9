package com.emart.dao;

import com.emart.model.Product9;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/** DAO mat hang - phuc vu module Tim kiem mat hang. */
public class ProductDAO9 extends DAO9 {

    private Product9 map(ResultSet rs) throws Exception {
        return new Product9(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("category"),
                rs.getString("brand"),
                rs.getString("unit"),
                rs.getDouble("price"),
                rs.getString("description"),
                rs.getString("status"));
    }

    /** Tim mat hang theo tu khoa (ten chua tu khoa) va danh muc. */
    public List<Product9> searchProducts(String keyword, String category) {
        List<Product9> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM tblProduct9 WHERE name LIKE ?");
        boolean hasCategory = category != null && !category.isEmpty()
                && !category.equalsIgnoreCase("Tat ca");
        if (hasCategory) sql.append(" AND category = ?");
        try {
            PreparedStatement ps = con.prepareStatement(sql.toString());
            ps.setString(1, "%" + (keyword == null ? "" : keyword) + "%");
            if (hasCategory) ps.setString(2, category);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(map(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /** Lay chi tiet 1 mat hang theo id. */
    public Product9 getProductById(int id) {
        String sql = "SELECT * FROM tblProduct9 WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return map(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
