package com.example.basic_bancking.repository;

import com.example.basic_bancking.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
