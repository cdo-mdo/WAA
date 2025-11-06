package org.edu.miu.cs545de.bankcontrollerapplication.repository;

import org.edu.miu.cs545de.bankcontrollerapplication.domain.BankAccount;

import java.util.Collection;
import java.util.Optional;

public interface BankAccountRepository {
    Optional<BankAccount> findById(int accountNumber);
    boolean existsById(int accountNumber);
    BankAccount save(BankAccount account);
    void deleteById(int accountNumber);
    Collection<BankAccount> findAll();
}
