package dev.redlab.rshb.testapp.service;

import dev.redlab.rshb.testapp.TestappPostgresqlContainer;
import dev.redlab.rshb.testapp.dao.entity.Transaction;
import dev.redlab.rshb.testapp.dao.repository.TransactionRepository;
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

    @Test
    @Transactional
    public void testNewTransaction() {
        Transaction transaction = transactionService.newTransaction();

        Optional<Transaction> transaction2 = transactionRepository.findById(transaction.getId());
        assertThat(transaction2.orElseThrow().getId(), equalTo(transaction.getId()));
    }

}
