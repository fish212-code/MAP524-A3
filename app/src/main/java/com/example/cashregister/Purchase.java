package com.example.cashregister;

public class Purchase {
    public String productName;
    public int quantity;
    public double totalPrice;
    public String purchaseDate;

    public Purchase(String productName, int quantity, double totalPrice, String purchaseDate) {
        this.productName = productName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.purchaseDate = purchaseDate;
    }
}
