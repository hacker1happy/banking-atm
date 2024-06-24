package com.atm.accountServices.controller;

import com.atm.accountServices.exceptions.AccountAlreadyExistsException;
import com.atm.accountServices.exceptions.AccountNotFoundException;
import com.atm.accountServices.models.Account;
import com.atm.accountServices.services.AccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This is the controller class for "/account"
 * @author PRIYANSG
 */
@RestController
public class Controller {
    @Autowired
    private AccountService accountService;

    /**
     * Endpoint to add your account in the bank
     * @param account
     * @return
     * @throws AccountAlreadyExistsException
     */
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> addAccount(@Valid @RequestBody Account account) throws AccountAlreadyExistsException {
        return new ResponseEntity<>(accountService.addAccount(account),
                HttpStatus.CREATED);
    }

    /**
     * Endpoint to withdraw cash from ATM
     *
     * @param accountNo
     * @param amount
     * @return
     * @throws AccountNotFoundException
     */
    @PutMapping(value = "/withdraw")
    public ResponseEntity<Account> withdraw(@RequestParam int accountNo, @Min(1) double amount) throws AccountNotFoundException {
        return new ResponseEntity<>(accountService.withdraw(accountNo, amount), HttpStatus.OK);
    }

    /**
     * Endpoint to deposit cash
     * @param accountNo
     * @param amount
     * @return
     * @throws AccountNotFoundException
     */
    @PutMapping(value = "/deposit")
    public ResponseEntity<Account> deposit(@RequestParam int accountNo, @Min(1) double amount) throws AccountNotFoundException {
        return new ResponseEntity<>(accountService.deposit(accountNo, amount), HttpStatus.OK);
    }

    /**
     * Endpoint to check balance in the account
     * @param accountNo
     * @return
     * @throws AccountNotFoundException
     */
    @GetMapping(value = "/{accountNo}")
    public ResponseEntity<Double> getBalance(@PathVariable int accountNo) throws AccountNotFoundException {
        return new ResponseEntity<>(accountService.getBalance(accountNo), HttpStatus.OK);
    }

}
