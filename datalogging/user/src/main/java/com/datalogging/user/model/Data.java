package com.datalogging.user.model;

import com.datalogging.user.HTTP;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.GenericUrl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Data {
    private Long id;
    private Date timestamp;
    private Double carbonDioxide;
    private Double humidity;
    private Double temperature;
    private Long dataLogger;

    public Data() {

    }

    public Data(Double carbonDioxide, Double humidity, Double temperature, Long dataLogger) {
        this.carbonDioxide = carbonDioxide;
        this.humidity = humidity;
        this.temperature = temperature;
        this.dataLogger = dataLogger;
    }

    public static List<Data> getLatestFromUser(User user) {
        String pathURL = "/authorized/data/" + user.getUsername() + "/" + "latest" +
                "?user-token=" + user.getToken();

        try {
            String response = HTTP.httpRequestFactory.buildGetRequest(new GenericUrl(HTTP.apiURL + pathURL)).execute().parseAsString();
            System.out.println(response);

            return Arrays.asList(HTTP.objectMapper.readValue(response, Data[].class));
        } catch (Exception e) {
            return null;
        }
    }

    public static Data getLatestFromDataLogger(User user, DataLogger dataLogger) {
        String pathURL = "/authorized/data/" + user.getUsername() + "/" + dataLogger.getID() + "/" + "latest" +
                "?user-token=" + user.getToken();

        try {
            String response = HTTP.httpRequestFactory.buildGetRequest(new GenericUrl(HTTP.apiURL + pathURL)).execute().parseAsString();
            System.out.println(response);

            return HTTP.objectMapper.readValue(response, Data.class);
        } catch (Exception e) {
            return null;
        }
    }

    public static List<Data> getAllFromDataLogger(User user, Long dataLoggerID, LocalDateTime from, LocalDateTime to) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String pathURL = "/authorized/data/" + user.getUsername() + "/" + dataLoggerID +
                "?from=" + from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) +
                "&to=" + to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) +
                "&user-token=" + user.getToken();

        try {
            String response = HTTP.httpRequestFactory.buildGetRequest(new GenericUrl(HTTP.apiURL + pathURL)).execute().parseAsString();

            return Arrays.asList(HTTP.objectMapper.readValue(response, Data[].class));
        } catch (Exception e) {
            return null;
        }
    }

    public static String create(DataLogger dataLogger, Data data) {
        String pathURL = "/authorized/data/" + dataLogger.getID() +
                "?carbon-dioxide=" + data.getCarbonDioxide() +
                "&humidity=" + data.getHumidity() +
                "&temperature=" + data.getTemperature() +
                "&data-logger-token=" + dataLogger.getToken();

        try {
            String response = HTTP.httpRequestFactory.buildPostRequest(new GenericUrl(HTTP.apiURL + pathURL), null).execute().parseAsString();

            return "success";
        } catch (Exception e) {
            return null;
        }
    }

    public static Data getLatestFromDataLogger(DataLogger dataLogger, GroupAuthentication groupAuthentication) {
        String pathURL = "/unauthorized/data/" + dataLogger.getID() + "/" + "latest" +
                "?group-token=" + groupAuthentication.getToken();

        try {
            String response = HTTP.httpRequestFactory.buildGetRequest(new GenericUrl(HTTP.apiURL + pathURL)).execute().parseAsString();
            System.out.println(response);


            return HTTP.objectMapper.readValue(response, Data.class);
        } catch (Exception e) {
            return null;
        }
    }

    public static List<Data> getAllFromDataLogger(Long dataLoggerID, GroupAuthentication groupAuthentication, LocalDateTime from, LocalDateTime to) {
        String pathURL = "/unauthorized/data/" + dataLoggerID +
                "?from=" + from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) +
                "&to=" + to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) +
                "&group-token=" + groupAuthentication.getToken();

        try {
            String response = HTTP.httpRequestFactory.buildGetRequest(new GenericUrl(HTTP.apiURL + pathURL)).execute().parseAsString();

            return Arrays.asList(HTTP.objectMapper.readValue(response, Data[].class));
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