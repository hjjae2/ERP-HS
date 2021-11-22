package com.hserp.work.repository;

import com.hserp.work.domain.ExpenditureStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExpenditureStatusRepository extends JpaRepository<ExpenditureStatus, Integer> {
    Optional<ExpenditureStatus> findByName(String name);
}
