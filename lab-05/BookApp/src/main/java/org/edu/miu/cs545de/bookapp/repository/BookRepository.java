package org.edu.miu.cs545de.bookapp.repository;

import org.edu.miu.cs545de.bookapp.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String> {

    Optional<Book> findByIsbn(String isbn);

    List<Book> findByAuthorContainingIgnoreCase(String author);

    void deleteByIsbn(String isbn);

    boolean existsByIsbn(String isbn);
}

