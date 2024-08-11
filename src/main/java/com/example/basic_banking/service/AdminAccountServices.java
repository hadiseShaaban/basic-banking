package com.example.basic_banking.service;

public interface AdminAccountServices {
    public void createAccount(String name, Double defaultAmount);
    public String deleteAccount(Long accountId);
}
