package org.edu.miu.cs545de.bankcontrollerapplication.api.dto;

import org.edu.miu.cs545de.bankcontrollerapplication.domain.Transaction;
import org.edu.miu.cs545de.bankcontrollerapplication.domain.TransactionType;

import java.math.BigDecimal;
import java.time.Instant;

public record TransactionResponse(Instant timestamp, TransactionType type, BigDecimal amount) {
    public static TransactionResponse from(Transaction t) {
        return new TransactionResponse(t.getTimestamp(), t.getType(), t.getAmount());
    }
}

