package org.edu.miu.cs545de.bookstoreapplication;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerIT {

    @LocalServerPort
    int port;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    void fullCrudAndSearchFlow() {
        // 1) addBook
        String body1 = """
            {"isbn":"A1","author":"Robert Martin","title":"Clean Code","price":39.99}
            """;
        given()
                .contentType(ContentType.JSON)
                .body(body1)
                .when()
                .post("/api/books")
                .then()
                .statusCode(201)
                .header("Location", containsString("/api/books/A1"))
                .body("isbn", equalTo("A1"))
                .body("author", equalTo("Robert Martin"))
                .body("title", equalTo("Clean Code"))
                .body("price", equalTo(39.99f));

        // another book
        String body2 = """
            {"isbn":"B2","author":"Martin Fowler","title":"Refactoring","price":45.50}
            """;
        given().contentType(ContentType.JSON).body(body2)
                .when().post("/api/books")
                .then().statusCode(201);

        // 2) getBook
        given()
                .when()
                .get("/api/books/A1")
                .then()
                .statusCode(200)
                .body("title", equalTo("Clean Code"));

        // 3) getAllBooks
        given()
                .when()
                .get("/api/books")
                .then()
                .statusCode(200)
                .body("$", hasSize(2))
                .body("isbn", hasItems("A1", "B2"));

        // 4) searchBooks by author (case-insensitive contains)
        given()
                .queryParam("author", "fowler")
                .when()
                .get("/api/books/search")
                .then()
                .statusCode(200)
                .body("$", hasSize(1))
                .body("[0].isbn", equalTo("B2"));

        // 5) updateBook
        String updateBody = """
            {"isbn":"SHOULD_BE_IGNORED","author":"Uncle Bob","title":"Clean Code (Updated)","price":41.00}
            """;
        given()
                .contentType(ContentType.JSON)
                .body(updateBody)
                .when()
                .put("/api/books/A1")
                .then()
                .statusCode(200)
                .body("isbn", equalTo("A1"))
                .body("author", equalTo("Uncle Bob"))
                .body("price", equalTo(41.00f));

        // 6) deleteBook
        given()
                .when()
                .delete("/api/books/B2")
                .then()
                .statusCode(204);

        // verify deletion
        given()
                .when()
                .get("/api/books/B2")
                .then()
                .statusCode(404);

        // final list should have 1
        given()
                .when()
                .get("/api/books")
                .then()
                .statusCode(200)
                .body("$", hasSize(1))
                .body("[0].isbn", equalTo("A1"));
    }

    @Test
    void validationErrors() {
        // missing fields -> 400
        String bad = """
            {"isbn":"","author":"","title":"","price":-1}
            """;
        given()
                .contentType(ContentType.JSON)
                .body(bad)
                .when()
                .post("/api/books")
                .then()
                .statusCode(400)
                .body("error", notNullValue());
    }

    @Test
    void notFoundOnUpdateOrDelete() {
        // update non-existing
        String upd = """
            {"isbn":"X","author":"Nobody","title":"Ghost","price":10}
            """;
        given().contentType(ContentType.JSON).body(upd)
                .when().put("/api/books/NOPE")
                .then().statusCode(404);

        // delete non-existing
        given()
                .when().delete("/api/books/NOPE")
                .then().statusCode(404);
    }
}

