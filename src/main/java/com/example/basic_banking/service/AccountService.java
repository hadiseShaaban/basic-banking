package com.example.basic_banking.service;

import com.example.basic_banking.model.Account;
import com.example.basic_banking.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Synchronized
    @Transactional
    public void deposit(Long toId, Long fromId, Double amount) {

        Optional<Account> accountFrom = accountRepository.findById(fromId);

        accountFrom.ifPresent(account1 -> {
            Optional<Account> accountTo = accountRepository.findById(toId);
            accountTo.ifPresent(account2 ->{
                account1.setBalance(account1.getBalance() + amount);
                accountRepository.save(account1);
                withdraw(fromId, amount);
            });
            accountTo.orElseThrow(()->new IllegalArgumentException("Destination Account isn't exist!"));

        });
        accountFrom.orElseThrow(() -> new IllegalArgumentException("Source Account isn't exist!"));

    }

    @Synchronized
    @Transactional
    public void withdraw(Long accountId, Double amount) {
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

    @Synchronized
    @Transactional
    public Double getBalance(Long accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        Double balance = 0.0;

        if (account.isPresent()) {
            balance = account.get().getBalance();
            return balance;
        } else {
            throw new IllegalArgumentException("account id doesnt exist!");
        }
    }

    @Synchronized
    @Transactional
    public void createAccount(String name, Double defaultAmount) {
        Account account = new Account();
        account.setName(name);
        if (defaultAmount >= 10000) {
            account.setBalance(defaultAmount);
            accountRepository.save(account);
        } else throw new IllegalArgumentException("defaultAmount cant be less than 10000");
    }

    @Synchronized
    @Transactional
    public String deleteAccount(Long accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        String deletedName = "";

        if (account.isPresent()) {
            deletedName = account.get().getName();
            accountRepository.deleteById(accountId);
            return deletedName;
        } else {
            throw new IllegalArgumentException("user not found!");
        }
    }

}