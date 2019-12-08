package com.datacollection.server.controller;

import com.datacollection.server.model.DC;
import com.datacollection.server.model.DCGroup;
import com.datacollection.server.model.DCGroupAuthentication;
import com.datacollection.server.model.User;
import com.datacollection.server.repository.DCGroupAuthenticationRepository;
import com.datacollection.server.repository.DCGroupRepository;
import com.datacollection.server.repository.DCRepository;
import com.datacollection.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DCController {
    @Autowired
    DCRepository dcRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DCGroupRepository dcGroupRepository;
    @Autowired
    DCGroupAuthenticationRepository dcGroupAuthenticationRepository;


    @GetMapping("/unauthorized/dc/{dc-group-name}")
    public ResponseEntity<?> getAllFromDCGroup1(
            @PathVariable(value = "dc-group-name") String dcGroupName,
            @RequestParam(value = "dc-group-token") String dcGroupToken) {
        DCGroup dcGroup = dcGroupRepository.findVisibleByName(dcGroupName);


        if (dcGroup != null) {
            DCGroupAuthentication dcGroupAuthentication = dcGroupAuthenticationRepository.findByTokenAndGroup(dcGroupToken, dcGroup.getID());

            if (dcGroupAuthentication != null) {
                List<DC> dcs = dcRepository.findAllByDCGroup(dcGroup.getID());
                return ResponseEntity.status(HttpStatus.OK).body(dcs);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }


        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    @PutMapping("/authorized/dc/{user-username}/activate")
    public ResponseEntity<?> activate(
            @PathVariable(value = "user-username") String userUsername,
            @RequestParam(value = "dc-group-name") String dcGroupName,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "activation-key") String activationKey,
            @RequestParam(value = "user-token") String userToken) {
        User user = userRepository.findByUsernameAndToken(userUsername, userToken);
        DCGroup dcGroup = dcGroupRepository.findByNameAndUser(dcGroupName, user.getID());

        if (user != null && dcGroup != null) {
            DC dc = dcRepository.findUnactivatedByActivationKey(activationKey);

            if (dc != null) {
                dc.setName(name);
                dc.setDescription(description);
                dc.setActivated(true);
                dc.setUser(user);
                dc.setDCGroup(dcGroup);
                dcRepository.save(dc);

                return ResponseEntity.status(HttpStatus.OK).body(dc);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/authorized/dc/{user-username}")
    public ResponseEntity<?> getAllFromUser(
            @PathVariable(value = "user-username") String userUsername,
            @RequestParam(value = "user-token") String userToken) {
        User user = userRepository.findByUsernameAndToken(userUsername, userToken);

        if (user != null) {
            List<DC> dcs = dcRepository.findAllByUser(user.getID());

            return ResponseEntity.status(HttpStatus.OK).body(dcs);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/authorized/dc/{user-username}/{dc-group-name}")
    public ResponseEntity<?> getAllFromDCGroup(
            @PathVariable(value = "user-username") String userUsername,
            @PathVariable(value = "dc-group-name") String dcGroupName,
            @RequestParam(value = "user-token") String userToken) {
        User user = userRepository.findByUsernameAndToken(userUsername, userToken);
        DCGroup dcGroup = dcGroupRepository.findByNameAndUser(dcGroupName, user.getID());

        if (user != null && dcGroup != null) {
            if (dcGroup != null) {
                List<DC> dcs = dcRepository.findAllByDCGroup(dcGroup.getID());
                System.out.println(dcs);

                return ResponseEntity.status(HttpStatus.OK).body(dcs);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping("/authorized/dc/{user-username}/{id}")
    public ResponseEntity<?> update(
            @PathVariable(value = "user-username") String userUsername,
            @PathVariable(value = "id") Long id,
            @RequestParam(value = "dc-group-name") String dcGroupName,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "user-token") String userToken) {
        User user = userRepository.findByUsernameAndToken(userUsername, userToken);
        DCGroup dcGroup = dcGroupRepository.findByNameAndUser(dcGroupName, user.getID());

        if (user != null && dcGroup != null) {
            DC dc = dcRepository.findByIDAndUser(id, user.getID());

            if (dc != null) {
                dc.setName(name);
                dc.setDescription(description);
                dc.setDCGroup(dcGroup);
                dcRepository.save(dc);

                return ResponseEntity.status(HttpStatus.OK).body(dc);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping("/authorized/dc/{user-username}/{id}/deactivate")
    public ResponseEntity<?> deactivate(
            @PathVariable(value = "user-username") String userUsername,
            @PathVariable(value = "id") Long id,
            @RequestParam(value = "user-token") String userToken) {
        User user = userRepository.findByUsernameAndToken(userUsername, userToken);

        if (user != null) {
            DC dc = dcRepository.findByIDAndUser(id, user.getID());

            if (dc != null) {
                dc.setActivated(false);
                dc.setUser(null);
                dc.setDCGroup(null);
                dcRepository.save(dc);

                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
