package com.example.monitoring.model;

import com.example.monitoring.HTTP;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.GenericUrl;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DataLogger implements Serializable {
    private Long id;
    private String name;
    private String description;
    private String activationKey;
    private String user;
    private String token;
    private Long group;

    public DataLogger() {
    }

    public DataLogger(String name, String description, String activationKey) {
        this.name = name;
        this.description = description;
        this.activationKey = activationKey;
    }

    public static DataLogger authorize(Long id, String password) {
        String pathURL = "/unauthorized/data-logger/" + id + "/authorize" + "?password=" + password;

        try {
            String response = HTTP.httpRequestFactory.buildGetRequest(new GenericUrl(HTTP.apiURL + pathURL)).execute().parseAsString();

            return new ObjectMapper().readValue(response, DataLogger.class);
        } catch (Exception e) {
            return null;
        }
    }

    public static List<DataLogger> getAllFromGroup(GroupAuthentication groupAuthentication) {
        String pathURL = "/unauthorized/data-logger/" + groupAuthentication.getGroup() + "?group-token=" + groupAuthentication.getToken();

        try {
            String response = HTTP.httpRequestFactory.buildGetRequest(new GenericUrl(HTTP.apiURL + pathURL)).execute().parseAsString();

            return Arrays.asList(new ObjectMapper().readValue(response, DataLogger[].class));
        } catch (Exception e) {
            return null;
        }
    }

    public static DataLogger activate(User user, Group group, DataLogger dataLogger) {
        String pathURL = "/authorized/data-logger/" + user.getUsername() + "/activate" +
                "?group-id=" + group.getID() +
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
            ObjectMapper mapper = new ObjectMapper();
            return Arrays.asList(mapper.readValue(response, DataLogger[].class));
        } catch (Exception e) {
            return null;
        }
    }

    public static List<DataLogger> getAllFromGroup(User user, Group group) {
        String pathURL = "/authorized/data-logger/" + user.getUsername() + "/" + group.getID() +
                "?user-token=" + user.getToken();

        try {
            String response = HTTP.httpRequestFactory.buildGetRequest(new GenericUrl(HTTP.apiURL + pathURL)).execute().parseAsString();
            ObjectMapper mapper = new ObjectMapper();
            return Arrays.asList(mapper.readValue(response, DataLogger[].class));
        } catch (Exception e) {
            return null;
        }
    }

    public static DataLogger update(User user, Long id, Group group, DataLogger dataLogger) {
        String pathURL = "/authorized/data-logger/" + user.getUsername() + "/" + id +
                "?group-id=" + group.getID() +
                "&name=" + dataLogger.getName() +
                "&description=" + dataLogger.getDescription() +
                "&user-token=" + user.getToken();

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public Long getGroup() {
        return group;
    }

    public void setGroup(Long group) {
        this.group = group;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
