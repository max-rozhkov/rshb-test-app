package dev.redlab.rshb.testapp.dao.repository;

import dev.redlab.rshb.testapp.dao.entity.Transaction;
import dev.redlab.rshb.testapp.dao.entity.TransactionDeposit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDepositRepository extends JpaRepository<TransactionDeposit, Long> {

}
