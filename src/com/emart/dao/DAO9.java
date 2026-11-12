package com.emart.dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * DAO9 - Lop ket noi CSDL dung chung (DBConnection).
 * Ket noi toi MySQL cua XAMPP: localhost:3306/emart_db
 * (XAMPP mac dinh: user = root, password = rong)
 */
public class DAO9 {
    public static Connection con;

    public DAO9() {
        if (con == null) {
            String dbUrl = "jdbc:mysql://localhost:3306/emart_db"
                    + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Ho_Chi_Minh";
            String dbClass = "com.mysql.cj.jdbc.Driver";
            try {
                Class.forName(dbClass);
                try {
                    con = DriverManager.getConnection(dbUrl, "root", "");
                } catch (Exception e1) {
                    try {
                        con = DriverManager.getConnection(dbUrl, "root", "root");
                    } catch (Exception e2) {
                        con = DriverManager.getConnection(dbUrl, "root", "123456");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        return con;
    }
}
