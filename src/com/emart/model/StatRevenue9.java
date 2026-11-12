package com.emart.model;

/** Thong ke doanh thu theo khach hang (ke thua Customer9). */
public class StatRevenue9 extends Customer9 {
    private double totalRevenue;
    private int totalOrders;

    public StatRevenue9() { super(); }

    public double getTotalRevenue() { return totalRevenue; }
    public void setTotalRevenue(double totalRevenue) { this.totalRevenue = totalRevenue; }
    public int getTotalOrders() { return totalOrders; }
    public void setTotalOrders(int totalOrders) { this.totalOrders = totalOrders; }
}
