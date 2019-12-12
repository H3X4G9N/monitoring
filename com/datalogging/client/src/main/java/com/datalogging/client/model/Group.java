package com.datalogging.client.model;

import com.datalogging.client.HTTP;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.GenericUrl;

import java.util.Arrays;
import java.util.List;

public class Group {
    private Long id;
    private String name;
    private String description;
    private Boolean visible;
    @JsonIgnore
    private String password;
    private Boolean permissionRequired;
    private Boolean root;
    private String user;

    public Group() {

    }

    public Group(String name, String description, Boolean visible, String password, Boolean permissionRequired) {
        this.name = name;
        this.description = description;
        this.visible = visible;
        this.password = password;
        this.permissionRequired = permissionRequired;
    }

    public static List<Group> getAllVisible() {
        String pathURL = "/unauthorized/group/";

        try {
            String response = HTTP.httpRequestFactory.buildGetRequest(new GenericUrl(HTTP.apiURL + pathURL)).execute().parseAsString();

            return Arrays.asList(new ObjectMapper().readValue(response, Group[].class));
        } catch (Exception e) {
            return null;
        }
    }

    public static GroupAuthentication authorize(String groupName, String password) {
        String pathURL = "/unauthorized/group/" + groupName + "/authorize?password=" + password;

        try {
            String response = HTTP.httpRequestFactory.buildGetRequest(new GenericUrl(HTTP.apiURL + pathURL)).execute().parseAsString();

            return new ObjectMapper().readValue(response, GroupAuthentication.class);
        } catch (Exception e) {
            return null;
        }
    }

    public static Group create(User user, Group group) {
        String pathURL = "/authorized/group/" + user.getUsername() +
                "?name=" + group.getName() +
                "&description=" + group.getDescription() +
                "&visible=" + group.getVisible() +
                "&password=" + group.getPassword() +
                "&permission-required=" + group.getPermissionRequired() +
                "&user-token=" + user.getToken();

        try {
            String response = HTTP.httpRequestFactory.buildPostRequest(new GenericUrl(HTTP.apiURL + pathURL), null).execute().parseAsString();
            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(response, Group.class);
        } catch (Exception e) {
            return null;
        }
    }

    public static Group setAsRoot(User user, Group group) {
        String pathURL = "/authorized/group/root/" + user.getUsername() + "/" + group.getName() +
                "?user-token=" + user.getToken();

        try {
            String response = HTTP.httpRequestFactory.buildPutRequest(new GenericUrl(HTTP.apiURL + pathURL), null).execute().parseAsString();

            return new ObjectMapper().readValue(response, Group.class);
        } catch (Exception e) {
            return null;
        }
    }

    public static List<Group> getAllFromUser(User user) {
        String pathURL = "/authorized/group/" + user.getUsername() + "?user-token=" + user.getToken();

        try {
            String response = HTTP.httpRequestFactory.buildGetRequest(new GenericUrl(HTTP.apiURL + pathURL)).execute().parseAsString();
            ObjectMapper mapper = new ObjectMapper();

            List<Group> groups = Arrays.asList(mapper.readValue(response, Group[].class));
            return groups;
        } catch (Exception e) {
            return null;
        }
    }

    public static Group update(User user, String name, Group group) {
        String pathURL = "/authorized/group/" + user.getUsername() + "/" + name +
                "?name=" + group.getName() +
                "&description=" + group.getDescription() +
                "&visible=" + group.getVisible() +
                "&password=" + group.getPassword() +
                "&permission-required=" + group.getPermissionRequired() +
                "&user-token=" + user.getToken();

        try {
            String response = HTTP.httpRequestFactory.buildPutRequest(new GenericUrl(HTTP.apiURL + pathURL), null).execute().parseAsString();

            return new ObjectMapper().readValue(response, Group.class);
        } catch (Exception e) {
            return null;
        }
    }

    public static String delete(User user, String name) {
        String pathURL = "/authorized/group/" + user.getUsername() + "/" + name + "?user-token=" + user.getToken();

        try {
            HTTP.httpRequestFactory.buildDeleteRequest(new GenericUrl(HTTP.apiURL + pathURL)).execute();
            return "success";
        } catch (Exception e) {
            return null;
        }
    }

    public Boolean getRoot() {
        return root;
    }

    public void setRoot(Boolean root) {
        this.root = root;
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
}
