package dev.redlab.rshb.testapp.service;

import dev.redlab.rshb.testapp.TestappPostgresqlContainer;
import dev.redlab.rshb.testapp.dao.entity.Account;
import dev.redlab.rshb.testapp.dao.entity.Transaction;
import dev.redlab.rshb.testapp.dao.entity.TransactionDeposit;
import dev.redlab.rshb.testapp.dao.entity.TransactionWithdraw;
import dev.redlab.rshb.testapp.dao.repository.TransactionDepositRepository;
import dev.redlab.rshb.testapp.dao.repository.TransactionRepository;
import dev.redlab.rshb.testapp.dao.repository.TransactionWithdrawRepository;
import dev.redlab.rshb.testapp.dto.request.DepositRequest;
import dev.redlab.rshb.testapp.dto.request.WithdrawRequest;
import dev.redlab.rshb.testapp.exceptions.NotEnoughOnBalanceException;
import lombok.extern.log4j.Log4j2;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@ActiveProfiles("default")
@SpringBootTest
@Log4j2
public class TransactionServiceTest {

    @Container
    @ClassRule
    public static PostgreSQLContainer<?> postgreSQLContainer = TestappPostgresqlContainer.getInstance();

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionDepositRepository transactionDepositRepository;

    @Autowired
    private TransactionWithdrawRepository transactionWithdrawRepository;

    @Autowired
    private TestSupport testSupport;

    @Test
    @Transactional
    public void testNewTransaction() {
        Transaction transaction = transactionService.newTransaction();

        Optional<Transaction> transaction2 = transactionRepository.findById(transaction.getId());
        assertThat(transaction2.orElseThrow().getId(), equalTo(transaction.getId()));
    }

    @Test
    @Transactional
    public void testDeposit() {
        Account account = testSupport.createAccount("Account for deposit").get(0);
        Transaction transaction = transactionService.newTransaction();
        Account account2 = transactionService.deposit(DepositRequest.builder()
                .transactionId(transaction.getId())
                .accountId(account.getId())
                .deposit(BigDecimal.TEN)
                .build());

        Optional<TransactionDeposit> transaction2 = transactionDepositRepository.findById(transaction.getId());
        assertThat(transaction2.orElseThrow().getId(), equalTo(transaction.getId()));
        assertThat(transaction2.orElseThrow().getAccountId(), equalTo(account.getId()));
        assertThat(transaction2.orElseThrow().getAmount(), equalTo(BigDecimal.TEN));

        assertThat(account2.getBalance(), equalTo(BigDecimal.TEN));
    }

    @Test
    @Transactional
    public void testDepositTwice() {
        Account account = testSupport.createAccount("Account for deposit").get(0);
        Transaction transaction = transactionService.newTransaction();
        Account account2 = transactionService.deposit(DepositRequest.builder()
                .transactionId(transaction.getId())
                .accountId(account.getId())
                .deposit(BigDecimal.TEN)
                .build());
        Account account3 = transactionService.deposit(DepositRequest.builder()
                .transactionId(transaction.getId())
                .accountId(account.getId())
                .deposit(BigDecimal.TEN)
                .build());

        Optional<TransactionDeposit> transaction2 = transactionDepositRepository.findById(transaction.getId());
        assertThat(transaction2.orElseThrow().getId(), equalTo(transaction.getId()));
        assertThat(transaction2.orElseThrow().getAccountId(), equalTo(account.getId()));
        assertThat(transaction2.orElseThrow().getAmount(), equalTo(BigDecimal.TEN));

        assertThat(account2.getBalance(), equalTo(BigDecimal.TEN));
        assertThat(account3.getBalance(), equalTo(BigDecimal.TEN));
    }

    @Test(expected = NotEnoughOnBalanceException.class)
    @Transactional
    public void testWithdrawNotEnough() {
        Account account = testSupport.createAccount("Account for withdrawal").get(0);

        Transaction transaction = transactionService.newTransaction();
        transactionService.withdraw(WithdrawRequest.builder()
                .transactionId(transaction.getId())
                .accountId(account.getId())
                .withdraw(BigDecimal.ONE)
                .build());
    }

    @Test
    @Transactional
    public void testWithdraw() {
        Account account = testSupport.createAccount("Account for withdrawal").get(0);

        Transaction transaction = transactionService.newTransaction();
        transactionService.deposit(DepositRequest.builder()
                .transactionId(transaction.getId())
                .accountId(account.getId())
                .deposit(BigDecimal.TEN)
                .build());

        transaction = transactionService.newTransaction();
        Account account2 = transactionService.withdraw(WithdrawRequest.builder()
                .transactionId(transaction.getId())
                .accountId(account.getId())
                .withdraw(BigDecimal.TEN)
                .build());

        Optional<TransactionWithdraw> transaction2 = transactionWithdrawRepository.findById(transaction.getId());
        assertThat(transaction2.orElseThrow().getId(), equalTo(transaction.getId()));
        assertThat(transaction2.orElseThrow().getAccountId(), equalTo(account.getId()));
        assertThat(transaction2.orElseThrow().getAmount(), equalTo(BigDecimal.TEN));

        assertThat(account2.getBalance(), equalTo(BigDecimal.ZERO));
    }

    @Test
    @Transactional
    public void testWithdrawTwice() {
        Account account = testSupport.createAccount("Account for deposit").get(0);

        Transaction transaction = transactionService.newTransaction();
        transactionService.deposit(DepositRequest.builder()
                .transactionId(transaction.getId())
                .accountId(account.getId())
                .deposit(BigDecimal.TEN)
                .build());


        transaction = transactionService.newTransaction();
        Account account2 = transactionService.withdraw(WithdrawRequest.builder()
                .transactionId(transaction.getId())
                .accountId(account.getId())
                .withdraw(BigDecimal.TEN)
                .build());
        Account account3 = transactionService.withdraw(WithdrawRequest.builder()
                .transactionId(transaction.getId())
                .accountId(account.getId())
                .withdraw(BigDecimal.TEN)
                .build());

        Optional<TransactionWithdraw> transaction2 = transactionWithdrawRepository.findById(transaction.getId());
        assertThat(transaction2.orElseThrow().getId(), equalTo(transaction.getId()));
        assertThat(transaction2.orElseThrow().getAccountId(), equalTo(account.getId()));
        assertThat(transaction2.orElseThrow().getAmount(), equalTo(BigDecimal.TEN));

        assertThat(account2.getBalance(), equalTo(BigDecimal.ZERO));
        assertThat(account3.getBalance(), equalTo(BigDecimal.ZERO));
    }

}
