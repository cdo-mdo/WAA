package org.edu.miu.cs545de.bankapp.service;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(int accountNumber) {
        super("Insufficient funds for account: " + accountNumber);
    }
}

