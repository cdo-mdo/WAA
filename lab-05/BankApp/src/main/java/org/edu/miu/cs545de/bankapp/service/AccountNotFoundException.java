package org.edu.miu.cs545de.bankapp.service;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(int accountNumber) {
        super("Account not found: " + accountNumber);
    }
}

