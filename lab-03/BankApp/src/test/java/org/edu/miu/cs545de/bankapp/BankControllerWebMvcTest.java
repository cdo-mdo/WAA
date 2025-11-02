package org.edu.miu.cs545de.bankapp;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.edu.miu.cs545de.bankapp.api.GlobalExceptionHandler;
import org.edu.miu.cs545de.bankapp.controller.BankController;
import org.edu.miu.cs545de.bankapp.domain.BankAccount;
import org.edu.miu.cs545de.bankapp.dto.CreateAccountRequest;
import org.edu.miu.cs545de.bankapp.dto.MoneyRequest;
import org.edu.miu.cs545de.bankapp.service.AccountNotFoundException;
import org.edu.miu.cs545de.bankapp.service.BankService;
import org.edu.miu.cs545de.bankapp.service.InsufficientFundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

@WebMvcTest(controllers = {BankController.class, GlobalExceptionHandler.class})
class BankControllerWebMvcTest {

    @MockBean
    BankService bankService;

    @BeforeEach
    void setup() {
        RestAssuredMockMvc.standaloneSetup(new BankController(bankService), new GlobalExceptionHandler());
    }

    @Test
    void createAccount_ok() {
        var acc = new BankAccount(1001, "Alice");
        when(bankService.createAccount(1001, "Alice")).thenReturn(acc);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(new CreateAccountRequest(1001, "Alice"))
                .when()
                .post("/api/accounts")
                .then()
                .statusCode(201)
                .body("accountNumber", equalTo(1001))
                .body("accountHolder", equalTo("Alice"))
                .body("balance", equalTo(0)); // BigDecimal -> 0
    }

    @Test
    void deposit_ok() {
        var acc = new BankAccount(1001, "Alice");
        acc.deposit(new BigDecimal("500"));
        when(bankService.deposit(1001, new BigDecimal("500"))).thenReturn(acc);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(new MoneyRequest(500))
                .when()
                .post("/api/accounts/1001/deposit")
                .then()
                .statusCode(200)
                .body("balance", equalTo(500))
                .body("transactions.size()", equalTo(1))
                .body("transactions[0].type", equalTo("DEPOSIT"))
                .body("transactions[0].amount", equalTo(500));
    }

    @Test
    void withdraw_ok() {
        var acc = new BankAccount(1001, "Alice");
        acc.deposit(new BigDecimal("800"));
        acc.withdraw(new BigDecimal("300"));
        when(bankService.withdraw(1001, new BigDecimal("300"))).thenReturn(acc);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(new MoneyRequest(300))
                .when()
                .post("/api/accounts/1001/withdraw")
                .then()
                .statusCode(200)
                .body("balance", equalTo(500))
                .body("transactions.size()", equalTo(2))
                .body("transactions[1].type", equalTo("WITHDRAW"));
    }

    @Test
    void withdraw_insufficient() {
        when(bankService.withdraw(1001, new BigDecimal("9999")))
                .thenThrow(new InsufficientFundsException(1001));

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(new MoneyRequest(9999))
                .when()
                .post("/api/accounts/1001/withdraw")
                .then()
                .statusCode(400)
                .body("error", containsString("Insufficient funds"));
    }

    @Test
    void getAccount_ok() {
        var acc = new BankAccount(1001, "Alice");
        acc.deposit(new BigDecimal("50"));
        when(bankService.get(1001)).thenReturn(acc);

        given()
                .when()
                .get("/api/accounts/1001")
                .then()
                .statusCode(200)
                .body("accountNumber", equalTo(1001))
                .body("transactions.size()", equalTo(1))
                .body("transactions[0].type", equalTo("DEPOSIT"));
    }

    @Test
    void getAccount_notFound() {
        when(bankService.get(404)).thenThrow(new AccountNotFoundException(404));

        given()
                .when()
                .get("/api/accounts/404")
                .then()
                .statusCode(404)
                .body("error", containsString("not found"));
    }

    @Test
    void getAll_ok() {
        var a1 = new BankAccount(1, "A");
        var a2 = new BankAccount(2, "B");
        when(bankService.all()).thenReturn(List.of(a1, a2));

        given()
                .when()
                .get("/api/accounts")
                .then()
                .statusCode(200)
                .body("size()", equalTo(2))
                .body("[0].accountNumber", equalTo(1))
                .body("[1].accountNumber", equalTo(2));
    }

    @Test
    void remove_ok() {
        doNothing().when(bankService).remove(1001);

        given()
                .when()
                .delete("/api/accounts/1001")
                .then()
                .statusCode(204);

        verify(bankService, times(1)).remove(1001);
    }
}

