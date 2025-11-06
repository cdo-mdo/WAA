package org.edu.miu.cs545de.bookstoreapplication.service;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String isbn) {
        super("Book not found: " + isbn);
    }
}

