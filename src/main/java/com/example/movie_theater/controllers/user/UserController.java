package com.example.movie_theater.controllers.user;

import com.example.movie_theater.entities.User;
import com.example.movie_theater.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        User existingUser = userService.getUserById(id);
        existingUser.setPassword(user.getPassword());
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        return ResponseEntity.ok(userService.saveUser(existingUser));
    }

    @GetMapping("/{username}")
    public Optional<User> getUserByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }
}
