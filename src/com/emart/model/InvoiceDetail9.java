package com.emart.model;

/** Chi tiet hoa don (lop trung gian Invoice9 - Product9, dung cho ban tai quay). */
public class InvoiceDetail9 {
    private int id;
    private int quantity;
    private double price;
    private Product9 product;

    public InvoiceDetail9() {}

    public InvoiceDetail9(int id, int quantity, double price, Product9 product) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.product = product;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public Product9 getProduct() { return product; }
    public void setProduct(Product9 product) { this.product = product; }
}
