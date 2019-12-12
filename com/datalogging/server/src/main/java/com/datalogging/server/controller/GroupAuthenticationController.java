package com.datalogging.server.controller;

import com.datalogging.server.repository.GroupAuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GroupAuthenticationController {
    @Autowired
    GroupAuthenticationRepository groupAuthenticationRepository;
}
