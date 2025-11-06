package org.edu.miu.cs545de.bankcontrollerapplication.service;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(int n) { super("Account not found: " + n); }
}

