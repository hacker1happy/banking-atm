package com.atm.accountServices.controllerTests;

import com.atm.accountServices.constants.ErrorResponse;
import com.atm.accountServices.controller.Controller;
import com.atm.accountServices.exceptions.CustomException;
import com.atm.accountServices.models.Account;
import com.atm.accountServices.services.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(Controller.class)
public class AccountServicesControllerExceptionTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    private Account requestObject;
    private final double amount = 10;
    private final int accountNo = 1;
    @BeforeEach
    public void setUp(){
        requestObject = Account.builder().balance(100).customerId(123).build();
    }

    @Test
    @DisplayName("Test POST /account")
    public void addAccountTest() throws Exception {

        when(accountService.addAccount(requestObject)).thenAnswer(invocation ->{
                throw new CustomException(ErrorResponse.ACCOUNT_ALREADY_EXISTS);
        });

        mockMvc
                .perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestObject)))
                .andExpect(status().isConflict())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorCode").value(1001));
    }

    @Test
    @DisplayName("Test PUT /account/withdraw")
    public void withdrawTest() throws Exception {
        when(accountService.withdraw(accountNo, amount)).thenAnswer(invocation ->{
            throw new CustomException(ErrorResponse.ACCOUNT_NOT_FOUND);
        });
        mockMvc
                .perform(put("/withdraw")
                        .param("accountNo", String.valueOf(accountNo))
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorCode").value(1000));
    }

    @Test
    @DisplayName("Test PUT /account/deposit")
    public void depositTest() throws Exception {
        when(accountService.deposit(accountNo, amount)).thenAnswer(invocation ->{
            throw new CustomException(ErrorResponse.ACCOUNT_NOT_FOUND);
        });
        mockMvc
                .perform(put("/deposit")
                        .param("accountNo", String.valueOf(accountNo))
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorCode").value(1000));
    }

    @Test
    @DisplayName("Test GET /account/{accountNo}")
    public void getCallTest() throws Exception {

        when(accountService.getBalance(accountNo)).thenAnswer(invocation ->{
            throw new CustomException(ErrorResponse.ACCOUNT_NOT_FOUND);
        });

        mockMvc
                .perform(get("/"+accountNo))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorCode").value(1000));
    }
}
