package dev.redlab.rshb.testapp.service;

import dev.redlab.rshb.testapp.dao.entity.Account;
import dev.redlab.rshb.testapp.dao.repository.AccountRepository;
import dev.redlab.rshb.testapp.dto.request.CreateAccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getAccountInfo(long accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        return account.orElseThrow();
    }

    public void createAccount(CreateAccountRequest request) {
        Account account = new Account();
        account.setName(request.getName());
        account.setBalance(BigDecimal.ZERO);
        accountRepository.save(account);
    }
}
