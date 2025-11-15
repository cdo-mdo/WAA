package org.edu.miu.cs545de.bookapp.controller;

import io.restassured.RestAssured;
import org.edu.miu.cs545de.bookapp.dto.BookRequestDto;
import org.edu.miu.cs545de.bookapp.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class BookControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        bookRepository.deleteAll();
    }

    @Test
    void addBook_shouldCreateBook() {
        BookRequestDto request = new BookRequestDto("123", "Author A", "Title A", 10.5);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/api/books")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("isbn", equalTo("123"))
                .body("author", equalTo("Author A"))
                .body("title", equalTo("Title A"))
                .body("price", equalTo(10.5F));
    }

    @Test
    void getBook_shouldReturnBook() {
        // First create
        BookRequestDto request = new BookRequestDto("123", "Author A", "Title A", 10.5);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/api/books")
                .then()
                .statusCode(HttpStatus.CREATED.value());

        // Then get
        given()
                .when()
                .get("/api/books/{isbn}", "123")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("isbn", equalTo("123"))
                .body("author", equalTo("Author A"));
    }

    @Test
    void updateBook_shouldUpdateExistingBook() {
        // Create
        BookRequestDto request = new BookRequestDto("123", "Author A", "Title A", 10.5);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/api/books")
                .then()
                .statusCode(HttpStatus.CREATED.value());

        // Update
        BookRequestDto update = new BookRequestDto("123", "Author B", "Title B", 20.0);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(update)
                .when()
                .put("/api/books/{isbn}", "123")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("author", equalTo("Author B"))
                .body("title", equalTo("Title B"))
                .body("price", equalTo(20.0F));
    }

    @Test
    void deleteBook_shouldRemoveBook() {
        // Create
        BookRequestDto request = new BookRequestDto("123", "Author A", "Title A", 10.5);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/api/books")
                .then()
                .statusCode(HttpStatus.CREATED.value());

        // Delete
        given()
                .when()
                .delete("/api/books/{isbn}", "123")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());

        // Verify not found
        given()
                .when()
                .get("/api/books/{isbn}", "123")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void getAllBooks_shouldReturnList() {
        BookRequestDto request1 = new BookRequestDto("111", "Author A", "Title A", 10.5);
        BookRequestDto request2 = new BookRequestDto("222", "Author B", "Title B", 12.0);

        given().contentType(MediaType.APPLICATION_JSON_VALUE).body(request1)
                .when().post("/api/books").then().statusCode(HttpStatus.CREATED.value());

        given().contentType(MediaType.APPLICATION_JSON_VALUE).body(request2)
                .when().post("/api/books").then().statusCode(HttpStatus.CREATED.value());

        given()
                .when()
                .get("/api/books")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("size()", is(2));
    }

    @Test
    void searchBooksByAuthor_shouldReturnMatchingBooks() {
        BookRequestDto request1 = new BookRequestDto("111", "John Smith", "Title 1", 10.5);
        BookRequestDto request2 = new BookRequestDto("222", "Johnny Walker", "Title 2", 12.0);
        BookRequestDto request3 = new BookRequestDto("333", "Other Author", "Title 3", 15.0);

        given().contentType(MediaType.APPLICATION_JSON_VALUE).body(request1)
                .when().post("/api/books").then().statusCode(HttpStatus.CREATED.value());

        given().contentType(MediaType.APPLICATION_JSON_VALUE).body(request2)
                .when().post("/api/books").then().statusCode(HttpStatus.CREATED.value());

        given().contentType(MediaType.APPLICATION_JSON_VALUE).body(request3)
                .when().post("/api/books").then().statusCode(HttpStatus.CREATED.value());

        given()
                .queryParam("author", "john")
                .when()
                .get("/api/books/search")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("size()", is(2));
    }
}
