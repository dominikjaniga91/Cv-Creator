package com.cvgenerator.cvgenerator.repository;

import com.cvgenerator.cvgenerator.domain.entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {
}
