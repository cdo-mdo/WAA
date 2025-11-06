package org.edu.miu.cs545de.bankcontrollerapplication.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.edu.miu.cs545de.bankcontrollerapplication.api.dto.BankAccountResponse;
import org.edu.miu.cs545de.bankcontrollerapplication.api.dto.CreateAccountRequest;
import org.edu.miu.cs545de.bankcontrollerapplication.api.dto.MoneyRequest;
import org.edu.miu.cs545de.bankcontrollerapplication.service.BankService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/accounts")
public class BankController {

    private final BankService service;
    public BankController(BankService service) { this.service = service; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BankAccountResponse create(@Valid @RequestBody CreateAccountRequest req) {
        return BankAccountResponse.from(service.createAccount(req.accountNumber(), req.accountHolder()));
    }

    @PostMapping("/{accountNumber}/deposit")
    public BankAccountResponse deposit(@PathVariable @Min(1) int accountNumber,
                                       @Valid @RequestBody MoneyRequest req) {
        return BankAccountResponse.from(service.deposit(accountNumber, req.amount()));
    }

    @PostMapping("/{accountNumber}/withdraw")
    public BankAccountResponse withdraw(@PathVariable @Min(1) int accountNumber,
                                        @Valid @RequestBody MoneyRequest req) {
        return BankAccountResponse.from(service.withdraw(accountNumber, req.amount()));
    }

    @GetMapping("/{accountNumber}")
    public BankAccountResponse get(@PathVariable @Min(1) int accountNumber) {
        return BankAccountResponse.from(service.get(accountNumber));
    }

    @GetMapping
    public List<BankAccountResponse> getAll() {
        return service.all().stream().map(BankAccountResponse::from).toList();
    }

    @DeleteMapping("/{accountNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable @Min(1) int accountNumber) {
        service.remove(accountNumber);
    }
}

