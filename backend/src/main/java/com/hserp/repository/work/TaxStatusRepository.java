package com.hserp.repository.work;

import com.hserp.entity.work.TaxStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaxStatusRepository extends JpaRepository<TaxStatus, Integer> {
    Optional<TaxStatus> findByName(String name);
}
