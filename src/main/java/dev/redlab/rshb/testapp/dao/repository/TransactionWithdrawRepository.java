package dev.redlab.rshb.testapp.dao.repository;

import dev.redlab.rshb.testapp.dao.entity.TransactionDeposit;
import dev.redlab.rshb.testapp.dao.entity.TransactionWithdraw;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionWithdrawRepository extends JpaRepository<TransactionWithdraw, Long> {

}
