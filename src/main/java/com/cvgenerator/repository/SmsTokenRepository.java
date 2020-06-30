package com.cvgenerator.repository;

import com.cvgenerator.domain.entity.SmsToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SmsTokenRepository extends JpaRepository<SmsToken, Long> {

    Optional<SmsToken> getSmsTokenByValue(String value);
}
