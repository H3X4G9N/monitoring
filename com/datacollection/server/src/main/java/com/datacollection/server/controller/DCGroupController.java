package com.datacollection.server.controller;

import com.datacollection.server.Utility;
import com.datacollection.server.model.DCGroup;
import com.datacollection.server.model.User;
import com.datacollection.server.repository.DCGroupRepository;
import com.datacollection.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PreUpdate;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DCGroupController {
    @Autowired
    DCGroupRepository dcGroupRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/dc-group")
    public ResponseEntity<?> getAllVisibleDCGroups() {
        List<DCGroup> dcGroups = dcGroupRepository.findAllVisible();

        for (DCGroup dcGroup : dcGroups) {
            dcGroup.setPassword("");
            dcGroup.setToken("");
        }

        return ResponseEntity.status(HttpStatus.OK).body(dcGroups);
    }

    @GetMapping("/dc-group/{id}")
    public ResponseEntity<?> getVisibleDCGroupById(
            @PathVariable(value = "id") Long id) {
        DCGroup dcGroup = dcGroupRepository.findVisibleByID(id);

        return ResponseEntity.status(HttpStatus.OK).body(dcGroup);
    }

    @GetMapping("/dc-group/{id}/authorize")
    public ResponseEntity<?> authorize(
            @PathVariable(value = "id") Long id,
            @RequestParam(value = "password") String password) {
        DCGroup dcGroup = dcGroupRepository.findVisibleByIDAndPassword(id, password);

        if (dcGroup != null) {
            dcGroup.setToken(Utility.generateToken());
            return ResponseEntity.status(HttpStatus.OK).body(dcGroup.getToken());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/dc-group/{user-id}")
    public ResponseEntity<?> createDCGroup(
            @PathVariable(value = "user-id") Long userID,
            @RequestParam(value = "user-token") String userToken,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "visible") Boolean visible,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "permission-required") Boolean permissionRequired) {
        User user = userRepository.findByIDAndToken(userID, userToken);

        if (user != null) {
            if (dcGroupRepository.findByName(name) != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            } else {
                DCGroup dcGroup = new DCGroup(name, description, visible, password, permissionRequired, Utility.generateToken(), user);
                dcGroupRepository.save(dcGroup);

                return ResponseEntity.status(HttpStatus.OK).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/dc-group/{user-id}")
    public ResponseEntity<?> getAllDCGroupsByUser(
            @PathVariable(value = "user-id") Long userId,
            @RequestParam(value = "user-token") String userToken) {
        User user = userRepository.findByIDAndToken(userId, userToken);

        if (user != null) {
            return ResponseEntity.status(HttpStatus.OK).body(dcGroupRepository.findAllByUser(user.getUsername()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/dc-group/{user-id}/{id}")
    public ResponseEntity<?> getDCGroupByUserAndName(
            @PathVariable(value = "user-id") Long userID,
            @PathVariable(value = "id") Long id,
            @RequestParam(value = "user-token") String userToken) {
        User user = userRepository.findByIDAndToken(userID, userToken);

        if (user != null) {
            return ResponseEntity.status(HttpStatus.OK).body(dcGroupRepository.findByID(id));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping("/dc-group/{user-id}/{id}")
    public ResponseEntity<?> updateDCGroup(
            @PathVariable(value = "user-id") Long userID,
            @PathVariable(value = "id") Long id,
            @RequestParam(value = "user-token") String userToken,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "visible") Boolean visible,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "permission-required") Boolean permissionRequired) {
        User user = userRepository.findByIDAndToken(userID, userToken);

        if (user != null) {
            DCGroup dcGroup = dcGroupRepository.findByID(id);

            if (dcGroup != null) {
                dcGroup.setName(name);
                dcGroup.setDescription(description);
                dcGroup.setVisible(visible);
                dcGroup.setPassword(password);
                dcGroup.setPermissionRequired(permissionRequired);
                dcGroup.setUser(user);
                dcGroupRepository.save(dcGroup);

                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @DeleteMapping("/dc-group/{user-id}/{id}")
    public ResponseEntity<?> deleteDCGroup(
            @PathVariable(value = "user-id") Long userID,
            @PathVariable(value = "id") Long id,
            @RequestParam(value = "user-token") String userToken) {
        User user = userRepository.findByIDAndToken(id, userToken);

        if (user != null) {
            DCGroup dcGroup = dcGroupRepository.findByID(id);

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
