package com.emart.model;

import java.util.ArrayList;
import java.util.List;

/** Hoa don (xuat ra khi duyet don / ban tai quay). */
public class Invoice9 {
    private int id;
    private String exportDate;
    private String status;
    private double totalAmount;
    private String note;
    private Order9 order;         // don hang nguon (ban truc tuyen)
    private Customer9 customer;   // khach hang
    private List<InvoiceDetail9> listInvoiceDetail = new ArrayList<>(); // dong hang (ban tai quay)

    public Invoice9() {}

    public Invoice9(int id, String exportDate, String status, double totalAmount,
                    String note, Order9 order, Customer9 customer) {
        this.id = id;
        this.exportDate = exportDate;
        this.status = status;
        this.totalAmount = totalAmount;
        this.note = note;
        this.order = order;
        this.customer = customer;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getExportDate() { return exportDate; }
    public void setExportDate(String exportDate) { this.exportDate = exportDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
    public Order9 getOrder() { return order; }
    public void setOrder(Order9 order) { this.order = order; }
    public Customer9 getCustomer() { return customer; }
    public void setCustomer(Customer9 customer) { this.customer = customer; }
    public List<InvoiceDetail9> getListInvoiceDetail() { return listInvoiceDetail; }
    public void setListInvoiceDetail(List<InvoiceDetail9> l) { this.listInvoiceDetail = l; }
}
