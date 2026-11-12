package com.emart.model;

/** Thong ke nha cung cap (ke thua Supplier9). */
public class StatSupplier9 extends Supplier9 {
    private int totalImportQuantity;
    private double totalImportAmount;

    public StatSupplier9() { super(); }

    public int getTotalImportQuantity() { return totalImportQuantity; }
    public void setTotalImportQuantity(int v) { this.totalImportQuantity = v; }
    public double getTotalImportAmount() { return totalImportAmount; }
    public void setTotalImportAmount(double v) { this.totalImportAmount = v; }
}
