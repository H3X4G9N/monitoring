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

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DataController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    DataLoggerRepository dataLoggerRepository;
    @Autowired
    DataRepository dataRepository;

    @GetMapping("/authorized/data/{user-username}/latest")
    public ResponseEntity<?> getLatestFromUser(
            @PathVariable(value = "user-username") String userUsername,
            @RequestParam(value = "user-token") String userToken
    ) {
        User user = userRepository.findByUsernameAndToken(userUsername, userToken);

        if (user != null) {
            List<DataLogger> dataLoggerList = dataLoggerRepository.findAllByUser(user.getID());

            if (dataLoggerList != null) {
                List<Data> dataList = new ArrayList<>();

                for (DataLogger dataLogger : dataLoggerList) {
                    Data data = dataRepository.findLatestByDataLogger(dataLogger.getID());

                    if (data != null) {
                        dataList.add(data);
                    }
                }

                return ResponseEntity.status(HttpStatus.OK).body(dataList);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/authorized/data/{user-username}/{data-logger-id}/latest")
    public ResponseEntity<?> getLatestFromDataLogger(
            @PathVariable(value = "user-username") String userUsername,
            @PathVariable(value = "data-logger-id") Long dataLoggerID,
            @RequestParam(value = "user-token") String userToken
    ) {
        User user = userRepository.findByUsernameAndToken(userUsername, userToken);

        if (user != null) {
            DataLogger dataLogger = dataLoggerRepository.findByIDAndUser(dataLoggerID, user.getID());

            if (dataLogger != null) {
                Data data = dataRepository.findLatestByDataLogger(dataLoggerID);

                return ResponseEntity.status(HttpStatus.OK).body(data);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/authorized/data/{user-username}/{data-logger-id}")
    public ResponseEntity<?> getAllFromDataLogger(
            @PathVariable(value = "user-username") String userUsername,
            @PathVariable(value = "data-logger-id") Long dataLoggerID,
            @RequestParam(value = "from") String from,
            @RequestParam(value = "to") String to,
            @RequestParam(value = "user-token") String userToken
    ) {
        User user = userRepository.findByUsernameAndToken(userUsername, userToken);

        if (user != null) {
            DataLogger dataLogger = dataLoggerRepository.findByIDAndUser(dataLoggerID, user.getID());

            if (dataLogger != null) {
                List<Data> dataList = dataRepository.findAllByDataLogger(dataLoggerID, from, to);
                List<Data> newDataList = new ArrayList<>();
                Integer step = dataList.size() / (Math.min(dataList.size(), 144) + 1);

                for (int a = 0; a < dataList.size(); a += step) {
                    newDataList.add(dataList.get(a));
                }

                return ResponseEntity.status(HttpStatus.OK).body(newDataList);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/authorized/data/{data-logger-id}")
    public ResponseEntity<?> create(
            @PathVariable(value = "data-logger-id") Long dataLoggerID,
            @RequestParam(value = "carbon-dioxide") Double carbonDioxide,
            @RequestParam(value = "humidity") Double humidity,
            @RequestParam(value = "temperature") Double temperature,
            @RequestParam(value = "data-logger-token") String dataLoggerToken
    ) {
        DataLogger dataLogger = dataLoggerRepository.findByIDAndToken(dataLoggerID, dataLoggerToken);

        if (dataLogger != null) {
            Data data = new Data(carbonDioxide, humidity, temperature, dataLogger);
            dataRepository.save(data);

            return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/unauthorized/data/{data-logger-id}")
    public ResponseEntity<?> getAllFromDataLogger(
            @PathVariable(value = "data-logger-id") Long dataLoggerID,
            @RequestParam(value = "from") String from,
            @RequestParam(value = "to") String to,
            @RequestParam(value = "group-token") String groupToken
    ) {
        DataLogger dataLogger = dataLoggerRepository.findByID(dataLoggerID);

        if (dataLogger != null) {
            List<Data> dataList = dataRepository.findAllByDataLogger(dataLoggerID, from, to);
            List<Data> newDataList = new ArrayList<>();
            int step = dataList.size() / (Math.min(dataList.size(), 144) + 1);

            for (int a = 0; a < dataList.size(); a += step) {
                newDataList.add(dataList.get(a));
            }

            return ResponseEntity.status(HttpStatus.OK).body(newDataList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/unauthorized/data/{data-logger-id}/latest")
    public ResponseEntity<?> getLatestFromDataLogger(
            @PathVariable(value = "data-logger-id") Long dataLoggerID,
            @RequestParam(value = "group-token") String groupToken
    ) {
        DataLogger dataLogger = dataLoggerRepository.findByID(dataLoggerID);

        if (dataLogger != null) {
            Data data = dataRepository.findLatestByDataLogger(dataLoggerID);

            return ResponseEntity.status(HttpStatus.OK).body(data);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
