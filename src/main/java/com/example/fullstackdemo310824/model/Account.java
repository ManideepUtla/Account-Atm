package com.example.fullstackdemo310824.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Account {

    private String accountNumber;
    private String name;
    private String pan;
    private String mobileNumber;
    private double balance;
}
