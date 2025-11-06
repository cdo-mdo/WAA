package org.edu.miu.cs545de.bankcontrollerapplication.service;

public class DuplicateAccountException extends RuntimeException {
    public DuplicateAccountException(int n) { super("Account already exists: " + n); }
}

