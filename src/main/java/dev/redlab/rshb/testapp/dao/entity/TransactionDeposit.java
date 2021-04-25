package dev.redlab.rshb.testapp.dao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "transactions_deposit")
@Data
@NoArgsConstructor
public class TransactionDeposit {

    @Id
    private Long id;

    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "amount")
    private BigDecimal amount;

}
