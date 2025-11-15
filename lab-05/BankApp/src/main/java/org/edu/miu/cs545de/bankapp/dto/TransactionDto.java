package org.edu.miu.cs545de.bankapp.dto;

import org.edu.miu.cs545de.bankapp.domain.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDto {

    private LocalDateTime dateTime;
    private BigDecimal amount;
    private TransactionType type;

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

