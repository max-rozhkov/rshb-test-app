package dev.redlab.rshb.testapp.dao.repository;

import dev.redlab.rshb.testapp.dao.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
