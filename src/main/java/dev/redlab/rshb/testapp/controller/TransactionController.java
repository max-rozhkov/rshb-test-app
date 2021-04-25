package dev.redlab.rshb.testapp.controller;

import dev.redlab.rshb.testapp.dao.entity.Transaction;
import dev.redlab.rshb.testapp.service.TransactionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
