package org.edu.miu.cs545de.bookapp.controller;

import jakarta.validation.Valid;
import org.edu.miu.cs545de.bookapp.dto.BookRequestDto;
import org.edu.miu.cs545de.bookapp.dto.BookResponseDto;
import org.edu.miu.cs545de.bookapp.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookResponseDto> addBook(@Valid @RequestBody BookRequestDto requestDto) {
        BookResponseDto saved = bookService.addBook(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<BookResponseDto> updateBook(
            @PathVariable String isbn,
            @Valid @RequestBody BookRequestDto requestDto) {
        BookResponseDto updated = bookService.updateBook(isbn, requestDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> deleteBook(@PathVariable String isbn) {
        bookService.deleteBook(isbn);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<BookResponseDto> getBook(@PathVariable String isbn) {
        BookResponseDto dto = bookService.getBook(isbn);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookResponseDto>> searchByAuthor(@RequestParam String author) {
        return ResponseEntity.ok(bookService.searchBooksByAuthor(author));
    }
}

