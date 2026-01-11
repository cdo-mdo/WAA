package org.edu.miu.cs545de.bankcontroller.api;

import jakarta.validation.Valid;
import org.edu.miu.cs545de.bankcontroller.domain.BankAccount;
import org.edu.miu.cs545de.bankcontroller.dto.AmountRequest;
import org.edu.miu.cs545de.bankcontroller.dto.CreateAccountRequest;
import org.edu.miu.cs545de.bankcontroller.service.BankService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/accounts")
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    // createAccount(int accountNumber, String accountHolder)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BankAccount createAccount(@Valid @RequestBody CreateAccountRequest req) {
        return bankService.createAccount(req.accountNumber(), req.accountHolder());
    }

    // deposit(int accountNumber, amount)
    @PostMapping("/{accountNumber}/deposit")
    public BankAccount deposit(@PathVariable int accountNumber, @Valid @RequestBody AmountRequest req) {
        return bankService.deposit(accountNumber, req.amount());
    }

    // withdraw(int accountNumber, amount)
    @PostMapping("/{accountNumber}/withdraw")
    public BankAccount withdraw(@PathVariable int accountNumber, @Valid @RequestBody AmountRequest req) {
        return bankService.withdraw(accountNumber, req.amount());
    }

    // getAccount(int accountNumber)
    @GetMapping("/{accountNumber}")
    public BankAccount getAccount(@PathVariable int accountNumber) {
        return bankService.getAccount(accountNumber);
    }

    // removeAccount(int accountNumber)
    @DeleteMapping("/{accountNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAccount(@PathVariable int accountNumber) {
        bankService.removeAccount(accountNumber);
    }
}
