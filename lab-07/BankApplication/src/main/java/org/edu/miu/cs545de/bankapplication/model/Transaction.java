package org.edu.miu.cs545de.bankapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private LocalDateTime timestamp;
    private BigDecimal amount;
    private String type; // "DEPOSIT" or "WITHDRAWAL"
}
