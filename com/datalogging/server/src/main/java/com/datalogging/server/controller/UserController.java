package com.datalogging.server.controller;

import com.datalogging.server.model.User;
import com.datalogging.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/unauthorized/user/register")
    public ResponseEntity<?> register(@RequestParam(value = "username") String username,
                                      @RequestParam(value = "email") String email,
                                      @RequestParam(value = "password") String password) {
        User user = userRepository.findByEmail(email);

        if (user != null) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        } else {
            User newUser = new User(username, email, password);
            userRepository.save(newUser);

            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @GetMapping("/unauthorized/user/authorize")
    public ResponseEntity<?> authorize(@RequestParam(value = "username") String username,
                                       @RequestParam(value = "password") String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);

        if (user != null) {
            String token = UUID.randomUUID().toString();

            user.setToken(token);
            userRepository.save(user);

            return ResponseEntity.status(HttpStatus.OK).body(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }

    @GetMapping("/authorized/user/authenticate")
    public ResponseEntity<?> authenticate(@RequestParam(value = "username") String username, @RequestParam(value = "token") String token) {
        User user = userRepository.findByUsernameAndToken(username, token);

        if (user != null) {
            return ResponseEntity.status((HttpStatus.OK)).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
