package com.tomangg.repository;

import com.tomangg.model.ApiAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ApiAuthRepository extends JpaRepository<ApiAuth, Long> {
    Optional<ApiAuth> findByUsername(String username);
}