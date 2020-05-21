package com.cvgenerator.repository;

import com.cvgenerator.domain.entity.User;
import com.cvgenerator.domain.entity.UserCv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserCvRepository extends JpaRepository<UserCv, Long> {

    Optional<List<UserCv>> getAllByUser(User user);
}
