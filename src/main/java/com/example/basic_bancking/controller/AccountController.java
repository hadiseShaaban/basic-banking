package com.example.basic_bancking.controller;

import com.example.basic_bancking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping("/{id}/deposit")
    public void postDeposit(@PathVariable Long id, @RequestParam Double amount){
        accountService.deposit(id, amount);
    }


    @PostMapping("/{id}/withdraw")
    public void postWithdraw(@PathVariable Long id, @RequestParam Double amount){
        accountService.withdraw(id, amount);
    }

    @GetMapping("/{id}/getbalance")
    public Double getBalance(@PathVariable Long id){
        return accountService.getBalance(id);
    }
}
