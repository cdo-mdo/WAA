package org.edu.miu.cs545de.springmvcapplication.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Product {

    @NotBlank(message = "Product number is required")
    @Size(min = 2, max = 5, message = "Product number must be 2–5 characters")
    private String number;

    @NotBlank(message = "Product name is required")
    @Size(min = 2, max = 20, message = "Product name must be 2–20 characters")
    private String name;

    public Product() {}
    public Product(String number, String name) {
        this.number = number;
        this.name = name;
    }

    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
