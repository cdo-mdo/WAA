package org.edu.miu.cs545de.bankcontrollerapplication.api.dto;

import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;

public record MoneyRequest(
        @DecimalMin(value = "0.01", message = "amount must be >= 0.01")
        BigDecimal amount
) {}

