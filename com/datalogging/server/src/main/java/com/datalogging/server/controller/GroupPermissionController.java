package com.datalogging.server.controller;

import com.datalogging.server.model.Group;
import com.datalogging.server.model.GroupPermission;
import com.datalogging.server.model.User;
import com.datalogging.server.repository.GroupPermissionRepository;
import com.datalogging.server.repository.GroupRepository;
import com.datalogging.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GroupPermissionController {
    @Autowired
    GroupPermissionRepository groupPermissionRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    GroupRepository groupRepository;

    @PostMapping("/authorized/group-permission/{user-username}")
    public ResponseEntity<?> create(
            @PathVariable(value = "user-username") String userUsername,
            @RequestParam(value = "permitted-user-username") String permittedUserUsername,
            @RequestParam(value = "permissible-group-name") String permissibleDCGroupName,
            @RequestParam(value = "user-token") String userToken) {
        User user = userRepository.findByUsernameAndToken(userUsername, userToken);

        if (user != null) {
            User permittedUser = userRepository.findByUsername(permittedUserUsername);
            Group permissibleDCGroup = groupRepository.findByName(permissibleDCGroupName);

            if (permittedUser != null && permissibleDCGroup != null) {
                GroupPermission groupPermission = new GroupPermission(permittedUser, permissibleDCGroup, user);


                groupPermissionRepository.save(groupPermission);

                return ResponseEntity.status(HttpStatus.OK).body(groupPermission);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/authorized/group-permission/{user-username}/{group-name}")
    public ResponseEntity<?> getAllFromUser(
            @PathVariable(value = "user-username") String userUsername,
            @PathVariable(value = "group-name") String groupName,
            @RequestParam(value = "user-token") String userToken) {
        User user = userRepository.findByUsernameAndToken(userUsername, userToken);

        if (user != null) {
            Group group = groupRepository.findByNameAndUser(groupName, user.getID());

            if (group != null) {
                List<GroupPermission> groupPermissions = groupPermissionRepository.findAllByUserAndPermissibleGroup(user.getID(), group.getID());

                return ResponseEntity.status(HttpStatus.OK).body(groupPermissions);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping("/authorized/group-permission/{user-username}/{permitted-user-username}/{permissible-group-name}")
    public ResponseEntity<?> update(
            @PathVariable(value = "user-username") String userUsername,
            @PathVariable(value = "permitted-user-username") String currentPermittedUserUsername,
            @PathVariable(value = "permissible-group-name") String currentPermissibleDCGroupName,
            @RequestParam(value = "permitted-user-username") String permittedUserUsername,
            @RequestParam(value = "permissible-group-name") String permissibleDCGroupName,
            @RequestParam(value = "user-token") String userToken) {
        User user = userRepository.findByUsernameAndToken(userUsername, userToken);

        if (user != null) {
            User currentPermittedUser = userRepository.findByUsername(currentPermittedUserUsername);
            Group currentPermissibleDCGroup = groupRepository.findByNameAndUser(currentPermissibleDCGroupName, user.getID());
            User permittedUser = userRepository.findByUsername(permittedUserUsername);
            Group permissibleDCGroup = groupRepository.findByName(permissibleDCGroupName);

            if (currentPermittedUser != null && currentPermissibleDCGroup != null && permittedUser != null && permissibleDCGroup != null) {
                GroupPermission currentGroupPermission = groupPermissionRepository.findByUserPermittedUserAndPermissibleGroup(user.getID(), currentPermittedUser.getID(), currentPermissibleDCGroup.getID());
                groupPermissionRepository.delete(currentGroupPermission);

                GroupPermission groupPermission = new GroupPermission(permittedUser, permissibleDCGroup, user);
                groupPermissionRepository.save(groupPermission);

                return ResponseEntity.status(HttpStatus.OK).body(groupPermission);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @DeleteMapping("/authorized/group-permission/{user-username}/{permitted-user-username}/{permissible-group-name}")
    public ResponseEntity<?> delete(
            @PathVariable(value = "user-username") String userUsername,
            @PathVariable(value = "permitted-user-username") String permittedUserUsername,
            @PathVariable(value = "permissible-group-name") String permissibleDCGroupName,
            @RequestParam(value = "user-token") String userToken) {
        User user = userRepository.findByUsernameAndToken(userUsername, userToken);

        if (user != null) {
            User permittedUser = userRepository.findByUsername(permittedUserUsername);
            Group permissibleDCGroup = groupRepository.findByNameAndUser(permissibleDCGroupName, user.getID());

            if (permittedUser != null && permissibleDCGroup != null) {
                GroupPermission currentGroupPermission = groupPermissionRepository.findByUserPermittedUserAndPermissibleGroup(user.getID(), permittedUser.getID(), permissibleDCGroup.getID());
                groupPermissionRepository.delete(currentGroupPermission);

                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
