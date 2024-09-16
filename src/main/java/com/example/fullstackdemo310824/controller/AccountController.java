package com.example.fullstackdemo310824.controller;

import com.example.fullstackdemo310824.exception.AccountcreationFailedException;
import com.example.fullstackdemo310824.model.Account;
import com.example.fullstackdemo310824.model.AccountEntity;
import com.example.fullstackdemo310824.model.WithdrawalRequest;
import com.example.fullstackdemo310824.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpHeaders;
import java.util.List;

import static java.nio.file.Paths.get;

@RestController
@CrossOrigin("*")
public class  AccountController {

/*    @PostMapping(value="/api/createAccount",
            consumes = "application/json",
            produces = "application/json")
    public Account getAccountNumber(@RequestBody Account account)
            throws Exception {
        AccountService accountService=new AccountService();
        String accountNumber=accountService.createAccount(account);
        account.setAccountNumber(accountNumber);
        return account;
    }*/


/*    @PostMapping(value="/api/createAccount",
            consumes = "application/json",
            produces = "application/json")
    public Account getaccountNumberUsingHibernate(@RequestBody Account account)
            throws Exception {
        AccountService accountService=new AccountService();
        String accountNumber=accountService.oneTOManyUsingHibernate(account);
        account.setAccountNumber(accountNumber);
        return account;
    }*/


    @PostMapping(value="/api/createAccount",
            consumes = "application/json",
            produces = "application/json")
    public Account getaccountNumberUsingHibernateFromUi(@RequestBody Account account)
            throws Exception {
        AccountService accountService=new AccountService();
        String accountNumber=accountService.OneToManyUsingHibernateFromUI(account);
        account.setAccountNumber(accountNumber);
        return account;
    }

    @GetMapping(value="/api/searchAccount",
            consumes = "application/json",
            produces = "application/json")
    public Account searchAccount(@RequestHeader (name = "accountinput")
                                     String accountNumber) {

        AccountService accountService=new AccountService();
        return accountService.searchAccount(accountNumber);




    }









    @PostMapping(value = "/api/withdraw", consumes = "application/json", produces = "application/json")
    public Account withdraw(@RequestBody WithdrawalRequest request) throws Exception {
        AccountService accountService=new AccountService();
        return accountService.withdraw(request);
    }

}
