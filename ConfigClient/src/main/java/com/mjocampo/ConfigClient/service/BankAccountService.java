package com.mjocampo.ConfigClient.service;

import com.mjocampo.ConfigClient.config.Configuration;
import com.mjocampo.ConfigClient.model.BankAccount;
import com.mjocampo.ConfigClient.model.EnumAccountType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class BankAccountService {

    @Autowired
    private Configuration configuration;

    private Map<String, BankAccount> accountCache = new HashMap<>();

    @PostConstruct
    public void setUpTestData() {

        BankAccount account1 = new BankAccount("AI123", "Maria Ocampo", BigDecimal.valueOf(1250.38), EnumAccountType.CURRENT_ACCOUNT);
        BankAccount account2 = new BankAccount("AI456", "Maria Ocampo", BigDecimal.valueOf(1550.40), EnumAccountType.SAVINGS_ACCOUNT);

        accountCache.put(account1.getAccountId(), account1);
        accountCache.put(account2.getAccountId(), account2);

    }

    public void createBankAccount(BankAccount account) {

        if(account.getAccountBalance().doubleValue() >= configuration.getMinBalance() &&
                account.getAccountBalance().doubleValue() <= configuration.getMaxBalance()) {
            accountCache.put(account.getAccountId(), account);
        }
        else {
            throw new InvalidAccountBalanceException("Bank Account Balance is outside of allowed thresholds");
        }
    }

    public BankAccount retrieveBankAccount(String accountId) {
        return accountCache.get(accountId);
    }

}
