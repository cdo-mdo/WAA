package org.edu.miu.cs545de.bookapp.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String isbn) {
        super("Book with isbn " + isbn + " not found");
    }
}

