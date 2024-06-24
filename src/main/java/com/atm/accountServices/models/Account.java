package com.atm.accountServices.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Account Entity class to hold a user's account
 *
 * @author PRIYANSG
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Min(value = 0, message = "Account number can't be negative")
    private int accountNo;
    @Min(value = 0, message = "Balance is zero.")
    private double balance;
    private int customerId;
}
