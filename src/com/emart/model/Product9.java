package com.emart.model;

/** Mat hang (san pham dien may). */
public class Product9 {
    private int id;
    private String name;
    private String category;
    private String brand;
    private String unit;
    private double price;
    private String description;
    private String status;

    public Product9() {}

    public Product9(int id, String name, String category, String brand,
                    String unit, double price, String description, String status) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.unit = unit;
        this.price = price;
        this.description = description;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
