package org.edu.miu.cs545de.bankcontrollerapplication.api;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.edu.miu.cs545de.bankcontrollerapplication.domain.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.math.BigDecimal;
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import org.edu.miu.cs545de.bankcontrollerapplication.service.BankService;
import org.edu.miu.cs545de.bankcontrollerapplication.service.InsufficientFundsException;
import org.edu.miu.cs545de.bankcontrollerapplication.service.AccountNotFoundException;

/**
 * Standalone MockMvc + RestAssured using Spring's org.springframework.validation.Validator.
 * This activates @Valid on @RequestBody with our GlobalExceptionHandler.
 * (Method-level validation on @PathVariable is covered by your RANDOM_PORT IT tests.)
 */
@ExtendWith(MockitoExtension.class)
class BankControllerStandaloneTest {

    @Mock
    BankService bankService;

    @InjectMocks
    BankController bankController;

    private Validator springValidator;

    @BeforeEach
    void setup() {
        // Use Spring's Validator (org.springframework.validation.Validator)
        LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
        factoryBean.afterPropertiesSet();            // initialize JSR-380 provider under the hood
        this.springValidator = factoryBean;

        var mockMvc = MockMvcBuilders
                .standaloneSetup(bankController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .setValidator(springValidator)       // <- Spring Validator here
                .build();

        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    void create_ok() {
        var acc = new BankAccount(1, "Alice");
        when(bankService.createAccount(1, "Alice")).thenReturn(acc);

        given().contentType("application/json")
                .body("{\"accountNumber\":1,\"accountHolder\":\"Alice\"}")
                .when().post("/api/accounts")
                .then().statusCode(201)
                .body("accountNumber", equalTo(1))
                .body("accountHolder", equalTo("Alice"))
                .body("balance", equalTo(0));
    }

    @Test
    void create_validation_error_from_body_uses_spring_validator() {
        // accountHolder blank -> @NotBlank should trigger through Spring Validator
        given().contentType("application/json")
                .body("{\"accountNumber\":5,\"accountHolder\":\"   \"}")
                .when().post("/api/accounts")
                .then().statusCode(400)
                .body("error", equalTo("Validation failed"))
                .body("fields.accountHolder", containsString("required"));
        verifyNoInteractions(bankService);
    }

    @Test
    void deposit_ok() {
        var acc = new BankAccount(1, "A");
        acc.deposit(new BigDecimal("50"));
        when(bankService.deposit(1, new BigDecimal("50"))).thenReturn(acc);

        given().contentType("application/json")
                .body("{\"amount\":50}")
                .when().post("/api/accounts/1/deposit")
                .then().statusCode(200)
                .body("balance", equalTo(50))
                .body("transactions.size()", equalTo(1))
                .body("transactions[0].type", equalTo("DEPOSIT"));
    }

    @Test
    void deposit_validation_error_amount_must_be_positive() {
        given().contentType("application/json")
                .body("{\"amount\":0}")
                .when().post("/api/accounts/1/deposit")
                .then().statusCode(400)
                .body("error", equalTo("Validation failed"))
                .body("fields.amount", containsString(">= 0.01"));
        verifyNoInteractions(bankService);
    }

    @Test
    void withdraw_insufficient() {
        when(bankService.withdraw(1, new BigDecimal("9999")))
                .thenThrow(new InsufficientFundsException(1));

        given().contentType("application/json")
                .body("{\"amount\":9999}")
                .when().post("/api/accounts/1/withdraw")
                .then().statusCode(400)
                .body("error", containsString("Insufficient"));
    }

    @Test
    void get_notFound() {
        when(bankService.get(404)).thenThrow(new AccountNotFoundException(404));

        given().when().get("/api/accounts/404")
                .then().statusCode(404)
                .body("error", containsString("not found"));
    }

    @Test
    void getAll_ok() {
        var a = new BankAccount(1, "A");
        var b = new BankAccount(2, "B");
        when(bankService.all()).thenReturn(List.of(a, b));

        given().when().get("/api/accounts")
                .then().statusCode(200)
                .body("size()", equalTo(2))
                .body("[0].accountNumber", equalTo(1))
                .body("[1].accountNumber", equalTo(2));
    }

    @Test
    void remove_ok() {
        doNothing().when(bankService).remove(1);

        given().when().delete("/api/accounts/1")
                .then().statusCode(204);

        verify(bankService, times(1)).remove(1);
    }
}
