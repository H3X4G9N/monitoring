package com.datacollection.server.controller;


import com.datacollection.server.model.DC;
import com.datacollection.server.model.DCData;
import com.datacollection.server.model.User;
import com.datacollection.server.repository.DCDataRepository;
import com.datacollection.server.repository.DCRepository;
import com.datacollection.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class DCDataController {
    @Autowired
    DCDataRepository dcDataRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DCRepository dcRepository;

    @GetMapping("/unauthorized/dc-data/{dc-id}")
    public ResponseEntity<?> getAllDCData2(
            @PathVariable(value = "dc-id") Long dcID,
            @RequestParam(value = "dc-group-token") String dcGroupToken
    ) {
        DC dc = dcRepository.findByID(dcID);

        if (dc != null) {
            List<DCData>  dcData = dcDataRepository.findAllByDC(dcID);

            return ResponseEntity.status(HttpStatus.OK).body(dcData);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/authorized/dc-data/{user-username}/{dc-id}")
    public ResponseEntity<?> getAllDCData(
            @PathVariable(value = "user-username") String userUsername,
            @PathVariable(value = "dc-id") Long dcID,
            @RequestParam(value = "user-token") String userToken
    ) {
        User user = userRepository.findByUsernameAndToken(userUsername, userToken);

        if (user != null) {
            DC dc = dcRepository.findByIDAndUser(dcID, user.getID());

            if (dc != null) {
                List<DCData>  dcData = dcDataRepository.findAllByDC(dcID);

                return ResponseEntity.status(HttpStatus.OK).body(dcData);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
