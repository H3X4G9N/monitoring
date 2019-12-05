package com.datacollection.server.controller;

import com.datacollection.server.model.User;
import com.datacollection.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.datacollection.server.Utility;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/user/register")
    public ResponseEntity<?> register(@RequestParam(value = "username") String username,
                                           @RequestParam(value = "email") String email,
                                           @RequestParam(value = "password") String password) {
        User user = userRepository.findByEmail(email);

        if (user != null) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        } else {
            String token = Utility.generateToken();

            User newUser = new User(username, email, password, token);
            userRepository.save(newUser);

            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @GetMapping("/user/authorize")
    public ResponseEntity<?> authorize(@RequestParam(value = "username") String username,
                            @RequestParam(value = "password") String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);

        if (user != null) {
            String token =  Utility.generateToken();

            user.setToken(token);
            userRepository.save(user);

            return ResponseEntity.status(HttpStatus.OK).body(token);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }

    @GetMapping("/user/authenticate")
    public ResponseEntity<?> authenticate(@RequestParam(value = "username") String username, @RequestParam(value = "token") String token) {
        User user = userRepository.findByUsernameAndToken(username, token);

        if (user != null) {
            return ResponseEntity.status((HttpStatus.OK)).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
