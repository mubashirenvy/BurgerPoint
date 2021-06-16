package com.example.burgerpoint;


public class Order {

    public String burger_name;
    public int qty,total_price;



    public Order() {
    }

    public Order(String burger_name, int qty, int total_price) {
        this.burger_name = burger_name;
        this.qty = qty;
        this.total_price = total_price;
    }
}