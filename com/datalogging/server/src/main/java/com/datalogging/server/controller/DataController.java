package com.datalogging.server.controller;

import com.datalogging.server.model.Data;
import com.datalogging.server.model.DataLogger;
import com.datalogging.server.model.User;
import com.datalogging.server.repository.DataLoggerRepository;
import com.datalogging.server.repository.DataRepository;
import com.datalogging.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DataController {
    @Autowired
    DataRepository dataRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DataLoggerRepository dataLoggerRepository;

    @GetMapping("/unauthorized/data/{data-logger-id}")
    public ResponseEntity<?> getAllFromDataLoggerAndGroupToken(
            @PathVariable(value = "data-logger-id") Long dataLoggerID,
            @RequestParam(value = "data-logger-group-token") String groupToken
    ) {
        DataLogger dataLogger = dataLoggerRepository.findByID(dataLoggerID);

        if (dataLogger != null) {
            List<Data> data = dataRepository.findAllByDataLogger(dataLoggerID);

            return ResponseEntity.status(HttpStatus.OK).body(data);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/authorized/data/{user-username}/{data-logger-id}")
    public ResponseEntity<?> getAllFromDataLogger(
            @PathVariable(value = "user-username") String userUsername,
            @PathVariable(value = "data-logger-id") Long dataLoggerID,
            @RequestParam(value = "user-token") String userToken
    ) {
        User user = userRepository.findByUsernameAndToken(userUsername, userToken);

        if (user != null) {
            DataLogger dataLogger = dataLoggerRepository.findByIDAndUser(dataLoggerID, user.getID());

            if (dataLogger != null) {
                List<Data> data = dataRepository.findAllByDataLogger(dataLoggerID);

                return ResponseEntity.status(HttpStatus.OK).body(data);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
