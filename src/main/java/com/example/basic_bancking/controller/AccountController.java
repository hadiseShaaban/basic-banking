package com.example.basic_bancking.controller;

import com.example.basic_bancking.repository.AccountRepository;
import com.example.basic_bancking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    @PostMapping("/deposit")
    public ResponseEntity<Object> postDeposit(@RequestParam Double amount, @RequestParam Long from, @RequestParam Long to) {
        accountService.deposit(to, from, amount);
        return new ResponseEntity<>("واریز به حساب " + accountRepository.findById(to).get().getName() + " انجام شد :)", HttpStatus.OK);
    }


    @PostMapping("/{id}/withdraw")
    public ResponseEntity<Object> postWithdraw(@PathVariable Long id, @RequestParam Double amount) {
        accountService.withdraw(id, amount);
        return new ResponseEntity<>(accountService.getBalance(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/getbalance")
    public Double getBalance(@PathVariable Long id) {
        return accountService.getBalance(id);
    }

    @PostMapping("/create-account")
    public ResponseEntity<Object> postCreateAccount(@RequestParam String accountName, @RequestParam Double defaultAmount) {
        accountService.createAccount(accountName, defaultAmount);
        return new ResponseEntity<>(accountName + " created!", HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-account")
    public ResponseEntity<Object> deleteAccount(@RequestParam Long id) {
        String deletedName = accountService.deleteAccount(id);
        return new ResponseEntity<>(deletedName + " deleted!", HttpStatus.OK);
    }
}