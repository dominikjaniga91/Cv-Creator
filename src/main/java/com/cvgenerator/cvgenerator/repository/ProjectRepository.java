package com.cvgenerator.cvgenerator.repository;

import com.cvgenerator.cvgenerator.domain.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
