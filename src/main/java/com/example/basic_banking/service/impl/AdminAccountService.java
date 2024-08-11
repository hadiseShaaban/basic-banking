package com.example.basic_banking.service.impl;

import com.example.basic_banking.model.Account;
import com.example.basic_banking.repository.AccountRepository;
import com.example.basic_banking.service.AdminAccountServices;
import jakarta.transaction.Transactional;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminAccountService implements AdminAccountServices {

    @Autowired
    AccountRepository accountRepository;

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