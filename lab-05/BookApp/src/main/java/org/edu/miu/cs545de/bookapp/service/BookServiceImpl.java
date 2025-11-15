package org.edu.miu.cs545de.bookapp.service;

import org.edu.miu.cs545de.bookapp.dto.BookRequestDto;
import org.edu.miu.cs545de.bookapp.dto.BookResponseDto;
import org.edu.miu.cs545de.bookapp.exception.BookAlreadyExistsException;
import org.edu.miu.cs545de.bookapp.exception.BookNotFoundException;
import org.edu.miu.cs545de.bookapp.model.Book;
import org.edu.miu.cs545de.bookapp.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    private BookResponseDto toDto(Book book) {
        return new BookResponseDto(
                book.getId(),
                book.getIsbn(),
                book.getAuthor(),
                book.getTitle(),
                book.getPrice()
        );
    }

    @Override
    public BookResponseDto addBook(BookRequestDto requestDto) {
        if (bookRepository.existsByIsbn(requestDto.getIsbn())) {
            throw new BookAlreadyExistsException(requestDto.getIsbn());
        }
        Book book = new Book(
                requestDto.getIsbn(),
                requestDto.getAuthor(),
                requestDto.getTitle(),
                requestDto.getPrice()
        );
        return toDto(bookRepository.save(book));
    }

    @Override
    public BookResponseDto updateBook(String isbn, BookRequestDto requestDto) {
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));

        book.setAuthor(requestDto.getAuthor());
        book.setTitle(requestDto.getTitle());
        book.setPrice(requestDto.getPrice());

        return toDto(bookRepository.save(book));
    }

    @Override
    public void deleteBook(String isbn) {
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));
        bookRepository.delete(book);
    }

    @Override
    public BookResponseDto getBook(String isbn) {
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));
        return toDto(book);
    }

    @Override
    public List<BookResponseDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public List<BookResponseDto> searchBooksByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author)
                .stream()
                .map(this::toDto)
                .toList();
    }
}

