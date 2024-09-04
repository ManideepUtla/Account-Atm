package com.example.fullstackdemo310824.model;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class WithdrawalRequest {
    private String accountnumber;
    private double amount;
}
