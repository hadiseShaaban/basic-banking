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

    }
}
