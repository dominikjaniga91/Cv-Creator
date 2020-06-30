package com.cvgenerator.repository;

import com.cvgenerator.domain.entity.Clause;
import com.cvgenerator.domain.entity.Summary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClauseRepository extends JpaRepository<Clause, Long> {
}
