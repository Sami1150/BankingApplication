package com.redmath.assignment.bankingapplication.Balance;

import com.redmath.assignment.bankingapplication.account.Account;
import com.redmath.assignment.bankingapplication.account.AccountService;
import jakarta.persistence.*;

@Entity
@Table(name="balance")
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "balance_id") // Rename the column
    private long balance_id;

    private String date;
    private double amount;
    private String accountType;

    // Constructors, getters, setters, etc.

    @OneToOne
    @MapsId
    @JoinColumn(name = "account_id")
    private Account account;
    public Balance() {
    }

    public Balance(long account_id, String date, double amount, String accountType) {
        this.balance_id = account_id;
        this.date = date;
        this.amount = amount;
        this.accountType = accountType;
    }

    public long getBalance_id() {
        return balance_id;
    }

    public void setAccount_id(long balance_id) {
        this.balance_id = balance_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}