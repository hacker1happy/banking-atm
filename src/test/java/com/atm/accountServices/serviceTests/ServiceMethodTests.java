package com.atm.accountServices.serviceTests;

import com.atm.accountServices.exceptions.CustomException;
import com.atm.accountServices.models.Account;
import com.atm.accountServices.repositories.AccountDao;
import com.atm.accountServices.services.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServiceMethodTests {
    @Mock
    private AccountDao accountDao;
    @InjectMocks
    private AccountServiceImpl accountService;
    private Account responseObject, requestObject;
    private final double amount = 10;
    private final int accountNo = 1;
    @BeforeEach
    public void setUp(){
        requestObject = Account.builder().balance(100).customerId(123).build();
        responseObject = Account.builder().accountNo(1).balance(100).customerId(123).build();
    }

    @Test
    @DisplayName("Method addAccount()")
    public void addAccountTest() throws Exception {
        when(accountDao.findAccountByCustomerId(requestObject.getCustomerId())).thenReturn(Optional.empty());
        when(accountDao.save(requestObject)).thenReturn(responseObject);
        Account savedObject = accountService.addAccount(requestObject);
        verify(accountDao,times(1)).save(requestObject);
        assertNotNull(savedObject);
        assertEquals(responseObject, savedObject);
    }

    @Test
    @DisplayName("Method withdraw()")
    public void withdrawTest() throws Exception {
        when(accountDao.findById(accountNo)).thenReturn(Optional.ofNullable(responseObject));
        when(accountDao.save(Mockito.any(Account.class))).thenReturn(responseObject);
        Account savedObject = accountService.withdraw(accountNo, amount);
        verify(accountDao,times(1)).save(Mockito.any(Account.class));
        assertNotNull(savedObject);
        assertEquals(responseObject, savedObject);
    }

    @Test
    @DisplayName("Method deposit()")
    public void depositTest() throws Exception {
        when(accountDao.findById(accountNo)).thenReturn(Optional.ofNullable(responseObject));
        when(accountDao.save(Mockito.any(Account.class))).thenReturn(responseObject);
        Account savedObject = accountService.deposit(accountNo, amount);
        verify(accountDao,times(1)).save(Mockito.any(Account.class));
        assertNotNull(savedObject);
        assertEquals(responseObject, savedObject);
    }

    @Test
    @DisplayName("Method getBalance()")
    public void getIngredientTest() throws CustomException {
        when(accountDao.findById(accountNo)).thenReturn(Optional.ofNullable(responseObject));
        double balance = accountService.getBalance(accountNo);
        verify(accountDao,times(1)).findById( accountNo );
        assertEquals(responseObject.getBalance(), balance);
    }
}
