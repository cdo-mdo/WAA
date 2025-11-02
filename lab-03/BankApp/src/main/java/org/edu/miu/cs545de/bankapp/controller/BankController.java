package org.edu.miu.cs545de.bankapp.controller;

import jakarta.validation.Valid;
import org.edu.miu.cs545de.bankapp.dto.BankAccountResponse;
import org.edu.miu.cs545de.bankapp.dto.CreateAccountRequest;
import org.edu.miu.cs545de.bankapp.dto.MoneyRequest;
import org.edu.miu.cs545de.bankapp.service.BankService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class BankController {

    private final BankService service;

    public BankController(BankService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BankAccountResponse create(@Valid @RequestBody CreateAccountRequest req) {
        return BankAccountResponse.from(service.createAccount(req.accountNumber(), req.accountHolder()));
    }

    @PostMapping("/{accountNumber}/deposit")
    public BankAccountResponse deposit(@PathVariable int accountNumber, @Valid @RequestBody MoneyRequest req) {
        return BankAccountResponse.from(service.deposit(accountNumber, BigDecimal.valueOf(req.amount())));
    }

    @PostMapping("/{accountNumber}/withdraw")
    public BankAccountResponse withdraw(@PathVariable int accountNumber, @Valid @RequestBody MoneyRequest req) {
        return BankAccountResponse.from(service.withdraw(accountNumber, BigDecimal.valueOf(req.amount())));
    }

    @GetMapping("/{accountNumber}")
    public BankAccountResponse get(@PathVariable int accountNumber) {
        return BankAccountResponse.from(service.get(accountNumber));
    }

    @GetMapping
    public List<BankAccountResponse> getAll() {
        return service.all().stream().map(BankAccountResponse::from).toList();
    }

    @DeleteMapping("/{accountNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable int accountNumber) {
        service.remove(accountNumber);
    }
}

