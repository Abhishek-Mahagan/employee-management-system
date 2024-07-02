package org.example.controller;

import org.example.entity.LoginSession;
import org.example.entity.User;
import org.example.service.LoginSessionService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class BankAuth {

    @Autowired
    private UserService userService;

    @Autowired
    private LoginSessionService loginSessionService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String login, @RequestParam String password) {
        User user = userService.verifyLogin(login, password);
        if (user != null) {
            LoginSession loginSession = new LoginSession(user.getId(), login, password);
            loginSessionService.saveLoginSession(loginSession);
            return ResponseEntity.status(HttpStatus.CREATED).body("Successfully logged in. Session ID: " + loginSession.getSessionId());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Failed to log in");
        }
    }
}
