package org.edu.miu.cs545de.bankapplication.service;

import lombok.RequiredArgsConstructor;
import org.edu.miu.cs545de.bankapplication.model.BankAccount;
import org.edu.miu.cs545de.bankapplication.model.Transaction;
import org.edu.miu.cs545de.bankapplication.repository.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BankAccountService {

    private final BankAccountRepository repository;

    public List<BankAccount> getAllAccounts() {
        return repository.findAll();
    }

    public BankAccount getAccount(Integer accountNumber) {
        return repository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found: " + accountNumber));
    }

    public BankAccount createAccount(Integer accountNumber, String holderName) {
        if (repository.existsByAccountNumber(accountNumber)) {
            throw new RuntimeException("Account already exists: " + accountNumber);
        }

        BankAccount account = new BankAccount();
        account.setAccountNumber(accountNumber);
        account.setHolderName(holderName);
        account.setBalance(BigDecimal.ZERO);

        return repository.save(account);
    }

    public BankAccount deposit(Integer accountNumber, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Deposit amount must be positive");
        }

        BankAccount account = getAccount(accountNumber);
        account.setBalance(account.getBalance().add(amount));

        Transaction tx = new Transaction(LocalDateTime.now(), amount, "DEPOSIT");
        account.getTransactions().add(tx);

        return repository.save(account);
    }

    public BankAccount withdraw(Integer accountNumber, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Withdrawal amount must be positive");
        }

        BankAccount account = getAccount(accountNumber);

        if (account.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        account.setBalance(account.getBalance().subtract(amount));

        Transaction tx = new Transaction(LocalDateTime.now(), amount, "WITHDRAWAL");
        account.getTransactions().add(tx);

        return repository.save(account);
    }

    public boolean removeAccount(Integer accountNumber) {
        BankAccount account = getAccount(accountNumber);
        repository.delete(account);
        return true;
    }
}
