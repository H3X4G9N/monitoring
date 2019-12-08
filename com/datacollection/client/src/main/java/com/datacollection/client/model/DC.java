package com.datacollection.client.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.GenericUrl;
import com.datacollection.client.HTTP;

import java.util.Arrays;
import java.util.List;

public class DC {
    private Long id;
    private String name;
    private String description;
    private String activationKey;
    private String user;

    @JsonProperty(value = "dcGroup")
    private String dcGroup;

    public DC() {}

    public DC(String name, String description, String activationKey) {
        this.name = name;
        this.description = description;
        this.activationKey = activationKey;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    @JsonProperty(value = "dcGroup")
    public String getDCGroup() {
        return dcGroup;
    }

    @JsonProperty(value = "dcGroup")
    public void setDCGroup(String dcGroup) {
        this.dcGroup = dcGroup;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public static List<DC> getAllFromDCGroup(String dcGroupName, String dcGroupToken) {
        String pathURL = "/unauthorized/dc/" + dcGroupName + "?dc-group-token=" + dcGroupToken;
        System.out.println(pathURL);

        try {
            String response = HTTP.httpRequestFactory.buildGetRequest(new GenericUrl(HTTP.apiURL + pathURL)).execute().parseAsString();

            return Arrays.asList(new ObjectMapper().readValue(response, DC[].class));
        } catch (Exception e) {
            return null;
        }
    }

    public static DC activate(User user, String dcGroupName, DC dc) {
        String pathURL = "/authorized/dc/" + user.getUsername() + "/activate" +
                "?dc-group-name=" + dcGroupName +
                "&name=" + dc.getName() +
                "&description=" + dc.getDescription() +
                "&activation-key=" + dc.getActivationKey() +
                "&user-token=" + user.getToken();

        try {
            String response = HTTP.httpRequestFactory.buildPutRequest(new GenericUrl(HTTP.apiURL + pathURL), null).execute().parseAsString();
            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(response, DC.class);
        } catch (Exception e) {
            return null;
        }
    }

    public static List<DC> getAllFromUser(User user) {
        String pathURL = "/authorized/dc/" + user.getUsername()  +
                "?user-token=" + user.getToken();

        try {
            String response = HTTP.httpRequestFactory.buildGetRequest(new GenericUrl(HTTP.apiURL + pathURL)).execute().parseAsString();
            System.out.println(response);
            ObjectMapper mapper = new ObjectMapper();
            return Arrays.asList(mapper.readValue(response, DC[].class));
        } catch (Exception e) {
            return null;
        }
    }

    public static List<DC> getAllFromDCGroup(User user, String dcGroupName) {
        String pathURL = "/authorized/dc/" + user.getUsername() + "/" + dcGroupName +
                "?user-token=" + user.getToken();

        try {
            String response = HTTP.httpRequestFactory.buildGetRequest(new GenericUrl(HTTP.apiURL + pathURL)).execute().parseAsString();
            ObjectMapper mapper = new ObjectMapper();
            return Arrays.asList(mapper.readValue(response, DC[].class));
        } catch (Exception e) {
            return null;
        }
    }

    public static String update(User user, Long id, DC dc) {
        String pathURL = "/authorized/dc/" + user.getUsername() + "/" + dc.getId() +
                "&name=" + dc.getName() +
                "&description" + dc.getDescription() +
                "?user-token=" + user.getToken();
        try {
            HTTP.httpRequestFactory.buildPutRequest(new GenericUrl(HTTP.apiURL + pathURL), null).execute();
            return "success";
        } catch (Exception e) {
            return null;
        }
    }

    public static String deactivate(User user, Long id) {
        String pathURL = "/authorized/dc/" + user.getUsername() + "/" + id + "/deactivate" +
                "?user-token=" + user.getToken();

        try {
            HTTP.httpRequestFactory.buildPutRequest(new GenericUrl(HTTP.apiURL + pathURL), null).execute();
            return "success";
        } catch (Exception e) {
            return null;
        }
    }
}