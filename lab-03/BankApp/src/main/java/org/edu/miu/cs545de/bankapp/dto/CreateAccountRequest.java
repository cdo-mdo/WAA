package org.edu.miu.cs545de.bankapp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CreateAccountRequest(
        @Min(1) int accountNumber,
        @NotBlank String accountHolder
) {}

