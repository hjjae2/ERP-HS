package com.hserp.work.repository;

import com.hserp.work.domain.WorkType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkTypeRepository extends JpaRepository<WorkType, Integer> {
    Optional<WorkType> findByName(String name);
}
