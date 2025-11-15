package org.edu.miu.cs545de.bankapp.controller;

import io.restassured.RestAssured;
import org.edu.miu.cs545de.bankapp.dto.CreateAccountRequest;
import org.edu.miu.cs545de.bankapp.dto.MoneyOperationRequest;
import org.edu.miu.cs545de.bankapp.repository.BankAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BankAccountControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private BankAccountRepository repository;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
        repository.deleteAll();
    }

    @Test
    void createAccount_and_getAccount() {
        CreateAccountRequest createReq = new CreateAccountRequest();
        createReq.setAccountNumber(1001);
        createReq.setAccountHolder("John Doe");

        // createAccount
        given()
                .contentType("application/json")
                .body(createReq)
                .when()
                .post("/api/accounts")
                .then()
                .statusCode(201)
                .body("accountNumber", equalTo(1001))
                .body("accountHolder", equalTo("John Doe"))
                .body("balance", comparesEqualTo(0));

        // getAccount
        given()
                .when()
                .get("/api/accounts/{accountNumber}", 1001)
                .then()
                .statusCode(200)
                .body("accountNumber", equalTo(1001))
                .body("balance", comparesEqualTo(0));
    }

    @Test
    void deposit_and_withdraw_shouldTrackTransactions() {
        // create account
        CreateAccountRequest createReq = new CreateAccountRequest();
        createReq.setAccountNumber(2001);
        createReq.setAccountHolder("Alice");

        given()
                .contentType("application/json")
                .body(createReq)
                .when()
                .post("/api/accounts")
                .then()
                .statusCode(201);

        // deposit 100
        MoneyOperationRequest depositReq = new MoneyOperationRequest();
        depositReq.setAmount(new BigDecimal("100.00"));

        given()
                .contentType("application/json")
                .body(depositReq)
                .when()
                .post("/api/accounts/{accountNumber}/deposit", 2001)
                .then()
                .statusCode(200)
                .body("balance", comparesEqualTo(100));

        // withdraw 40
        MoneyOperationRequest withdrawReq = new MoneyOperationRequest();
        withdrawReq.setAmount(new BigDecimal("40.00"));

        given()
                .contentType("application/json")
                .body(withdrawReq)
                .when()
                .post("/api/accounts/{accountNumber}/withdraw", 2001)
                .then()
                .statusCode(200)
                .body("balance", comparesEqualTo(60));

        // check transactions list via getAccount
        given()
                .when()
                .get("/api/accounts/{accountNumber}", 2001)
                .then()
                .statusCode(200)
                .body("transactions", hasSize(2))
                .body("transactions[0].type", anyOf(equalTo("DEPOSIT"), equalTo("WITHDRAWAL")))
                .body("transactions[0].amount", anyOf(comparesEqualTo(100), comparesEqualTo(40)))
                .body("transactions[1].type", anyOf(equalTo("DEPOSIT"), equalTo("WITHDRAWAL")));
    }

    @Test
    void withdraw_withInsufficientBalance_shouldReturnBadRequest() {
        // create account
        CreateAccountRequest createReq = new CreateAccountRequest();
        createReq.setAccountNumber(3001);
        createReq.setAccountHolder("Bob");

        given()
                .contentType("application/json")
                .body(createReq)
                .when()
                .post("/api/accounts")
                .then()
                .statusCode(201);

        // try to withdraw 10 (no money)
        MoneyOperationRequest withdrawReq = new MoneyOperationRequest();
        withdrawReq.setAmount(new BigDecimal("10.00"));

        given()
                .contentType("application/json")
                .body(withdrawReq)
                .when()
                .post("/api/accounts/{accountNumber}/withdraw", 3001)
                .then()
                .statusCode(400) // InsufficientBalanceException -> BAD_REQUEST
                .body("error", equalTo("Insufficient Balance"));
    }
}
