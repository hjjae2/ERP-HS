package com.hserp.work.repository;

import com.hserp.work.domain.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WorkRepository extends JpaRepository<Work, Integer> {

    List<Work> findAllByWorkDateBetween(LocalDate from, LocalDate to);
}
