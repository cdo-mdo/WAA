package org.edu.miu.cs545de.bankapplication.repository;

import org.edu.miu.cs545de.bankapplication.model.BankAccount;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BankAccountRepository extends MongoRepository<BankAccount, String> {

    Optional<BankAccount> findByAccountNumber(Integer accountNumber);

    void deleteByAccountNumber(Integer accountNumber);

    boolean existsByAccountNumber(Integer accountNumber);
}
