package org.edu.miu.cs545de.bankcontroller.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private int accountNumber;
    private String accountHolder;
    private BigDecimal balance = BigDecimal.ZERO;
    private final List<Transaction> transactions = new ArrayList<>();

    public BankAccount() {}

    public BankAccount(int accountNumber, String accountHolder) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = BigDecimal.ZERO;
    }

    public int getAccountNumber() { return accountNumber; }
    public void setAccountNumber(int accountNumber) { this.accountNumber = accountNumber; }

    public String getAccountHolder() { return accountHolder; }
    public void setAccountHolder(String accountHolder) { this.accountHolder = accountHolder; }

    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }

    public List<Transaction> getTransactions() { return transactions; }

    public void addTransaction(Transaction tx) {
        this.transactions.add(tx);
    }
}
