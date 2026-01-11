package org.edu.miu.cs545de.bankcontroller.service;

import org.edu.miu.cs545de.bankcontroller.domain.BankAccount;
import org.edu.miu.cs545de.bankcontroller.domain.Transaction;
import org.edu.miu.cs545de.bankcontroller.domain.TransactionType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class BankService {

    private final Map<Integer, BankAccount> accounts = new ConcurrentHashMap<>();

    public BankAccount createAccount(int accountNumber, String accountHolder) {
        if (accounts.containsKey(accountNumber)) {
            throw new IllegalArgumentException("Account already exists: " + accountNumber);
        }
        BankAccount acc = new BankAccount(accountNumber, accountHolder);
        accounts.put(accountNumber, acc);
        return acc;
    }

    public BankAccount deposit(int accountNumber, BigDecimal amount) {
        BankAccount acc = getAccountOrThrow(accountNumber);
        acc.setBalance(acc.getBalance().add(amount));
        acc.addTransaction(new Transaction(Instant.now(), TransactionType.DEPOSIT, amount));
        return acc;
    }

    public BankAccount withdraw(int accountNumber, BigDecimal amount) {
        BankAccount acc = getAccountOrThrow(accountNumber);
        if (acc.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        acc.setBalance(acc.getBalance().subtract(amount));
        acc.addTransaction(new Transaction(Instant.now(), TransactionType.WITHDRAWAL, amount));
        return acc;
    }

    public BankAccount getAccount(int accountNumber) {
        return getAccountOrThrow(accountNumber);
    }

    public void removeAccount(int accountNumber) {
        if (accounts.remove(accountNumber) == null) {
            throw new IllegalArgumentException("Account not found: " + accountNumber);
        }
    }

    private BankAccount getAccountOrThrow(int accountNumber) {
        BankAccount acc = accounts.get(accountNumber);
        if (acc == null) throw new IllegalArgumentException("Account not found: " + accountNumber);
        return acc;
    }
}
