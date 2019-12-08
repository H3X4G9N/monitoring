package com.datacollection.server.controller;

import com.datacollection.server.model.DCGroup;
import com.datacollection.server.model.Permission;
import com.datacollection.server.model.User;
import com.datacollection.server.repository.DCGroupRepository;
import com.datacollection.server.repository.PermissionRepository;
import com.datacollection.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HttpsURLConnection;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PermissionController {
    @Autowired
    PermissionRepository permissionRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DCGroupRepository dcGroupRepository;

    @PostMapping("/authorized/permission/{user-username}")
    public ResponseEntity<?> create(
            @PathVariable(value = "user-username") String userUsername,
            @RequestParam(value = "permitted-user-username") String permittedUserUsername,
            @RequestParam(value = "permissible-dc-group-name") String permissibleDCGroupName,
            @RequestParam(value = "user-token") String userToken) {
        User user = userRepository.findByUsernameAndToken(userUsername, userToken);

        if (user != null) {
            User permittedUser = userRepository.findByUsername(permittedUserUsername);
            DCGroup permissibleDCGroup = dcGroupRepository.findByName(permissibleDCGroupName);

            if (permittedUser != null && permissibleDCGroup != null) {
                Permission permission = new Permission(permittedUser, permissibleDCGroup, user);


                permissionRepository.save(permission);

                return ResponseEntity.status(HttpStatus.OK).body(permission);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/authorized/permission/{user-username}/{dc-group-name}")
    public ResponseEntity<?> getAllFromUser(
            @PathVariable(value = "user-username") String userUsername,
            @PathVariable(value = "dc-group-name") String dcGroupName,
            @RequestParam(value = "user-token") String userToken) {
        User user = userRepository.findByUsernameAndToken(userUsername, userToken);

        if (user != null) {
            DCGroup dcGroup = dcGroupRepository.findByNameAndUser(dcGroupName, user.getID());

            if (dcGroup != null) {
                List<Permission> permissions = permissionRepository.findAllByUserAndPermissibleDCGroup(user.getID(), dcGroup.getID());

                return ResponseEntity.status(HttpStatus.OK).body(permissions);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping("/authorized/permission/{user-username}/{permitted-user-username}/{permissible-dc-group-name}")
    public ResponseEntity<?> update(
            @PathVariable(value = "user-username") String userUsername,
            @PathVariable(value = "permitted-user-username") String currentPermittedUserUsername,
            @PathVariable(value = "permissible-dc-group-name") String currentPermissibleDCGroupName,
            @RequestParam(value = "permitted-user-username") String permittedUserUsername,
            @RequestParam(value = "permissible-dc-group-name") String permissibleDCGroupName,
            @RequestParam(value = "user-token") String userToken) {
        User user = userRepository.findByUsernameAndToken(userUsername, userToken);

        if (user != null) {
            User currentPermittedUser = userRepository.findByUsername(currentPermittedUserUsername);
            DCGroup currentPermissibleDCGroup = dcGroupRepository.findByNameAndUser(currentPermissibleDCGroupName, user.getID());
            User permittedUser = userRepository.findByUsername(permittedUserUsername);
            DCGroup permissibleDCGroup = dcGroupRepository.findByName(permissibleDCGroupName);

            if (currentPermittedUser != null &&  currentPermissibleDCGroup != null && permittedUser != null && permissibleDCGroup != null) {
                Permission currentPermission = permissionRepository.findByUserPermittedUserAndPermissibleDCGroup(user.getID(), currentPermittedUser.getID(), currentPermissibleDCGroup.getID());
                permissionRepository.delete(currentPermission);

                Permission permission = new Permission(permittedUser, permissibleDCGroup, user);
                permissionRepository.save(permission);

                return ResponseEntity.status(HttpStatus.OK).body(permission);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @DeleteMapping("/authorized/permission/{user-username}/{permitted-user-username}/{permissible-dc-group-name}")
    public ResponseEntity<?> delete(
            @PathVariable(value = "user-username") String userUsername,
            @PathVariable(value = "permitted-user-username") String permittedUserUsername,
            @PathVariable(value = "permissible-dc-group-name") String permissibleDCGroupName,
            @RequestParam(value = "user-token") String userToken) {
        User user = userRepository.findByUsernameAndToken(userUsername, userToken);

        if (user != null) {
            User permittedUser = userRepository.findByUsername(permittedUserUsername);
            DCGroup permissibleDCGroup = dcGroupRepository.findByNameAndUser(permissibleDCGroupName, user.getID());

            if (permittedUser != null && permissibleDCGroup != null) {
                Permission currentPermission = permissionRepository.findByUserPermittedUserAndPermissibleDCGroup(user.getID(), permittedUser.getID(), permissibleDCGroup.getID());
                permissionRepository.delete(currentPermission);

                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
