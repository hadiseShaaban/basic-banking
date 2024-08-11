package com.example.basic_banking.controller;

import com.example.basic_banking.repository.AccountRepository;
import com.example.basic_banking.service.AdminAccountService;
import com.example.basic_banking.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    AdminAccountService adminAccountService;

    @Autowired
    AccountRepository accountRepository;

    @PostMapping("/deposit")
    public ResponseEntity<Object> postDeposit(@RequestParam Double amount, @RequestParam Long from, @RequestParam Long to) {
        userAccountService.deposit(to, from, amount);
        return new ResponseEntity<>("واریز به حساب " + accountRepository.findById(to).get().getName() + " انجام شد :)", HttpStatus.OK);
    }


    @PostMapping("/{id}/withdraw")
    public ResponseEntity<Object> postWithdraw(@PathVariable Long id, @RequestParam Double amount) {
        userAccountService.withdraw(id, amount);
        return new ResponseEntity<>(userAccountService.getBalance(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/getbalance")
    public Double getBalance(@PathVariable Long id) {
        return userAccountService.getBalance(id);
    }

    @PostMapping("/create-account")
    public ResponseEntity<Object> postCreateAccount(@RequestParam String accountName, @RequestParam Double defaultAmount) {
        adminAccountService.createAccount(accountName, defaultAmount);
        return new ResponseEntity<>(accountName + " created!", HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-account")
    public ResponseEntity<Object> deleteAccount(@RequestParam Long id) {
        String deletedName = adminAccountService.deleteAccount(id);
        return new ResponseEntity<>(deletedName + " deleted!", HttpStatus.OK);
    }
}