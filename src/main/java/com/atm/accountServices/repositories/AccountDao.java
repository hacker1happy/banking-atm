package com.atm.accountServices.repositories;

import com.atm.accountServices.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountDao extends JpaRepository<Account, Integer> {
    Optional<Account> findAccountByCustomerId(int customerId);
}
