package org.edu.miu.cs545de.bookapp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class BookRequestDto {

    @NotBlank
    private String isbn;

    @NotBlank
    private String author;

    @NotBlank
    private String title;

    @Min(0)
    private double price;

    public BookRequestDto() {
    }

    public BookRequestDto(String isbn, String author, String title, double price) {
        this.isbn = isbn;
        this.author = author;
        this.title = title;
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

