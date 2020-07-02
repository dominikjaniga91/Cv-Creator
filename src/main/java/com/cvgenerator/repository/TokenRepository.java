package com.cvgenerator.repository;

import com.cvgenerator.domain.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> getTokenByValue(String value);

    @Modifying
    @Query("DELETE FROM Token token WHERE token.expiryDate <= ?1")
    void deleteAllExpiredTokens(LocalDateTime time);
}
