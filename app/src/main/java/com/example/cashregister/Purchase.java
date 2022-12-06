package com.example.cashregister;

import java.util.Date;

public class Purchase {
    private double price;
    private String productName;
    private int quantity;
    private Date date;

    //default constructor
    public Purchase(){
        this.productName = "";
        this.price = 0;
        this.quantity = 0;
        this.date = null;
    }

    //constructor
    public Purchase(String name, double price, int quantity, Date date) {
        this.productName = name;
        this.price = price;
        this.quantity = quantity;
        this.date = date;
    }

//only need setter for quantity to restock
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getProductName() {
        return productName;
    }

    public Date getDate() {
        return date;
    }
}
