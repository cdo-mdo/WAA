package org.edu.miu.cs545de.bookapplication.service;

import org.edu.miu.cs545de.bookapplication.model.Book;
import org.edu.miu.cs545de.bookapplication.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository repo;

    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    public List<Book> getAllBooks() {
        return repo.findAll();
    }

    public Book getBookById(Long id) {
        Optional<Book> opt = repo.findById(id);
        return opt.orElse(null); // GraphQL will return null if not found
    }

    public Book addBook(String title, String author, String isbn, double price) {
        Book book = new Book(null, title, author, isbn, price);
        return repo.save(book);
    }

    public boolean deleteBook(Long id) {
        return repo.deleteById(id);
    }
}

