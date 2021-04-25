package dev.redlab.rshb.testapp.controller;

import dev.redlab.rshb.testapp.dao.entity.Account;
import dev.redlab.rshb.testapp.dao.entity.Transaction;
import dev.redlab.rshb.testapp.dto.request.DepositRequest;
import dev.redlab.rshb.testapp.service.TransactionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/transaction")
@Tag(name = "TRANSACTION", description = "Endpoints for TRANSACTION")
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/new")
    public ResponseEntity<Transaction> newTransaction() {
        return ResponseEntity.ok(transactionService.newTransaction());
    }

    @PutMapping("/deposit")
    public ResponseEntity<Account> deposit(@Valid @RequestBody DepositRequest request) {
        return ResponseEntity.ok(transactionService.deposit(request));
    }

}
