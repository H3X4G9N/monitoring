package com.datacollection.client.model;

import com.datacollection.client.HTTP;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.GenericUrl;

import javax.xml.transform.sax.SAXSource;
import java.util.Arrays;
import java.util.List;

public class DCGroup {
    private Long id;
    private String name;
    private String description;
    private Boolean visible;
    @JsonIgnore
    private String password;
    private Boolean permissionRequired;
    private String user;

    public DCGroup() {

    }

    public DCGroup(String name, String description, Boolean visible, String password, Boolean permissionRequired) {
        this.name = name;
        this.description = description;
        this.visible = visible;
        this.password = password;
        this.permissionRequired = permissionRequired;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getPermissionRequired() {
        return permissionRequired;
    }

    public void setPermissionRequired(Boolean permissionRequired) {
        this.permissionRequired = permissionRequired;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public  static List<DCGroup> getAllVisible() {
        String pathURL = "/unauthorized/dc-group/";

        try {
            String response = HTTP.httpRequestFactory.buildGetRequest(new GenericUrl(HTTP.apiURL + pathURL)).execute().parseAsString();

            return  Arrays.asList(new ObjectMapper().readValue(response, DCGroup[].class));
        } catch (Exception e) {
            return null;
        }
    }

    public  static DCGroupAuthentication authorize(String dcGroupName, String password) {
        String pathURL = "/unauthorized/dc-group/" + dcGroupName + "/authorize?password=" + password;

        try {
            String response = HTTP.httpRequestFactory.buildGetRequest(new GenericUrl(HTTP.apiURL + pathURL)).execute().parseAsString();

            return new ObjectMapper().readValue(response, DCGroupAuthentication.class);
        } catch (Exception e) {
            return null;
        }
    }

    public static DCGroup create(User user, DCGroup dcGroup) {
        String pathURL = "/authorized/dc-group/" + user.getUsername() +
                "?name=" + dcGroup.getName() +
                "&description=" + dcGroup.getDescription() +
                "&visible=" + dcGroup.getVisible() +
                "&password=" + dcGroup.getPassword() +
                "&permission-required=" + dcGroup.getPermissionRequired() +
                "&user-token=" + user.getToken();

        try {
            String response = HTTP.httpRequestFactory.buildPostRequest(new GenericUrl(HTTP.apiURL + pathURL), null).execute().parseAsString();
            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(response, DCGroup.class);
        } catch (Exception e) {
            return null;
        }
    }

    public static List<DCGroup> getAllFromUser(User user) {
        String pathURL = "/authorized/dc-group/" + user.getUsername() + "?user-token=" + user.getToken();

        try {
            String response = HTTP.httpRequestFactory.buildGetRequest(new GenericUrl(HTTP.apiURL + pathURL)).execute().parseAsString();
            ObjectMapper mapper = new ObjectMapper();

            List<DCGroup> dcGroups = Arrays.asList(mapper.readValue(response, DCGroup[].class));
            return dcGroups;
        } catch (Exception e) {
            return null;
        }
    }

    public static DCGroup update(User user, String name, DCGroup dcGroup) {
        String pathURL = "/authorized/dc-group/" + user.getUsername() + "/" + name +
                "?name=" + dcGroup.getName() +
                "&description=" + dcGroup.getDescription() +
                "&visible=" + dcGroup.getVisible() +
                "&password=" + dcGroup.getPassword() +
                "&permission-required=" + dcGroup.getPermissionRequired() +
                "&user-token=" + user.getToken();

        try {
            String response = HTTP.httpRequestFactory.buildPutRequest(new GenericUrl(HTTP.apiURL + pathURL), null).execute().parseAsString();

            return new ObjectMapper().readValue(response, DCGroup.class);
        } catch (Exception e) {
            return null;
        }
    }

    public static String delete(User user, String name) {
        String pathURL = "/authorized/dc-group/" + user.getUsername()  + "/" + name + "?user-token=" + user.getToken();

        try {
            HTTP.httpRequestFactory.buildDeleteRequest(new GenericUrl(HTTP.apiURL + pathURL)).execute();
            return "success";
        } catch (Exception e) {
            return null;
        }
    }
}
