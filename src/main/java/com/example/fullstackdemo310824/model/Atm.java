package com.example.fullstackdemo310824.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class Atm {
    private String cardnumber;
    private String pin;
    private String cvv;
    private Date expire;
    private String accountnumber;

    public String getCardnumber() {
        return cardnumber;
    }

    public String getPin() {
        return pin;
    }

    public String getCvv() {
        return cvv;
    }

    public Date getExpire() {
        return expire;
    }

    public String getAccountnumber() {
        return accountnumber;
    }
}
