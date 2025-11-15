package org.edu.miu.cs545de.bankapp.controller;

import jakarta.validation.Valid;
import org.edu.miu.cs545de.bankapp.dto.AccountResponse;
import org.edu.miu.cs545de.bankapp.dto.CreateAccountRequest;
import org.edu.miu.cs545de.bankapp.dto.MoneyOperationRequest;
import org.edu.miu.cs545de.bankapp.service.BankAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class BankAccountController {

    private final BankAccountService service;

    public BankAccountController(BankAccountService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(
            @Valid @RequestBody CreateAccountRequest request) {

        AccountResponse response = service.createAccount(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/{accountNumber}/deposit")
    public ResponseEntity<AccountResponse> deposit(
            @PathVariable int accountNumber,
            @Valid @RequestBody MoneyOperationRequest request) {

        AccountResponse response = service.deposit(accountNumber, request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{accountNumber}/withdraw")
    public ResponseEntity<AccountResponse> withdraw(
            @PathVariable int accountNumber,
            @Valid @RequestBody MoneyOperationRequest request) {

        AccountResponse response = service.withdraw(accountNumber, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable int accountNumber) {
        AccountResponse response = service.getAccount(accountNumber);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<Void> removeAccount(@PathVariable int accountNumber) {
        service.removeAccount(accountNumber);
        return ResponseEntity.noContent().build();
    }
}

