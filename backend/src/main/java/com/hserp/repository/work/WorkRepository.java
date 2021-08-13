package com.hserp.repository.work;

import com.hserp.entity.work.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkRepository extends JpaRepository<Work, Integer> {
}
