package com.datalogging.server.controller;

import com.datalogging.server.model.DataLogger;
import com.datalogging.server.model.Group;
import com.datalogging.server.model.GroupAuthentication;
import com.datalogging.server.model.User;
import com.datalogging.server.repository.DataLoggerRepository;
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
public class DataLoggerController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    GroupAuthenticationRepository groupAuthenticationRepository;
    @Autowired
    DataLoggerRepository dataLoggerRepository;

    @GetMapping("/authorized/data-logger/{user-username}")
    public ResponseEntity<?> getAllFromUser(
            @PathVariable(value = "user-username") String userUsername,
            @RequestParam(value = "user-token") String userToken) {
        User user = userRepository.findByUsernameAndToken(userUsername, userToken);

        if (user != null) {
            List<DataLogger> dataLoggers = dataLoggerRepository.findAllByUser(user.getID());

            for (DataLogger dataLogger : dataLoggers) {
                dataLogger.setToken("");
            }

            return ResponseEntity.status(HttpStatus.OK).body(dataLoggers);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/authorized/data-logger/{user-username}/{group-id}")
    public ResponseEntity<?> getAllFromGroup(
            @PathVariable(value = "user-username") String userUsername,
            @PathVariable(value = "group-id") Long groupID,
            @RequestParam(value = "user-token") String userToken) {
        User user = userRepository.findByUsernameAndToken(userUsername, userToken);
        Group group = groupRepository.findByIDAndUser(groupID, user.getID());

        if (user != null && group != null) {
            if (group != null) {
                List<DataLogger> dataLoggers = dataLoggerRepository.findAllByGroup(group.getID());

                for (DataLogger dataLogger : dataLoggers) {
                    dataLogger.setToken("");
                }

                return ResponseEntity.status(HttpStatus.OK).body(dataLoggers);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping("/authorized/data-logger/{user-username}/activate")
    public ResponseEntity<?> activate(
            @PathVariable(value = "user-username") String userUsername,
            @RequestParam(value = "group-id") Long groupID,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "activation-key") String activationKey,
            @RequestParam(value = "user-token") String userToken) {
        User user = userRepository.findByUsernameAndToken(userUsername, userToken);
        Group group = groupRepository.findByIDAndUser(groupID, user.getID());

        if (user != null && group != null) {
            DataLogger dataLogger = dataLoggerRepository.findUnactivatedByActivationKey(activationKey);

            if (dataLogger != null) {
                dataLogger.setName(name);
                dataLogger.setDescription(description);
                dataLogger.setActivated(true);
                dataLogger.setUser(user);
                dataLogger.setGroup(group);
                dataLoggerRepository.save(dataLogger);
                dataLogger.setToken("");

                return ResponseEntity.status(HttpStatus.OK).body(dataLogger);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping("/authorized/data-logger/{user-username}/{id}")
    public ResponseEntity<?> update(
            @PathVariable(value = "user-username") String userUsername,
            @PathVariable(value = "id") Long id,
            @RequestParam(value = "group-id") Long groupID,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "user-token") String userToken) {
        User user = userRepository.findByUsernameAndToken(userUsername, userToken);
        Group group = groupRepository.findByIDAndUser(groupID, user.getID());

        if (user != null && group != null) {
            DataLogger dataLogger = dataLoggerRepository.findByIDAndUser(id, user.getID());

            if (dataLogger != null) {
                dataLogger.setName(name);
                dataLogger.setDescription(description);
                dataLogger.setGroup(group);
                dataLoggerRepository.save(dataLogger);
                dataLogger.setToken("");

                return ResponseEntity.status(HttpStatus.OK).body(dataLogger);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping("/authorized/data-logger/{user-username}/{id}/deactivate")
    public ResponseEntity<?> deactivate(
            @PathVariable(value = "user-username") String userUsername,
            @PathVariable(value = "id") Long id,
            @RequestParam(value = "user-token") String userToken) {
        User user = userRepository.findByUsernameAndToken(userUsername, userToken);

        if (user != null) {
            DataLogger dataLogger = dataLoggerRepository.findByIDAndUser(id, user.getID());

            if (dataLogger != null) {
                dataLogger.setActivated(false);
                dataLogger.setUser(null);
                dataLogger.setGroup(null);
                dataLoggerRepository.save(dataLogger);

                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/unauthorized/data-logger/{group-id}")
    public ResponseEntity<?> getAllFromGroup(
            @PathVariable(value = "group-id") Long groupID,
            @RequestParam(value = "group-token") String groupToken) {
        Group group = groupRepository.findVisibleByID(groupID);

        if (group != null) {
            GroupAuthentication groupAuthentication = groupAuthenticationRepository.findByTokenAndGroup(groupToken, group.getID());

            if (groupAuthentication != null) {
                List<DataLogger> dataLoggers = dataLoggerRepository.findAllByGroup(group.getID());

                for (DataLogger dataLogger : dataLoggers) {
                    dataLogger.setToken("");
                }

                return ResponseEntity.status(HttpStatus.OK).body(dataLoggers);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/unauthorized/data-logger/{id}/authorize")
    public ResponseEntity<?> authorize(
            @PathVariable(value = "id") Long id,
            @RequestParam(value = "password") String password) {
        DataLogger dataLogger = dataLoggerRepository.findByIDAndPassword(id, password);

        if (dataLogger != null) {
            dataLogger.setToken(UUID.randomUUID().toString());
            dataLoggerRepository.save(dataLogger);

            return ResponseEntity.status(HttpStatus.OK).body(dataLogger);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
