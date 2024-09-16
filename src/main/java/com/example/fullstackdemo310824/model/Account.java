package com.example.fullstackdemo310824.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class Account {

    public String accountNumber;
    public String name;
    public String pan;
    public String mobileNumber;
    public double balance;
    public Address address;
}
