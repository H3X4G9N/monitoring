package com.datacollection.server.controller;

import com.datacollection.server.repository.DCGroupAuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DCGroupAuthenticationController {
    @Autowired
    DCGroupAuthenticationRepository dcGroupAuthenticationRepository;
}
