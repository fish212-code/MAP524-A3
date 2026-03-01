package com.example.cashregister;

public class Product {
    public String name;
    public int quantity;
    public double price;

    public Product(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName()    { return name; }
    public int getQuantity()   { return quantity; }
    public double getPrice()   { return price; }

}
