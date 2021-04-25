package dev.redlab.rshb.testapp.service;

import dev.redlab.rshb.testapp.dao.entity.Transaction;
import dev.redlab.rshb.testapp.dao.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public Transaction newTransaction() {
        return transactionRepository.save(new Transaction());
    }
}
