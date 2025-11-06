package org.edu.miu.cs545de.bankcontrollerapplication.service;

import org.edu.miu.cs545de.bankcontrollerapplication.domain.BankAccount;
import org.edu.miu.cs545de.bankcontrollerapplication.repository.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;

@Service
public class BankService {
    private final BankAccountRepository repo;

    public BankService(BankAccountRepository repo) { this.repo = repo; }

    public BankAccount createAccount(int accountNumber, String accountHolder) {
        if (repo.existsById(accountNumber)) throw new DuplicateAccountException(accountNumber);
        return repo.save(new BankAccount(accountNumber, accountHolder));
    }

    public BankAccount deposit(int accountNumber, BigDecimal amount) {
        var acc = repo.findById(accountNumber).orElseThrow(() -> new AccountNotFoundException(accountNumber));
        acc.deposit(amount);
        return repo.save(acc);
    }

    public BankAccount withdraw(int accountNumber, BigDecimal amount) {
        var acc = repo.findById(accountNumber).orElseThrow(() -> new AccountNotFoundException(accountNumber));
        if (acc.getBalance().compareTo(amount) < 0) throw new InsufficientFundsException(accountNumber);
        acc.withdraw(amount);
        return repo.save(acc);
    }

    public BankAccount get(int accountNumber) {
        return repo.findById(accountNumber).orElseThrow(() -> new AccountNotFoundException(accountNumber));
    }

    public void remove(int accountNumber) {
        if (!repo.existsById(accountNumber)) throw new AccountNotFoundException(accountNumber);
        repo.deleteById(accountNumber);
    }

    public Collection<BankAccount> all() { return repo.findAll(); }
}
