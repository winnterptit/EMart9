package com.emart.model;

/** Chi tiet phieu nhap (lop trung gian ImportNote9 - Product9). */
public class ImportDetail9 {
    private int id;
    private int quantity;
    private double importPrice;
    private Product9 product;

    public ImportDetail9() {}

    public ImportDetail9(int id, int quantity, double importPrice, Product9 product) {
        this.id = id;
        this.quantity = quantity;
        this.importPrice = importPrice;
        this.product = product;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getImportPrice() { return importPrice; }
    public void setImportPrice(double importPrice) { this.importPrice = importPrice; }
    public Product9 getProduct() { return product; }
    public void setProduct(Product9 product) { this.product = product; }
}
