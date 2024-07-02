package org.example.service;

import org.example.entity.LoginSession;

public interface LoginSessionService {
    void saveLoginSession(LoginSession loginSession);
    LoginSession getLoginSessionByUserId(Long userId);
    LoginSession findById(Long sessionId);
}
