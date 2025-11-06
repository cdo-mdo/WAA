package org.edu.miu.cs545de.bankcontrollerapplication.service;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(int n) { super("Insufficient funds for account: " + n); }
}

