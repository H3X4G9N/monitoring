package com.datacollection.server.controller;


import com.datacollection.server.model.DCData;
import com.datacollection.server.repository.DCDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class DCDataController {
    @Autowired
    DCDataRepository dataCollectorDataRepository;

    @GetMapping("/data-collector-data")
    public List<DCData> getAllDataCollectorData() {
        return dataCollectorDataRepository.findAll();
    }

    @PostMapping("/data-collector-data")
    public DCData createDataCollectorData(@Valid @RequestBody DCData dataCollectorData) {
        dataCollectorData.setTimestamp(new Date(System.currentTimeMillis()));

        return dataCollectorDataRepository.save(dataCollectorData);
    }


}
