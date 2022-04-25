package com.mjocampo.ConfigClient.rest;

import com.mjocampo.ConfigClient.model.BankAccount;
import com.mjocampo.ConfigClient.service.BankAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@RestController
@RequestMapping("/bank-account")
public class BankAccountController {

    @Autowired
    public BankAccountService bankAccountService;

    @PostMapping("/create")
    public ResponseEntity<?> createBankAccount(@RequestBody BankAccount bankAccount, HttpServletRequest request) throws URISyntaxException {

        bankAccountService.createBankAccount(bankAccount);

        log.info("created bank account {}", bankAccount);

        URI uri = new URI(request.getRequestURL() + "bank-account/" + bankAccount.getAccountId());

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<BankAccount> getBankAccount(@PathVariable("accountId") String accountId) {

        BankAccount account = bankAccountService.retrieveBankAccount(accountId);

        log.info("retrieved bank account {}", account);

        return ResponseEntity.ok(account);
    }
}
