package com.hserp.repository.work;

import com.hserp.entity.work.ExpenditureStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExpenditureStatusRepository extends JpaRepository<ExpenditureStatus, Integer> {
    Optional<ExpenditureStatus> findByName(String name);
}
