package dev.redlab.rshb.testapp.dao.repository;

import dev.redlab.rshb.testapp.dao.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {

}
