package com.cvgenerator.cvgenerator.repository;

import com.cvgenerator.cvgenerator.domain.entity.Interest;
import com.cvgenerator.cvgenerator.domain.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestRepository extends JpaRepository<Interest, Long> {


}
