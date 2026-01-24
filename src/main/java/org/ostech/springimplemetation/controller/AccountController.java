package org.ostech.springimplemetation.controller;

import org.ostech.springimplemetation.dto.WithdrawDTO;
import org.ostech.springimplemetation.dto.WithdrawDTOResponse;
import org.ostech.springimplemetation.service.AccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/account")
public class AccountController {

    AccountService accountService;

    AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/withdraw")
    public WithdrawDTOResponse withdraw(@RequestBody WithdrawDTO withdrawDTO) {
        return accountService.withdraw(withdrawDTO);
    }
}
