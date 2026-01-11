package org.edu.miu.cs545de.bankcontroller.domain;

import java.math.BigDecimal;
import java.time.Instant;

public record Transaction(
        Instant date,
        TransactionType type,
        BigDecimal amount
) {}

