package com.example.basic_banking.service;

public interface UserAccuntServices {
    public void deposit(Long toId, Long fromId, Double amount);
    public void withdraw(Long accountId, Double amount) ;
    public Double getBalance(Long accountId);
}
