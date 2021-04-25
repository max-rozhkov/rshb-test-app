package dev.redlab.rshb.testapp.service;

import dev.redlab.rshb.testapp.dao.entity.Account;
import dev.redlab.rshb.testapp.dao.entity.Log;
import dev.redlab.rshb.testapp.dao.entity.Transaction;
import dev.redlab.rshb.testapp.dao.entity.TransactionDeposit;
import dev.redlab.rshb.testapp.dao.repository.AccountRepository;
import dev.redlab.rshb.testapp.dao.repository.LogRepository;
import dev.redlab.rshb.testapp.dao.repository.TransactionDepositRepository;
import dev.redlab.rshb.testapp.dao.repository.TransactionRepository;
import dev.redlab.rshb.testapp.dto.request.DepositRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionService {

    private final AccountRepository accountRepository;

    private final TransactionRepository transactionRepository;

    private final TransactionDepositRepository transactionDepositRepository;

    private final LogRepository logRepository;

    public Transaction newTransaction() {
        return transactionRepository.save(new Transaction());
    }

    @Transactional
    public Account deposit(DepositRequest request) {
        Optional<TransactionDeposit> transaction = transactionDepositRepository.findById(request.getTransactionId());
        Account account = accountRepository.findById(request.getAccountId()).orElseThrow();
        if (transaction.isEmpty()) {
            TransactionDeposit transactionDeposit = new TransactionDeposit();
            transactionDeposit.setId(request.getTransactionId());
            transactionDeposit.setAccountId(request.getAccountId());
            transactionDeposit.setAmount(request.getDeposit());
            transactionDepositRepository.save(transactionDeposit);

            account.setBalance(account.getBalance().add(request.getDeposit()));
            accountRepository.save(account);

            Log log = new Log();
            log.setAccountId(request.getAccountId());
            log.setCreateDate(LocalDateTime.now());
            log.setMessage("Deposit " + request.getDeposit() + " to account " + account.getName());
            logRepository.save(log);
        }
        return account;
    }

}
