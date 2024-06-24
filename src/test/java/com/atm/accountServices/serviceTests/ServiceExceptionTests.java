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
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceExceptionTests {
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
    @DisplayName("Exception addAccount()")
    public void addAccountExceptionTest() throws Exception {
        when(accountDao.findAccountByCustomerId(requestObject.getCustomerId())).thenReturn(Optional.ofNullable(responseObject));
        assertThrows(CustomException.class, ()->accountService.addAccount(requestObject));
    }

    @Test
    @DisplayName("Exception withdraw()")
    public void withdrawExceptionTest() throws Exception {
        when(accountDao.findById(accountNo)).thenReturn(Optional.empty());
        assertThrows(CustomException.class, ()->accountService.withdraw(accountNo, amount));
    }

    @Test
    @DisplayName("Exception deposit()")
    public void depositExceptionTest() throws Exception {
        when(accountDao.findById(accountNo)).thenReturn(Optional.empty());
        assertThrows(CustomException.class, ()->accountService.deposit(accountNo, amount));
    }

    @Test
    @DisplayName("Exception getBalance()")
    public void getIngredientExceptionTest() throws CustomException {
        when(accountDao.findById(accountNo)).thenReturn(Optional.empty());
        assertThrows(CustomException.class, ()->accountService.getBalance(accountNo));
    }
}
