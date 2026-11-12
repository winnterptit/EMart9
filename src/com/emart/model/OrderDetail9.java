package com.emart.model;

/** Chi tiet don hang (lop trung gian Order9 - Product9). */
public class OrderDetail9 {
    private int id;
    private int quantity;
    private double price;
    private Product9 product;   // mat hang duoc dat

    public OrderDetail9() {}

    public OrderDetail9(int id, int quantity, double price, Product9 product) {
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
