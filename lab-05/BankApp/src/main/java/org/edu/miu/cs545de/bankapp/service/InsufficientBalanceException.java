package org.edu.miu.cs545de.bankapp.service;

import java.math.BigDecimal;

public class InsufficientBalanceException extends RuntimeException {

    public InsufficientBalanceException(BigDecimal balance, BigDecimal amount) {
        super("Insufficient balance. Current balance: " + balance + ", requested: " + amount);
    }
}
