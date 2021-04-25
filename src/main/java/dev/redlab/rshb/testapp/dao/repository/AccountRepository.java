package dev.redlab.rshb.testapp.dao.repository;

import dev.redlab.rshb.testapp.dao.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
