package org.edu.miu.cs545de.bookstoreapplication.service;

import org.edu.miu.cs545de.bookstoreapplication.model.Book;
import org.edu.miu.cs545de.bookstoreapplication.repo.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repo;

    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    public Book add(Book book) {
        // upsert is fine; if you must fail on duplicate, uncomment below:
        // if (repo.existsByIsbn(book.getIsbn())) throw new IllegalArgumentException("ISBN already exists");
        return repo.save(book);
    }

    public Book update(String pathIsbn, Book book) {
        return repo.findByIsbn(pathIsbn)
                .map(existing -> {
                    // force consistency with path
                    book.setIsbn(pathIsbn);
                    return repo.save(book);
                })
                .orElseThrow(() -> new BookNotFoundException(pathIsbn));
    }

    public void delete(String isbn) {
        if (!repo.existsByIsbn(isbn)) throw new BookNotFoundException(isbn);
        repo.deleteByIsbn(isbn);
    }

    public Book get(String isbn) {
        return repo.findByIsbn(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
    }

    public List<Book> getAll() { return repo.findAll(); }

    public List<Book> searchByAuthor(String author) {
        return repo.findByAuthorContainsIgnoreCase(author);
    }
}
