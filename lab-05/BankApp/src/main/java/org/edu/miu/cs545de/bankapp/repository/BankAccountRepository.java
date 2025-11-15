package org.edu.miu.cs545de.bankapp.repository;

import org.edu.miu.cs545de.bankapp.domain.BankAccount;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BankAccountRepository extends MongoRepository<BankAccount, String> {

    Optional<BankAccount> findByAccountNumber(int accountNumber);

    boolean existsByAccountNumber(int accountNumber);

    void deleteByAccountNumber(int accountNumber);
}
