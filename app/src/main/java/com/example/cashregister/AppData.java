package com.example.cashregister;

import java.util.ArrayList;

public class AppData {
    public static ArrayList<Product> products = new ArrayList<>();
    public static ArrayList<Purchase> purchaseHistory = new ArrayList<>();

    public static void initProducts() {
        if (products.isEmpty()) {
            products.add(new Product("Ballpoint Pen", 20, 2.40));
            products.add(new Product("Highlighter", 12, 2.40));
            products.add(new Product("Permanent Marker", 15, 2.90));
            products.add(new Product("Mechanical Pencil", 16, 12.90));
            products.add(new Product("Glue Stick", 12, 0.90));
            products.add(new Product("Sticky Notes", 18, 2.90));
            products.add(new Product("Notepad", 30, 3.90));
            products.add(new Product("Ruler", 15, 4.90));
            products.add(new Product("Lined Notebook", 10, 8.90));
            products.add(new Product("Stapler", 8, 7.90));
        }
    }
}
