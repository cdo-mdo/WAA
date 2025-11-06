package org.edu.miu.cs545de.bookstoreapplication.repo;

import org.edu.miu.cs545de.bookstoreapplication.model.Book;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryBookRepository implements BookRepository {

    private final Map<String, Book> store = new ConcurrentHashMap<>();

    @Override
    public Book save(Book book) {
        store.put(book.getIsbn(), book);
        return book;
    }

    @Override
    public boolean existsByIsbn(String isbn) {
        return store.containsKey(isbn);
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return Optional.ofNullable(store.get(isbn));
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void deleteByIsbn(String isbn) {
        store.remove(isbn);
    }

    @Override
    public List<Book> findByAuthorContainsIgnoreCase(String author) {
        String needle = author.toLowerCase(Locale.ROOT);
        return store.values().stream()
                .filter(b -> b.getAuthor() != null &&
                        b.getAuthor().toLowerCase(Locale.ROOT).contains(needle))
                .toList();
    }
}

