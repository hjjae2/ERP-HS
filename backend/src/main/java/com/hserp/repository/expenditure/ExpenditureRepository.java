package com.hserp.repository.expenditure;

import com.hserp.entity.expenditure.Expenditure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenditureRepository extends JpaRepository<Expenditure, Integer> {
}
