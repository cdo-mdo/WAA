package org.edu.miu.cs545de.bankapp.service;

import org.edu.miu.cs545de.bankapp.dto.AccountResponse;
import org.edu.miu.cs545de.bankapp.dto.CreateAccountRequest;
import org.edu.miu.cs545de.bankapp.dto.MoneyOperationRequest;

public interface BankAccountService {

    AccountResponse createAccount(CreateAccountRequest request);

    AccountResponse deposit(int accountNumber, MoneyOperationRequest request);

    AccountResponse withdraw(int accountNumber, MoneyOperationRequest request);

    AccountResponse getAccount(int accountNumber);

    void removeAccount(int accountNumber);
}
