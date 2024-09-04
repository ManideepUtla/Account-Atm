package com.example.fullstackdemo310824.controller;

import com.example.fullstackdemo310824.exception.AccountcreationFailedException;
import com.example.fullstackdemo310824.model.Account;
import com.example.fullstackdemo310824.model.WithdrawalRequest;
import com.example.fullstackdemo310824.service.AccountService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class AccountController {

    @PostMapping(value="/api/createAccount",
            consumes = "application/json",
            produces = "application/json")
    public Account getAccountNumber(@RequestBody Account account)
            throws Exception {
        AccountService accountService=new AccountService();
        String accountNumber=accountService.createAccount(account);
        account.setAccountNumber(accountNumber);
        return account;
    }


    @PostMapping(value = "/api/withdraw", consumes = "application/json", produces = "application/json")
    public Account withdraw(@RequestBody WithdrawalRequest request) throws Exception {
        AccountService accountService=new AccountService();
        return accountService.withdraw(request.getAccountnumber(), request.getAmount());
    }

}
