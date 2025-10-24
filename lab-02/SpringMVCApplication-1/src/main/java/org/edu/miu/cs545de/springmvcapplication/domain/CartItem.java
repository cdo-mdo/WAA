package org.edu.miu.cs545de.springmvcapplication.domain;

public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product) {
        this.product = product;
        this.quantity = 1;
    }
    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public void increment() { quantity++; }
    public void decrement() { if (quantity > 0) quantity--; }
}

