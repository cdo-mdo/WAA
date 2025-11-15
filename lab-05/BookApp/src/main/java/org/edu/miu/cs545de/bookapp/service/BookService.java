package org.edu.miu.cs545de.bookapp.service;

import org.edu.miu.cs545de.bookapp.dto.BookRequestDto;
import org.edu.miu.cs545de.bookapp.dto.BookResponseDto;

import java.util.List;

public interface BookService {

    BookResponseDto addBook(BookRequestDto requestDto);

    BookResponseDto updateBook(String isbn, BookRequestDto requestDto);

    void deleteBook(String isbn);

    BookResponseDto getBook(String isbn);

    List<BookResponseDto> getAllBooks();

    List<BookResponseDto> searchBooksByAuthor(String author);
}

