package com.datalogging.server.controller;

import com.datalogging.server.model.Group;
import com.datalogging.server.model.GroupAuthentication;
import com.datalogging.server.model.GroupPermission;
import com.datalogging.server.model.User;
import com.datalogging.server.repository.GroupAuthenticationRepository;
import com.datalogging.server.repository.GroupPermissionRepository;
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
    UserRepository userRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    GroupPermissionRepository groupPermissionRepository;
    @Autowired
    GroupAuthenticationRepository groupAuthenticationRepository;

    @GetMapping("/authorized/group/root/{user-username}")
    public ResponseEntity<?> getRootFromUser(
            @PathVariable(value = "user-username") String userUsername,
            @RequestParam(value = "user-token") String userToken) {
        User user = userRepository.findByUsernameAndToken(userUsername, userToken);

        if (user != null) {
            return ResponseEntity.status(HttpStatus.OK).body(groupRepository.findRootBydUser(user.getID()));
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

    @PostMapping("/authorized/group/{user-username}")
    public ResponseEntity<?> create(
            @PathVariable(value = "user-username") String userUsername,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "visible") Boolean visible,
            @RequestParam(value = "password-required") Boolean passwordRequired,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "permission-required") Boolean permissionRequired,
            @RequestParam(value = "user-token") String userToken) {
        User user = userRepository.findByUsernameAndToken(userUsername, userToken);

        if (user != null) {
            Group group = new Group(name, description, visible, passwordRequired, password, permissionRequired, user);
            group.setRoot(false);
            groupRepository.save(group);

            return ResponseEntity.status(HttpStatus.OK).body(group);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping("/authorized/group/{user-username}/{id}")
    public ResponseEntity<?> update(
            @PathVariable(value = "user-username") String userUsername,
            @PathVariable(value = "id") Long id,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "visible") Boolean visible,
            @RequestParam(value = "password-required") Boolean passwordRequired,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "permission-required") Boolean permissionRequired,
            @RequestParam(value = "user-token") String userToken) {
        User user = userRepository.findByUsernameAndToken(userUsername, userToken);

        if (user != null) {
            Group group = groupRepository.findByIDAndUser(id, user.getID());

            if (group != null) {
                if (group.getRoot()) {
                    group.setRootName(name);
                } else {
                    group.setName(name);
                }
                group.setDescription(description);
                group.setVisible(visible);
                group.setPasswordRequired(passwordRequired);
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

    @DeleteMapping("/authorized/group/{user-username}/{id}")
    public ResponseEntity<?> delete(
            @PathVariable(value = "user-username") String userUsername,
            @PathVariable(value = "id") Long id,
            @RequestParam(value = "user-token") String userToken) {
        User user = userRepository.findByUsernameAndToken(userUsername, userToken);

        if (user != null) {
            Group group = groupRepository.findByIDAndUser(id, user.getID());

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

    @GetMapping("/authorized/group/{id}/{user-username}/authorize")
    public ResponseEntity<?> authorize(
            @PathVariable(value = "id") Long id,
            @PathVariable(value = "user-username") String userUsername,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "user-token") String userToken) {
        User user = userRepository.findByUsernameAndToken(userUsername, userToken);

        if (user != null) {
            Group group = groupRepository.findByID(id);

            if (group != null) {
                GroupAuthentication groupAuthentication = null;

                if ((!group.getPasswordRequired() || password.equals(group.getPassword()))) {
                    groupAuthentication = new GroupAuthentication(UUID.randomUUID().toString(), group);
                    groupAuthenticationRepository.save(groupAuthentication);
                }

                if (group.getPermissionRequired() && group.getUser().getID() != user.getID()) {
                    GroupPermission groupPermission = groupPermissionRepository.findByPermittedUserAndPermissibleGroup(user.getID(), group.getID());

                    if (groupPermission == null) {
                        groupAuthentication = null;
                    }
                }

                if (groupAuthentication != null) {
                    return ResponseEntity.status(HttpStatus.OK).body(groupAuthentication);
                }

                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/unauthorized/group/root")
    public ResponseEntity<?> getAllVisibleRoot() {
        List<Group> groups = groupRepository.findAllByRootAndVisible();

        for (Group group : groups) {
            group.setPassword("");
        }

        return ResponseEntity.status(HttpStatus.OK).body(groups);
    }

    @GetMapping("/unauthorized/group/{id}")
    public ResponseEntity<?> getAllVisible(
            @PathVariable(value = "id") Long id,
            @RequestParam(value = "token") String token
    ) {
        GroupAuthentication groupAuthentication = groupAuthenticationRepository.findByTokenAndGroup(token, id);

        if (groupAuthentication != null) {
            List<Group> groupList = groupRepository.findAllVisibleByUser(groupAuthentication.getGroup().getUser().getID());

            for (Group group : groupList) {
                group.setPassword("");
            }

            return ResponseEntity.status(HttpStatus.OK).body(groupList);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/unauthorized/group/{id}/authorize")
    public ResponseEntity<?> authorize(
            @PathVariable(value = "id") Long id,
            @RequestParam(value = "password", required = false) String password) {
        Group group = groupRepository.findByID(id);

        if (group != null) {
            GroupAuthentication groupAuthentication = null;

            if ((!group.getPasswordRequired() || password.equals(group.getPassword())) && !group.getPermissionRequired()) {
                groupAuthentication = new GroupAuthentication(UUID.randomUUID().toString(), group);
                groupAuthenticationRepository.save(groupAuthentication);
            }

            if (groupAuthentication != null) {
                return ResponseEntity.status(HttpStatus.OK).body(groupAuthentication);
            }

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/unauthorized/group/{id}/authenticate")
    public ResponseEntity<?> authenticate(
            @PathVariable(value = "id") Long id,
            @RequestParam(value = "token") String token) {
        Group group = groupRepository.findByID(id);

        if (group != null) {
            GroupAuthentication groupAuthentication = groupAuthenticationRepository.findByTokenAndGroup(token, id);

            if (groupAuthentication != null) {
                return ResponseEntity.status(HttpStatus.OK).body(groupAuthentication);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
