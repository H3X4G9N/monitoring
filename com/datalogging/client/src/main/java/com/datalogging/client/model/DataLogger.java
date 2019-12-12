package com.datalogging.client.model;

import com.datalogging.client.HTTP;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.GenericUrl;

import java.util.Arrays;
import java.util.List;

public class DataLogger {
    private Long id;
    private String name;
    private String description;
    private String activationKey;
    private String user;

    private String group;

    public DataLogger() {
    }

    public DataLogger(String name, String description, String activationKey) {
        this.name = name;
        this.description = description;
        this.activationKey = activationKey;
    }

    public static List<DataLogger> getAllFromGroup(String groupName, String groupToken) {
        String pathURL = "/unauthorized/data-logger/" + groupName + "?group-token=" + groupToken;
        System.out.println(pathURL);

        try {
            String response = HTTP.httpRequestFactory.buildGetRequest(new GenericUrl(HTTP.apiURL + pathURL)).execute().parseAsString();

            return Arrays.asList(new ObjectMapper().readValue(response, DataLogger[].class));
        } catch (Exception e) {
            return null;
        }
    }

    public static DataLogger activate(User user, String groupName, DataLogger dataLogger) {
        String pathURL = "/authorized/data-logger/" + user.getUsername() + "/activate" +
                "?group-name=" + groupName +
                "&name=" + dataLogger.getName() +
                "&description=" + dataLogger.getDescription() +
                "&activation-key=" + dataLogger.getActivationKey() +
                "&user-token=" + user.getToken();

        try {
            String response = HTTP.httpRequestFactory.buildPutRequest(new GenericUrl(HTTP.apiURL + pathURL), null).execute().parseAsString();
            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(response, DataLogger.class);
        } catch (Exception e) {
            return null;
        }
    }

    public static List<DataLogger> getAllFromUser(User user) {
        String pathURL = "/authorized/data-logger/" + user.getUsername() +
                "?user-token=" + user.getToken();

        try {
            String response = HTTP.httpRequestFactory.buildGetRequest(new GenericUrl(HTTP.apiURL + pathURL)).execute().parseAsString();
            System.out.println(response);
            ObjectMapper mapper = new ObjectMapper();
            return Arrays.asList(mapper.readValue(response, DataLogger[].class));
        } catch (Exception e) {
            return null;
        }
    }

    public static List<DataLogger> getAllFromGroup(User user, String groupName) {
        String pathURL = "/authorized/data-logger/" + user.getUsername() + "/" + groupName +
                "?user-token=" + user.getToken();

        try {
            String response = HTTP.httpRequestFactory.buildGetRequest(new GenericUrl(HTTP.apiURL + pathURL)).execute().parseAsString();
            ObjectMapper mapper = new ObjectMapper();
            return Arrays.asList(mapper.readValue(response, DataLogger[].class));
        } catch (Exception e) {
            return null;
        }
    }

    public static DataLogger update(User user, Long id, String groupName, DataLogger dataLogger) {
        String pathURL = "/authorized/data-logger/" + user.getUsername() + "/" + id +
                "?group-name=" + groupName +
                "&name=" + dataLogger.getName() +
                "&description=" + dataLogger.getDescription() +
                "&user-token=" + user.getToken();

        System.out.println(pathURL);
        try {
            String response = HTTP.httpRequestFactory.buildPutRequest(new GenericUrl(HTTP.apiURL + pathURL), null).execute().parseAsString();

            return new ObjectMapper().readValue(response, DataLogger.class);
        } catch (Exception e) {
            return null;
        }
    }

    public static String deactivate(User user, Long id) {
        String pathURL = "/authorized/data-logger/" + user.getUsername() + "/" + id + "/deactivate" +
                "?user-token=" + user.getToken();

        try {
            HTTP.httpRequestFactory.buildPutRequest(new GenericUrl(HTTP.apiURL + pathURL), null).execute();
            return "success";
        } catch (Exception e) {
            return null;
        }
    }

    public Long getID() {
        return id;
    }

    public void setID(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
