package com.example.cashregister;

public class HistoryList extends StoreItem{
    String date;

    public HistoryList(String name, double price, int quantity, String date) {
        super(name, price, quantity);
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
