package org.edu.miu.cs545de.bankcontrollerapplication.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private final int accountNumber;
    private String accountHolder;
    private BigDecimal balance = BigDecimal.ZERO;
    private final List<Transaction> transactions = new ArrayList<>();

    public BankAccount(int accountNumber, String accountHolder) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
    }

    public int getAccountNumber() { return accountNumber; }
    public String getAccountHolder() { return accountHolder; }
    public void setAccountHolder(String accountHolder) { this.accountHolder = accountHolder; }
    public BigDecimal getBalance() { return balance; }
    public List<Transaction> getTransactions() { return transactions; }

    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
        transactions.add(new Transaction(TransactionType.DEPOSIT, amount));
    }

    public void withdraw(BigDecimal amount) {
        balance = balance.subtract(amount);
        transactions.add(new Transaction(TransactionType.WITHDRAW, amount));
    }
}

