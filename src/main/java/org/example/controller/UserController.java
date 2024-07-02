package org.example.controller;

import org.example.entity.BankItem;
import org.example.entity.BankItemUserRequest;
import org.example.entity.User;
import org.example.service.BankService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BankService bankService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = Optional.ofNullable(userService.getUserById(id));
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody User user) {
        if (user.getId() == null) {
            userService.addUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User created with ID: " + user.getId());
        } else {
            // Assuming userService.getUserById() returns null if the user does not exist
            User existingUser = userService.getUserById(user.getId());
            if (existingUser != null) {
                return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("User is present with ID: " + user.getId());
            } else {
                userService.addUser(user);
                return ResponseEntity.status(HttpStatus.CREATED).body("User created with ID: " + user.getId());
            }
        }
    }




    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User user) {
        Optional<User> existingUser = Optional.ofNullable(userService.getUserById(id));
        if (existingUser.isPresent()) {
            user.setId(id);
            userService.updateUser(user);
            return ResponseEntity.ok("User successfully updated");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User successfully deleted");
    }



}
