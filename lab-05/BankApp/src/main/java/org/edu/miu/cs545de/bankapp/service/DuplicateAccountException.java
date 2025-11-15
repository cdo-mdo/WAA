package org.edu.miu.cs545de.bankapp.service;

public class DuplicateAccountException extends RuntimeException {

    public DuplicateAccountException(int accountNumber) {
        super("Account already exists: " + accountNumber);
    }
}
