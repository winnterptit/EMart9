package com.emart.dao;

import com.emart.model.User9;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/** DAO dang nhap / nguoi dung. */
public class UserDAO9 extends DAO9 {

    /** Kiem tra dang nhap. Tra ve User9 (kem role) neu dung, nguoc lai null. */
    public User9 checkLogin(String username, String password) {
        String sql = "SELECT * FROM tblUser9 WHERE username = ? AND password = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User9(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("fullName"),
                        rs.getString("address"),
                        rs.getString("role"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
