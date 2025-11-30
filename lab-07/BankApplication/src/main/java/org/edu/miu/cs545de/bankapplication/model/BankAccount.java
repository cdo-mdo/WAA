package org.edu.miu.cs545de.bankapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "bank_accounts")
public class BankAccount {

    @Id
    private String id;

    private Integer accountNumber;
    private String holderName;
    private BigDecimal balance = BigDecimal.ZERO;

    private List<Transaction> transactions = new ArrayList<>();
}
