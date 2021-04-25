package dev.redlab.rshb.testapp.service;

import dev.redlab.rshb.testapp.TestappPostgresqlContainer;
import dev.redlab.rshb.testapp.dao.entity.Account;
import dev.redlab.rshb.testapp.dao.repository.AccountRepository;
import dev.redlab.rshb.testapp.dto.request.CreateAccountRequest;
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
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

@RunWith(SpringRunner.class)
@ActiveProfiles("default")
@SpringBootTest
@Log4j2
public class AccountServiceTest {


    @Container
    @ClassRule
    public static PostgreSQLContainer<?> postgreSQLContainer = TestappPostgresqlContainer.getInstance();

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TestSupport testSupport;

    @Test
    @Transactional
    public void testCreateAccount() {
        accountService.createAccount(CreateAccountRequest.builder()
                .name("Account name")
                .build());

        List<Account> accounts = accountRepository.findAll();
        assertThat(accounts.size(), greaterThan(0));
    }

    @Test
    @Transactional
    public void testGetAccount() {
        List<Account> accounts = testSupport.createAccount("Account to get");

        Account account = accountService.getAccountInfo(accounts.get(0).getId());

        assertThat(account.getName(), equalTo("Account to get"));
        assertThat(account.getBalance(), equalTo(BigDecimal.ZERO));
    }

}
