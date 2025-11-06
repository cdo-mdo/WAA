package org.edu.miu.cs545de.bankcontrollerapplication.repository;

import org.edu.miu.cs545de.bankcontrollerapplication.domain.BankAccount;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryBankAccountRepository implements BankAccountRepository {
    private final Map<Integer, BankAccount> store = new ConcurrentHashMap<>();

    @Override public Optional<BankAccount> findById(int n) { return Optional.ofNullable(store.get(n)); }
    @Override public boolean existsById(int n) { return store.containsKey(n); }
    @Override public BankAccount save(BankAccount a) { store.put(a.getAccountNumber(), a); return a; }
    @Override public void deleteById(int n) { store.remove(n); }
    @Override public Collection<BankAccount> findAll() { return store.values(); }
}

