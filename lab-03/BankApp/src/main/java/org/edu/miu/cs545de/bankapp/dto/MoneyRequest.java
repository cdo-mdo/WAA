package org.edu.miu.cs545de.bankapp.dto;

import jakarta.validation.constraints.Min;

public record MoneyRequest(@Min(1) long amount) {}

