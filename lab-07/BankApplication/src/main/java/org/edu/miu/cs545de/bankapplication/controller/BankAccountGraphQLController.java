package org.edu.miu.cs545de.bankapplication.controller;

import lombok.RequiredArgsConstructor;
import org.edu.miu.cs545de.bankapplication.model.BankAccount;
import org.edu.miu.cs545de.bankapplication.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BankAccountGraphQLController {

    @Autowired
    private final BankAccountService service;

    @QueryMapping
    public List<BankAccount> accounts() {
        return service.getAllAccounts();
    }

    @QueryMapping
    public BankAccount account(@Argument Integer accountNumber) {
        return service.getAccount(accountNumber);
    }

    @MutationMapping
    public BankAccount createAccount(@Argument Integer accountNumber,
                                     @Argument String holderName) {
        return service.createAccount(accountNumber, holderName);
    }

    @MutationMapping
    public BankAccount deposit(@Argument Integer accountNumber,
                               @Argument Double amount) {
        return service.deposit(accountNumber, BigDecimal.valueOf(amount));
    }

    @MutationMapping
    public BankAccount withdraw(@Argument Integer accountNumber,
                                @Argument Double amount) {
        return service.withdraw(accountNumber, BigDecimal.valueOf(amount));
    }

    @MutationMapping
    public Boolean removeAccount(@Argument Integer accountNumber) {
        return service.removeAccount(accountNumber);
    }
}

