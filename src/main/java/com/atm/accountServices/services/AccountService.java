package com.atm.accountServices.services;

import com.atm.accountServices.exceptions.AccountAlreadyExistsException;
import com.atm.accountServices.exceptions.AccountNotFoundException;
import com.atm.accountServices.models.Account;

/**
 * @author PRIYANSG
 */
public interface AccountService {
    /**
     * This is a service to add account
     * @param account
     * @return
     * @throws AccountAlreadyExistsException
     */
    public Account addAccount(Account account) throws AccountAlreadyExistsException;

    /**
     * This is a service to withdraw cash from an account
     * @param accountNo
     * @param amount
     * @return
     * @throws AccountNotFoundException
     */
    public Account withdraw(int accountNo, double amount) throws AccountNotFoundException;

    /**
     * This is a service to deposit cash into an account
     * @param accountNo
     * @param amount
     * @return
     * @throws AccountNotFoundException
     */
    public Account deposit(int accountNo, double amount) throws AccountNotFoundException;

    /**
     * This is a service to check your account balance
     * @param accountNo
     * @return
     * @throws AccountNotFoundException
     */
    public double getBalance(int accountNo) throws AccountNotFoundException;

}
