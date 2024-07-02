package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "login_session")
@Data
public class LoginSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionId;

    private Long userId;
    private String login;
    private String password;

    public LoginSession(Long userId, String login, String password) {
        this.userId = userId;
        this.login = login;
        this.password = password;
    }

    public LoginSession(Long sessionId, Long userId, String login, String password) {
        this.sessionId = sessionId;
        this.userId = userId;
        this.login = login;
        this.password = password;
    }

    public LoginSession() {
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
