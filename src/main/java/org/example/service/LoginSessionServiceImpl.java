package org.example.service;

import org.example.entity.LoginSession;
import org.example.repository.LoginSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginSessionServiceImpl implements LoginSessionService {

    @Autowired
    private LoginSessionRepository loginSessionRepository;

    @Override
    public void saveLoginSession(LoginSession loginSession) {
        loginSessionRepository.save(loginSession);
    }

    @Override
    public LoginSession getLoginSessionByUserId(Long userId) {
        return loginSessionRepository.findByUserId(userId);
    }

    @Override
    public LoginSession findById(Long sessionId) {
        return loginSessionRepository.findById(sessionId).orElse(null);
    }
}
