package com.datacollection.server.controller;

import com.datacollection.server.model.DCGroup;
import com.datacollection.server.model.User;
import com.datacollection.server.repository.DCGroupRepository;
import com.datacollection.server.repository.PermissionRepository;
import com.datacollection.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PermissionController {
    @Autowired
    PermissionRepository permissionRepository;
}
