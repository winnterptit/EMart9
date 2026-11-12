package com.emart.model;

import java.util.ArrayList;
import java.util.List;

/** Phieu nhap hang tu nha cung cap. */
public class ImportNote9 {
    private int id;
    private String importDate;
    private double totalAmount;
    private Supplier9 supplier;
    private List<ImportDetail9> listImportDetail = new ArrayList<>();

    public ImportNote9() {}

    public ImportNote9(int id, String importDate, double totalAmount, Supplier9 supplier) {
        this.id = id;
        this.importDate = importDate;
        this.totalAmount = totalAmount;
        this.supplier = supplier;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getImportDate() { return importDate; }
    public void setImportDate(String importDate) { this.importDate = importDate; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public Supplier9 getSupplier() { return supplier; }
    public void setSupplier(Supplier9 supplier) { this.supplier = supplier; }
    public List<ImportDetail9> getListImportDetail() { return listImportDetail; }
    public void setListImportDetail(List<ImportDetail9> l) { this.listImportDetail = l; }
}
