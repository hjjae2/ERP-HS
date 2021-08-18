package com.hserp.repository.work;

import com.hserp.entity.work.WorkType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkTypeRepository extends JpaRepository<WorkType, Integer> {
    Optional<WorkType> findByName(String name);
}
