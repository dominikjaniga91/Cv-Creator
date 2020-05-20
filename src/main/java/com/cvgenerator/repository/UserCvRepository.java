package com.cvgenerator.repository;

import com.cvgenerator.domain.entity.UserCv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCvRepository extends JpaRepository<UserCv, Long> {


}
