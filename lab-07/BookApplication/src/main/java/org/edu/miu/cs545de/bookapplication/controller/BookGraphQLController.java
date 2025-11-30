package org.edu.miu.cs545de.bookapplication.controller;

import org.edu.miu.cs545de.bookapplication.model.Book;
import org.edu.miu.cs545de.bookapplication.service.BookService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookGraphQLController {

    private final BookService bookService;

    public BookGraphQLController(BookService bookService) {
        this.bookService = bookService;
    }

    @QueryMapping
    public List<Book> books() {
        return bookService.getAllBooks();
    }

    @QueryMapping
    public Book bookById(@Argument Long id) {
        return bookService.getBookById(id);
    }

    @MutationMapping
    public Book addBook(
            @Argument String title,
            @Argument String author,
            @Argument String isbn,
            @Argument double price
    ) {
        return bookService.addBook(title, author, isbn, price);
    }

    @MutationMapping
    public Boolean deleteBook(@Argument Long id) {
        return bookService.deleteBook(id);
    }
}
