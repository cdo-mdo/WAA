package cs545de.springmvcapplication.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
public class Product {
    @NotBlank(message = "Product number is required")
    @Size(min = 2, max = 5, message = "Product number must be 2-5 characters")
    private String productNumber;

    @NotBlank(message = "Product name is required")
    @Size(min = 2, max = 20, message = "Product name must be 2-20 characters")
    private String name;

    private Double price;

    public Product(String productNumber, String name, Double price) {
        this.productNumber = productNumber;
        this.name = name;
        this.price = price;
    }

    public Product() {

    }

    public String getProductNumber() {
        return productNumber;
    }
    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
}
