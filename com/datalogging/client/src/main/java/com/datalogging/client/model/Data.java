package com.datalogging.client.model;

import com.datalogging.client.HTTP;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.GenericUrl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Data {
    private Long id;
    private Date timestamp;
    private Double carbonDioxide;
    private Double humidity;
    private Double temperature;
    private Long dataLogger;

    public static List<Data> getAllFromDataLogger(Long dataLoggerID, String groupToken) {
        String pathURL = "/unauthorized/data/" + dataLoggerID + "?group-token=" + groupToken;

        try {
            String response = HTTP.httpRequestFactory.buildGetRequest(new GenericUrl(HTTP.apiURL + pathURL)).execute().parseAsString();

            return Arrays.asList(new ObjectMapper().readValue(response, Data[].class));
        } catch (Exception e) {
            return null;
        }
    }

    public static List<Data> getAllFromDataLogger(User user, Long dataLoggerID) {
        String pathURL = "/authorized/data/" + user.getUsername() + "/" + dataLoggerID +
                "?user-token=" + user.getToken();

        try {
            String response = HTTP.httpRequestFactory.buildGetRequest(new GenericUrl(HTTP.apiURL + pathURL)).execute().parseAsString();

            return Arrays.asList(new ObjectMapper().readValue(response, Data[].class));
        } catch (Exception e) {
            return null;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Double getCarbonDioxide() {
        return carbonDioxide;
    }

    public void setCarbonDioxide(Double carbonDioxide) {
        this.carbonDioxide = carbonDioxide;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Long getDataLogger() {
        return dataLogger;
    }

    public void setDataLogger(Long dataLogger) {
        this.dataLogger = dataLogger;
    }
}