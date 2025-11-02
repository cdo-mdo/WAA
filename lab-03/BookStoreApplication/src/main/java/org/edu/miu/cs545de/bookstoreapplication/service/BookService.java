package org.edu.miu.cs545de.bookstoreapplication.service;

import org.edu.miu.cs545de.bookstoreapplication.model.Book;
import org.edu.miu.cs545de.bookstoreapplication.web.BookNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final Map<String, Book> store = new ConcurrentHashMap<>();

    public Book add(Book book) {
        store.put(book.getIsbn(), book);
        return book;
    }

    public Book update(String isbn, Book book) {
        if (!store.containsKey(isbn)) throw new BookNotFoundException(isbn);
        // keep path ISBN as source of truth
        book.setIsbn(isbn);
        store.put(isbn, book);
        return book;
    }

    public void delete(String isbn) {
        if (store.remove(isbn) == null) throw new BookNotFoundException(isbn);
    }

    public Book get(String isbn) {
        Book b = store.get(isbn);
        if (b == null) throw new BookNotFoundException(isbn);
        return b;
    }

    public List<Book> getAll() {
        return new ArrayList<>(store.values());
    }

    public List<Book> searchByAuthor(String author) {
        String needle = author.toLowerCase(Locale.ROOT);
        return store.values().stream()
                .filter(b -> b.getAuthor() != null && b.getAuthor().toLowerCase(Locale.ROOT).contains(needle))
                .collect(Collectors.toList());
    }
}

