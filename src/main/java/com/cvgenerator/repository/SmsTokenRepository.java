package com.cvgenerator.repository;

import com.cvgenerator.domain.entity.SmsToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface SmsTokenRepository extends JpaRepository<SmsToken, Long> {

    Optional<SmsToken> getSmsTokenByValue(String value);

    @Modifying
    @Query("DELETE FROM SmsToken smsToken WHERE smsToken.expiryDate <= ?1")
    void deleteAllExpiredSmsTokens(LocalDateTime time);
}
