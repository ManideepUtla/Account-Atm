package com.example.fullstackdemo310824.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="account", schema = "bank")

@Data //for setters and getters
public class AccountEntity {

    public AccountEntity(){

    }

    @Column(name = "name", nullable = false)
    private String name;

    @Id //it represents primary key
    @Column(name = "accountNumber")
    private String accountNumber;

    @Column(name = "pan",nullable = false)
    private String pan;

    @Column(name = "mobileNumber")
    private String mobileNumber;

    @Column(name = "balance",nullable = false)
    private double balance;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "accountEntity",
            cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    public List<AccountAddressEntity> accountAddressEntityList;



}
