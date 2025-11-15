package org.edu.miu.cs545de.bankapp.service;

import org.edu.miu.cs545de.bankapp.domain.BankAccount;
import org.edu.miu.cs545de.bankapp.domain.Transaction;
import org.edu.miu.cs545de.bankapp.domain.TransactionType;
import org.edu.miu.cs545de.bankapp.dto.AccountResponse;
import org.edu.miu.cs545de.bankapp.dto.CreateAccountRequest;
import org.edu.miu.cs545de.bankapp.dto.MoneyOperationRequest;
import org.edu.miu.cs545de.bankapp.dto.TransactionDto;
import org.edu.miu.cs545de.bankapp.repository.BankAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
@Transactional
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository repository;

    public BankAccountServiceImpl(BankAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public AccountResponse createAccount(CreateAccountRequest request) {
        if (repository.existsByAccountNumber(request.getAccountNumber())) {
            throw new DuplicateAccountException(request.getAccountNumber());
        }

        BankAccount account = new BankAccount(
                request.getAccountNumber(),
                request.getAccountHolder()
        );

        BankAccount saved = repository.save(account);
        return toResponse(saved);
    }

    @Override
    public AccountResponse deposit(int accountNumber, MoneyOperationRequest request) {
        BankAccount account = repository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException(accountNumber));

        BigDecimal newBalance = account.getBalance().add(request.getAmount());
        account.setBalance(newBalance);

        Transaction tx = new Transaction(LocalDateTime.now(), request.getAmount(), TransactionType.DEPOSIT);
        account.getTransactions().add(tx);

        BankAccount saved = repository.save(account);
        return toResponse(saved);
    }

    @Override
    public AccountResponse withdraw(int accountNumber, MoneyOperationRequest request) {
        BankAccount account = repository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException(accountNumber));

        BigDecimal amount = request.getAmount();
        if (account.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException(account.getBalance(), amount);
        }

        BigDecimal newBalance = account.getBalance().subtract(amount);
        account.setBalance(newBalance);

        Transaction tx = new Transaction(LocalDateTime.now(), amount, TransactionType.WITHDRAWAL);
        account.getTransactions().add(tx);

        BankAccount saved = repository.save(account);
        return toResponse(saved);
    }

    @Override
    public AccountResponse getAccount(int accountNumber) {
        BankAccount account = repository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException(accountNumber));
        return toResponse(account);
    }

    @Override
    public void removeAccount(int accountNumber) {
        if (!repository.existsByAccountNumber(accountNumber)) {
            throw new AccountNotFoundException(accountNumber);
        }
        repository.deleteByAccountNumber(accountNumber);
    }

    private AccountResponse toResponse(BankAccount account) {
        AccountResponse response = new AccountResponse();
        response.setAccountNumber(account.getAccountNumber());
        response.setAccountHolder(account.getAccountHolder());
        response.setBalance(account.getBalance());
        response.setTransactions(
                account.getTransactions()
                        .stream()
                        .map(tx -> {
                            TransactionDto dto = new TransactionDto();
                            dto.setDateTime(tx.getDateTime());
                            dto.setAmount(tx.getAmount());
                            dto.setType(tx.getType());
                            return dto;
                        })
                        .collect(Collectors.toList())
        );
        return response;
    }
}

