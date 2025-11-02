package org.edu.miu.cs545de.bankapp.dto;

import org.edu.miu.cs545de.bankapp.domain.BankAccount;

import java.math.BigDecimal;
import java.util.List;

public record BankAccountResponse(
        int accountNumber,
        String accountHolder,
        BigDecimal balance,
        List<TransactionResponse> transactions
) {
    public static BankAccountResponse from(BankAccount acc) {
        return new BankAccountResponse(
                acc.getAccountNumber(),
                acc.getAccountHolder(),
                acc.getBalance(),
                acc.getTransactions().stream().map(TransactionResponse::from).toList()
        );
    }
}

