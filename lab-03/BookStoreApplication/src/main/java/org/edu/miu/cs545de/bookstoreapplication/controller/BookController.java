package org.edu.miu.cs545de.bookstoreapplication.controller;


import jakarta.validation.Valid;
import org.edu.miu.cs545de.bookstoreapplication.model.Book;
import org.edu.miu.cs545de.bookstoreapplication.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    // addBook(Book book)
    @PostMapping
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book, UriComponentsBuilder uriBuilder) {
        Book created = service.add(book);
        URI location = uriBuilder.path("/api/books/{isbn}").build(created.getIsbn());
        return ResponseEntity.created(location).body(created);
    }

    // updateBook(Book book)
    @PutMapping("/{isbn}")
    public ResponseEntity<Book> updateBook(@PathVariable String isbn, @Valid @RequestBody Book book) {
        return ResponseEntity.ok(service.update(isbn, book));
    }

    // deleteBook(String isbn)
    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> deleteBook(@PathVariable String isbn) {
        service.delete(isbn);
        return ResponseEntity.noContent().build();
    }

    // getBook(String isbn)
    @GetMapping("/{isbn}")
    public ResponseEntity<Book> getBook(@PathVariable String isbn) {
        return ResponseEntity.ok(service.get(isbn));
    }

    // getAllBooks()
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(service.getAll());
    }

    // searchBooks(String author)
    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam String author) {
        return ResponseEntity.ok(service.searchByAuthor(author));
    }
}

