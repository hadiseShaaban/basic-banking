package com.example.basic_bancking.service;

import com.example.basic_bancking.model.Account;
import com.example.basic_bancking.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Transactional
    public void deposit(Long toId, Long fromId, Double amount) {

        Optional<Account> accountTo = accountRepository.findById(toId);
        synchronized (accountTo) {
            accountTo.ifPresent(account1 -> {
                account1.setBalance(account1.getBalance() + amount);
                accountRepository.save(account1);
            });
            accountTo.orElseThrow(() -> new IllegalArgumentException("invalid sourceAccount arguments"));

            withdraw(fromId, amount);
        }
    }

    @Transactional
    public synchronized void withdraw(Long accountId, Double amount) {
        Optional<Account> account = accountRepository.findById(accountId);
        account.ifPresent(account1 -> {
            if (account1.getBalance() > amount) {
                account1.setBalance(account1.getBalance() - amount);
                accountRepository.save(account1);
            } else {
                throw new IllegalArgumentException("insufficient balance");
            }
        });
        account.orElseThrow(() -> new IllegalArgumentException("invalid arguments"));
    }

    public synchronized Double getBalance(Long accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        Double balance = 0.0;

        if (account.isPresent()) {
            balance = account.get().getBalance();
        } else {
            throw new IllegalArgumentException("invalid arguments");
        }

        return balance;
    }
}