package org.edu.miu.cs545de.bankapp.domain;

import java.math.BigDecimal;
import java.time.Instant;

public class Transaction {
    private final Instant timestamp;
    private final TransactionType type;
    private final BigDecimal amount;

    public Transaction(TransactionType type, BigDecimal amount) {
        this.timestamp = Instant.now();
        this.type = type;
        this.amount = amount;
    }
    public Instant getTimestamp() { return timestamp; }
    public TransactionType getType() { return type; }
    public BigDecimal getAmount() { return amount; }
}

