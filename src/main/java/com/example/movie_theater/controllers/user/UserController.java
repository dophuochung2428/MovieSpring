package com.example.movie_theater.controllers.user;

import com.example.movie_theater.dtos.UserDTO;
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

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    @PutMapping("/{id}/disable")
    public ResponseEntity<String> disableUser(@PathVariable Long id) {
        userService.disableUser(id);
        return ResponseEntity.ok("User has been disabled successfully.");
    }
}
