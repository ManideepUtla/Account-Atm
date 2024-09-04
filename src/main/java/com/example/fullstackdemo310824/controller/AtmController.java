package com.example.fullstackdemo310824.controller;

import com.example.fullstackdemo310824.model.Atm;
import com.example.fullstackdemo310824.service.AccountService;
import com.example.fullstackdemo310824.service.AtmService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class AtmController {
    @PostMapping(value="/api/createAtm",
            consumes = "application/json",
            produces = "application/json")
    public Atm getCardNumber(@RequestBody Atm atm)
        throws Exception{
        AtmService atmService=new AtmService();
        AccountService accountService=new AccountService();
        String cardnumber=atmService.createAtm(atm);
        atm.setCardnumber(cardnumber);
        return atm;
    }


    @PostMapping(value = "/api/validateAtm", consumes = "application/json", produces = "application/json")
    public boolean validateAtm(@RequestBody Atm atm) throws Exception {
        AtmService atmService = new AtmService();
        return atmService.validateAtmLogin(atm.getCardnumber(), atm.getPin());
    }

}

