package com.emart.model;

import java.util.ArrayList;
import java.util.List;

/** Don dat hang truc tuyen. */
public class Order9 {
    private int id;
    private String orderDate;
    private String status;                 // Chua xuat | Da xuat | Da giao
    private double totalAmount;
    private Customer9 customer;             // khach dat hang
    private DeliveryStaff9 deliveryStaff;   // NV giao hang (gan khi duyet)
    private List<OrderDetail9> listOrderDetail = new ArrayList<>();

    public Order9() {}

    public Order9(int id, String orderDate, String status, double totalAmount,
                  Customer9 customer, DeliveryStaff9 deliveryStaff) {
        this.id = id;
        this.orderDate = orderDate;
        this.status = status;
        this.totalAmount = totalAmount;
        this.customer = customer;
        this.deliveryStaff = deliveryStaff;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getOrderDate() { return orderDate; }
    public void setOrderDate(String orderDate) { this.orderDate = orderDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public Customer9 getCustomer() { return customer; }
    public void setCustomer(Customer9 customer) { this.customer = customer; }
    public DeliveryStaff9 getDeliveryStaff() { return deliveryStaff; }
    public void setDeliveryStaff(DeliveryStaff9 deliveryStaff) { this.deliveryStaff = deliveryStaff; }
    public List<OrderDetail9> getListOrderDetail() { return listOrderDetail; }
    public void setListOrderDetail(List<OrderDetail9> listOrderDetail) { this.listOrderDetail = listOrderDetail; }
}
