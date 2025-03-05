package com.example.assignment1;

public class Transaction {
    private String date, name, price, quantity, user;

    public Transaction(String date, String name, String price, String quantity, String user) {
        this.user = user;
        this.date = date;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }
    public String getName() {
        return name;
    }
    public String getPrice() {
        return price;
    }
    public String getQuantity() {
        return quantity;
    }
    public String getUser() {
        return user;
    }
}
