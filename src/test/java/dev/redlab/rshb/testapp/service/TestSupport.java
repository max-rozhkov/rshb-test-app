package dev.redlab.rshb.testapp.service;

import dev.redlab.rshb.testapp.dao.entity.Account;
import dev.redlab.rshb.testapp.dao.repository.AccountRepository;
import dev.redlab.rshb.testapp.dto.request.CreateAccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@Service
public class TestSupport {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> createAccount(String accountName) {
        accountService.createAccount(CreateAccountRequest.builder()
                .name(accountName)
                .build());
        List<Account> accounts = accountRepository.findAll();
        accounts = accounts.stream()
                .filter(account -> account.getName().equals(accountName))
                .collect(Collectors.toList());
        assertThat(accounts.size(), greaterThan(0));
        return accounts;
    }

}
