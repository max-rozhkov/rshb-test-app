package dev.redlab.rshb.testapp.controller;

import dev.redlab.rshb.testapp.dao.entity.Account;
import dev.redlab.rshb.testapp.dto.request.CreateAccountRequest;
import dev.redlab.rshb.testapp.service.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
@Tag(name = "ACCOUNT", description = "Endpoints for ACCOUNT")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Account> getUserInfoById(@PathVariable long userId) {
        Account response = accountService.getAccountInfo(userId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createAccount(@Valid @RequestBody CreateAccountRequest request) {
        accountService.createAccount(request);
        return ResponseEntity.ok().build();
    }
}
