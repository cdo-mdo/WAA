package org.edu.miu.cs545de.bankapp.service;

import org.edu.miu.cs545de.bankapp.domain.BankAccount;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class BankService {

    private final Map<Integer, BankAccount> accounts = new ConcurrentHashMap<>();

    public BankAccount createAccount(int accountNumber, String accountHolder) {
        if (accounts.containsKey(accountNumber)) throw new DuplicateAccountException(accountNumber);
        var acc = new BankAccount(accountNumber, accountHolder);
        accounts.put(accountNumber, acc);
        return acc;
    }

    public BankAccount deposit(int accountNumber, BigDecimal amount) {
        var acc = find(accountNumber);
        acc.deposit(amount);
        return acc;
    }

    public BankAccount withdraw(int accountNumber, BigDecimal amount) {
        var acc = find(accountNumber);
        if (acc.getBalance().compareTo(amount) < 0) throw new InsufficientFundsException(accountNumber);
        acc.withdraw(amount);
        return acc;
    }

    public BankAccount get(int accountNumber) {
        return find(accountNumber);
    }

    public void remove(int accountNumber) {
        if (accounts.remove(accountNumber) == null) throw new AccountNotFoundException(accountNumber);
    }

    public Collection<BankAccount> all() {
        return accounts.values();
    }

    private BankAccount find(int accountNumber) {
        var acc = accounts.get(accountNumber);
        if (acc == null) throw new AccountNotFoundException(accountNumber);
        return acc;
    }
}
