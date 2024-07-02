package org.example.repository;

import org.example.entity.LoginSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginSessionRepository extends JpaRepository<LoginSession, Long> {
    LoginSession findByUserId(Long userId);
}
