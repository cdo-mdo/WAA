package org.edu.miu.cs545de.bookapp.exception;

public class BookAlreadyExistsException extends RuntimeException {
    public BookAlreadyExistsException(String isbn) {
        super("Book with isbn " + isbn + " already exists");
    }
}

