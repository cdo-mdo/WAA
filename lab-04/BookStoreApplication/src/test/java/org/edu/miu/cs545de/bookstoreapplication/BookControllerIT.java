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
        // create 1
        given().contentType(ContentType.JSON).body("""
            {"isbn":"A1","author":"Robert Martin","title":"Clean Code","price":39.99}
        """).when().post("/api/books")
                .then().statusCode(201)
                .header("Location", containsString("/api/books/A1"))
                .body("isbn", equalTo("A1"));

        // create 2
        given().contentType(ContentType.JSON).body("""
            {"isbn":"B2","author":"Martin Fowler","title":"Refactoring","price":45.50}
        """).when().post("/api/books")
                .then().statusCode(201);

        // get one
        given().when().get("/api/books/A1")
                .then().statusCode(200)
                .body("title", equalTo("Clean Code"));

        // list all
        given().when().get("/api/books")
                .then().statusCode(200)
                .body("$", hasSize(2));

        // search by author (case-insensitive contains)
        given().queryParam("author", "fowler")
                .when().get("/api/books/search")
                .then().statusCode(200)
                .body("$", hasSize(1))
                .body("[0].isbn", equalTo("B2"));

        // update
        given().contentType(ContentType.JSON).body("""
            {"isbn":"IGNORED","author":"Uncle Bob","title":"Clean Code (Updated)","price":41.0}
        """).when().put("/api/books/A1")
                .then().statusCode(200)
                .body("isbn", equalTo("A1"))
                .body("author", equalTo("Uncle Bob"))
                .body("price", equalTo(41.0f));

        // delete
        given().when().delete("/api/books/B2")
                .then().statusCode(204);

        // verify not found
        given().when().get("/api/books/B2")
                .then().statusCode(404)
                .body("error", containsString("Book not found"));
    }

    @Test
    void validationErrors_onCreate() {
        // empty + negative price -> 400 + error body from controller-level handler
        given().contentType(ContentType.JSON).body("""
            {"isbn":"","author":"","title":"","price":-1}
        """).when().post("/api/books")
                .then().statusCode(400)
                .body("error", notNullValue());
    }

    @Test
    void notFound_onUpdateAndDelete() {
        // update non-existing
        given().contentType(ContentType.JSON).body("""
            {"isbn":"X","author":"Nobody","title":"Ghost","price":10}
        """).when().put("/api/books/NOPE")
                .then().statusCode(404)
                .body("error", containsString("Book not found"));

        // delete non-existing
        given().when().delete("/api/books/NOPE")
                .then().statusCode(404)
                .body("error", containsString("Book not found"));
    }
}
