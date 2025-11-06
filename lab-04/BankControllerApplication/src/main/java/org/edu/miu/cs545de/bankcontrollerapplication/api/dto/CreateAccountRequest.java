package org.edu.miu.cs545de.bankcontrollerapplication.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CreateAccountRequest(
        @Min(value = 1, message = "accountNumber must be >= 1") int accountNumber,
        @NotBlank(message = "accountHolder is required") String accountHolder
) {}
