package org.edu.miu.cs545de.bankapp.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {

    private LocalDateTime dateTime;
    private BigDecimal amount;
    private TransactionType type;

    public Transaction() {
    }

    public Transaction(LocalDateTime dateTime, BigDecimal amount, TransactionType type) {
        this.dateTime = dateTime;
        this.amount = amount;
        this.type = type;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }
}

