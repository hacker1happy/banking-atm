package com.atm.accountServices.services;

import com.atm.accountServices.constants.ErrorResponse;
import com.atm.accountServices.exceptions.AccountAlreadyExistsException;
import com.atm.accountServices.exceptions.AccountNotFoundException;
import com.atm.accountServices.models.Account;
import com.atm.accountServices.repositories.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;
    private boolean accountAlreadyPresent(int accountNo) {
        return accountDao.findById(accountNo).isPresent();
    }

    @Override
    public Account addAccount(Account account) throws AccountAlreadyExistsException {
        if(accountDao.findAccountByCustomerId(account.getCustomerId()).isPresent())
            throw new AccountAlreadyExistsException(ErrorResponse.ACCOUNT_ALREADY_EXISTS);
        return accountDao.save(account);
    }


    @Override
    public Account withdraw(int accountNo, double amount) throws AccountNotFoundException {
        if(!accountAlreadyPresent(accountNo))
            throw new AccountNotFoundException(ErrorResponse.ACCOUNT_NOT_FOUND);
        Account presentAccount = accountDao.findById(accountNo).get();

        presentAccount.setBalance(presentAccount.getBalance()-amount);

        return accountDao.save(presentAccount);
    }

    @Override
    public Account deposit(int accountNo, double amount) throws AccountNotFoundException {
        if(!accountAlreadyPresent(accountNo))
            throw new AccountNotFoundException(ErrorResponse.ACCOUNT_NOT_FOUND);
        Account presentAccount = accountDao.findById(accountNo).get();

        presentAccount.setBalance(presentAccount.getBalance()+amount);

        // Add transactions
        return accountDao.save(presentAccount);
    }

    @Override
    public double getBalance(int accountNo) throws AccountNotFoundException {
        return accountDao.findById(accountNo).orElseThrow(()->new AccountNotFoundException(ErrorResponse.ACCOUNT_NOT_FOUND)).getBalance();
    }

}
