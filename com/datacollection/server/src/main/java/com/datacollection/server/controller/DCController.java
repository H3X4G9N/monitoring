package com.datacollection.server.controller;

import com.datacollection.server.model.DC;
import com.datacollection.server.model.DCGroup;
import com.datacollection.server.model.User;
import com.datacollection.server.repository.DCGroupRepository;
import com.datacollection.server.repository.DCRepository;
import com.datacollection.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DCController {
    @Autowired
    DCRepository dcRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DCGroupRepository dcGroupRepository;

    @PutMapping("/dc/{user-id}/activate")
    public ResponseEntity<?> activate(
            @PathVariable(value = "user-id") Long userID,
            @RequestParam(value = "user-token") String userToken,
            @RequestParam(value = "dc-activation-key") String dcActivationKey) {
        User user = userRepository.findByIDAndToken(userID, userToken);

        if (user != null) {
            DC dc = dcRepository.findUnactivatedByActivationKey(dcActivationKey);

            if (dc != null) {
                dc.setActivated(true);

                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/dc/{user-id}")
    public ResponseEntity<?> getAllDCsByUser(
            @PathVariable(value = "user-id") Long userID,
            @RequestParam(value = "user-token") String userToken) {
        User user = userRepository.findByIDAndToken(userID, userToken);

        if (user != null) {
            return ResponseEntity.status(HttpStatus.OK).body(dcRepository.findAllByUser(userID));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/dc")
    public ResponseEntity<?> getAllDCsByGroupToken(
            @RequestParam(value = "group-token") String dcGroupToken) {
        DCGroup dcGroup = dcGroupRepository.findByDCGroupToken(dcGroupToken);

        if (dcGroup != null) {
            return ResponseEntity.status(HttpStatus.OK).body(dcRepository.findAllByGroup(dcGroup.getId()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
