package com.emart.model;

/** Thong ke mat hang (ke thua Product9). */
public class StatProduct9 extends Product9 {
    private double totalRevenue;
    private int totalQuantitySold;

    public StatProduct9() { super(); }

    public double getTotalRevenue() { return totalRevenue; }
    public void setTotalRevenue(double totalRevenue) { this.totalRevenue = totalRevenue; }
    public int getTotalQuantitySold() { return totalQuantitySold; }
    public void setTotalQuantitySold(int totalQuantitySold) { this.totalQuantitySold = totalQuantitySold; }
}
