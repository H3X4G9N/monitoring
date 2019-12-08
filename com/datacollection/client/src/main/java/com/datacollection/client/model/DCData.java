package com.datacollection.client.model;

import com.datacollection.client.model.DC;
import com.datacollection.client.HTTP;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.GenericUrl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DCData {
    private Long id;
    private Date timestamp;
    private Double carbonDioxide;
    private Double humidity;
    private Double temperature;
    private Long dc;

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

    public Long getDC() {
        return dc;
    }

    public void setDC(Long dc) {
        this.dc = dc;
    }

    public static List<DCData> getAllFromDC(Long dcID, String dcGroupToken) {
        String pathURL = "/unauthorized/dc-data/" + dcID + "?dc-group-token=" + dcGroupToken;

        try {
            String response = HTTP.httpRequestFactory.buildGetRequest(new GenericUrl(HTTP.apiURL + pathURL)).execute().parseAsString();

            return Arrays.asList(new ObjectMapper().readValue(response, DCData[].class));
        } catch (Exception e) {
            return null;
        }
    }

    public static List<DCData> getAllFromDC(User user, Long dcID) {
        String pathURL = "/authorized/dc-data/" + user.getUsername() + "/" + dcID +
                "?user-token=" + user.getToken();

        try {
            String response = HTTP.httpRequestFactory.buildGetRequest(new GenericUrl(HTTP.apiURL + pathURL)).execute().parseAsString();

            return Arrays.asList(new ObjectMapper().readValue(response, DCData[].class));
        } catch (Exception e) {
            return null;
        }
    }
}