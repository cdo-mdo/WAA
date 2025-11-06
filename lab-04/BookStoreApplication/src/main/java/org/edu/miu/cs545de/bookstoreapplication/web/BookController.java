package org.edu.miu.cs545de.bookstoreapplication.web;

import jakarta.validation.Valid;
import org.edu.miu.cs545de.bookstoreapplication.model.Book;
import org.edu.miu.cs545de.bookstoreapplication.service.BookNotFoundException;
import org.edu.miu.cs545de.bookstoreapplication.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService service;
    public BookController(BookService service) { this.service = service; }

    // addBook(Book book)
    @PostMapping
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book, UriComponentsBuilder uriBuilder) {
        Book created = service.add(book);
        URI location = uriBuilder.path("/api/books/{isbn}")
                .buildAndExpand(created.getIsbn())
                .toUri();
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
    public ResponseEntity<?> getAllBooks() {
        return ResponseEntity.ok(service.getAll());
    }

    // searchBooks(String author)
    @GetMapping("/search")
    public ResponseEntity<?> searchBooks(@RequestParam String author) {
        return ResponseEntity.ok(service.searchByAuthor(author));
    }

    /* ===================== Per-controller exception handlers ===================== */

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(BookNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
        var first = ex.getBindingResult().getFieldErrors().stream().findFirst();
        String msg = first.map(fe -> fe.getField() + " " + fe.getDefaultMessage())
                .orElse("Validation error");
        return ResponseEntity.badRequest().body(Map.of("error", msg));
    }
}

