package com.example.fullstackdemo310824.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Address {
    private String add1;
    private String add2;
    private String pincode;
    private String city;
    private String state;
}

