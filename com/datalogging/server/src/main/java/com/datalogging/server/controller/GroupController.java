package com.datalogging.server.controller;

import com.datalogging.server.model.Group;
import com.datalogging.server.model.GroupAuthentication;
import com.datalogging.server.model.User;
import com.datalogging.server.repository.GroupAuthenticationRepository;
import com.datalogging.server.repository.GroupRepository;
import com.datalogging.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class GroupController {
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    GroupAuthenticationRepository groupAuthenticationRepository;

    @GetMapping("/unauthorized/group")
    public ResponseEntity<?> getAllVisible() {
        List<Group> groups = groupRepository.findAllVisible();

        return ResponseEntity.status(HttpStatus.OK).body(groups);
    }

    @GetMapping("/unauthorized/group/{name}/authorize")
    public ResponseEntity<?> authorize(
            @PathVariable(value = "name") String name,
            @RequestParam(value = "password") String password) {
        Group group = groupRepository.findVisibleByNameAndPassword(name, password);

        if (group != null) {
            GroupAuthentication groupAuthentication = new GroupAuthentication();
            groupAuthentication.setGroup(group);
            groupAuthentication.setToken(UUID.randomUUID().toString());
            groupAuthenticationRepository.save(groupAuthentication);

            return ResponseEntity.status(HttpStatus.OK).body(groupAuthentication);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping("/authorized/group/root/{user-username}/{name}")
    public ResponseEntity<?> setRoot(
            @PathVariable(value = "user-username") String userUsername,
            @PathVariable(value = "name") String name,
            @RequestParam(value = "user-token") String userToken) {
        User user = userRepository.findByUsernameAndToken(userUsername, userToken);

        if (user != null) {
            Group group = groupRepository.findByNameAndUser(name, user.getID());

            if (group != null) {
                List<Group> groupList = groupRepository.findAllByUser(user.getID());

                for (Group group2 : groupList) {
                    group2.setRoot(false);
                }

                group.setRoot(true);

                groupRepository.saveAll(groupList);
                groupRepository.save(group);

                return ResponseEntity.status(HttpStatus.OK).body(group);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/authorized/group/{user-username}")
    public ResponseEntity<?> create(
            @PathVariable(value = "user-username") String userUsername,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "visible") Boolean visible,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "permission-required") Boolean permissionRequired,
            @RequestParam(value = "user-token") String userToken) {
        User user = userRepository.findByUsernameAndToken(userUsername, userToken);

        if (user != null) {
            if (groupRepository.findByName(name) != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            } else {
                Group group = new Group(name, description, visible, password, permissionRequired, user);
                group.setRoot(false);

                groupRepository.save(group);

                return ResponseEntity.status(HttpStatus.OK).body(group);
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/authorized/group/{user-username}")
    public ResponseEntity<?> getAllFromUser(
            @PathVariable(value = "user-username") String userUsername,
            @RequestParam(value = "user-token") String userToken) {
        User user = userRepository.findByUsernameAndToken(userUsername, userToken);

        if (user != null) {
            return ResponseEntity.status(HttpStatus.OK).body(groupRepository.findAllByUser(user.getID()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping("/authorized/group/{user-username}/{name}")
    public ResponseEntity<?> update(
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
            Group group = groupRepository.findByNameAndUser(currentName, user.getID());

            if (group != null) {
                group.setName(name);
                group.setDescription(description);
                group.setVisible(visible);
                group.setPassword(password);
                group.setPermissionRequired(permissionRequired);

                groupRepository.save(group);

                return ResponseEntity.status(HttpStatus.OK).body(group);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @DeleteMapping("/authorized/group/{user-username}/{name}")
    public ResponseEntity<?> delete(
            @PathVariable(value = "user-username") String userUsername,
            @PathVariable(value = "name") String name,
            @RequestParam(value = "user-token") String userToken) {
        User user = userRepository.findByUsernameAndToken(userUsername, userToken);

        if (user != null) {
            Group group = groupRepository.findByNameAndUser(name, user.getID());

            if (group != null) {
                groupRepository.delete(group);

                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
