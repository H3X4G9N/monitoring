package com.datacollection.server.controller;

import com.datacollection.server.model.DCGroup;
import com.datacollection.server.model.DCGroupAuthentication;
import com.datacollection.server.model.User;
import com.datacollection.server.repository.DCGroupAuthenticationRepository;
import com.datacollection.server.repository.DCGroupRepository;
import com.datacollection.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class DCGroupController {
    @Autowired
    DCGroupRepository dcGroupRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DCGroupAuthenticationRepository dcGroupAuthenticationRepository;

    @GetMapping("/unauthorized/dc-group")
    public ResponseEntity<?> getAllVisibleDCGroups() {
        List<DCGroup> dcGroups = dcGroupRepository.findAllVisible();

        return ResponseEntity.status(HttpStatus.OK).body(dcGroups);
    }

    @GetMapping("/unauthorized/dc-group/{name}/authorize")
    public ResponseEntity<?> authorize(
            @PathVariable(value = "name") String name,
            @RequestParam(value = "password") String password) {
        DCGroup dcGroup = dcGroupRepository.findVisibleByNameAndPassword(name, password);

        if (dcGroup != null) {
            DCGroupAuthentication dcGroupAuthentication = new DCGroupAuthentication();
            dcGroupAuthentication.setDcGroup(dcGroup);
            dcGroupAuthentication.setToken("dc-group-token");
            dcGroupAuthenticationRepository.save(dcGroupAuthentication);

            return ResponseEntity.status(HttpStatus.OK).body(dcGroupAuthentication);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/authorized/dc-group/{user-username}")
    public ResponseEntity<?> createDCGroup(
            @PathVariable(value = "user-username") String userUsername,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "visible") Boolean visible,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "permission-required") Boolean permissionRequired,
            @RequestParam(value = "user-token") String userToken) {
        User user = userRepository.findByUsernameAndToken(userUsername, userToken);

        if (user != null) {
            if (dcGroupRepository.findByName(name) != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            } else {
                DCGroup dcGroup = new DCGroup(name, description, visible, password, permissionRequired, user);

                dcGroupRepository.save(dcGroup);

                return ResponseEntity.status(HttpStatus.OK).body(dcGroup);
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/authorized/dc-group/{user-username}")
    public ResponseEntity<?> getAllDCGroupsFromUser(
            @PathVariable(value = "user-username") String userUsername,
            @RequestParam(value = "user-token") String userToken) {
        User user = userRepository.findByUsernameAndToken(userUsername, userToken);

        if (user != null) {
            return ResponseEntity.status(HttpStatus.OK).body(dcGroupRepository.findAllByUser(user.getID()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping("/authorized/dc-group/{user-username}/{name}")
    public ResponseEntity<?> updateDCGroup(
            @PathVariable(value = "user-username") String userUsername,
            @PathVariable(value = "name") String currentName,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "visible") Boolean visible,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "permission-required") Boolean permissionRequired,
            @RequestParam(value = "user-token") String userToken) {
        User user = userRepository.findByUsernameAndToken(userUsername, userToken);

        if (user != null) {
            DCGroup dcGroup = dcGroupRepository.findByNameAndUser(currentName, user.getID());

            if (dcGroup != null) {
                dcGroup.setName(name);
                dcGroup.setDescription(description);
                dcGroup.setVisible(visible);
                dcGroup.setPassword(password);
                dcGroup.setPermissionRequired(permissionRequired);

                dcGroupRepository.save(dcGroup);

                return ResponseEntity.status(HttpStatus.OK).body(dcGroup);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @DeleteMapping("/authorized/dc-group/{user-username}/{name}")
    public ResponseEntity<?> deleteDCGroup(
            @PathVariable(value = "user-username") String userUsername,
            @PathVariable(value = "name") String name,
            @RequestParam(value = "user-token") String userToken) {
        User user = userRepository.findByUsernameAndToken(userUsername, userToken);

        if (user != null) {
            DCGroup dcGroup = dcGroupRepository.findByNameAndUser(name, user.getID());

            if (dcGroup != null) {
                dcGroupRepository.delete(dcGroup);

                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
