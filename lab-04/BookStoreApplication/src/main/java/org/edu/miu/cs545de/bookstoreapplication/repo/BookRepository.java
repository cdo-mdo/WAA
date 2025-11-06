package org.edu.miu.cs545de.bookstoreapplication.repo;

import org.edu.miu.cs545de.bookstoreapplication.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book save(Book book);                // insert or update
    boolean existsByIsbn(String isbn);
    Optional<Book> findByIsbn(String isbn);
    List<Book> findAll();
    void deleteByIsbn(String isbn);
    List<Book> findByAuthorContainsIgnoreCase(String author);
}

