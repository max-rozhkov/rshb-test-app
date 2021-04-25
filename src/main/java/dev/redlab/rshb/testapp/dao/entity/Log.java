package dev.redlab.rshb.testapp.dao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "logs")
@Data
@NoArgsConstructor
public class Log {

    @Id
    @SequenceGenerator(name = "main_seq", sequenceName = "main_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "main_seq")
    private Long id;

    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "message")
    private String message;

    @Column(name = "create_date")
    private LocalDateTime createDate;

}
