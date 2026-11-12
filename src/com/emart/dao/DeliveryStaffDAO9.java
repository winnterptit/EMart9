package com.emart.dao;

import com.emart.model.DeliveryStaff9;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/** DAO nhan vien giao hang. */
public class DeliveryStaffDAO9 extends DAO9 {

    /** Lay toan bo nhan vien giao hang (de chon khi duyet don). */
    public List<DeliveryStaff9> getAllDeliveryStaff() {
        List<DeliveryStaff9> list = new ArrayList<>();
        String sql = "SELECT * FROM tblDeliveryStaff9";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new DeliveryStaff9(rs.getInt("id"),
                        rs.getString("fullName"), rs.getString("phone")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
