package com.cvgenerator.utils.scheduler;

import com.cvgenerator.repository.TokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Slf4j
@Service
@Transactional
public class DeleteTokenTask {

    private final TokenRepository tokenRepository;

    @Autowired
    public DeleteTokenTask(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    /**
     *
     * Schedule task that delete expired Token from database.
     * Task is executing everyday from Monday to Friday at 12 pm.
     *
     */
    @Scheduled(cron = "${token.cron}")
    public void deleteAllExpiredTokens(){
        log.info("deleting tokens task");
        tokenRepository.deleteAllExpiredTokens(LocalDateTime.now());
    }

}
