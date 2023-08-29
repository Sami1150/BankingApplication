package com.redmath.assignment.bankingapplication.account;

import com.redmath.assignment.bankingapplication.Balance.Balance;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private long account_id;
    private String password;
    private String name;
    private String email;
    private String address;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_account_Id", referencedColumnName = "account_id")
    private List<Balance> balance;

    public Account() {
    }

    public Account(long account_id, String password, String name, String email, String address) {
        this.account_id = account_id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.address = address;
    }


    public long getAccount_idId() {
        return account_id;
    }

    public void setAccount_id(long id) {
        this.account_id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
