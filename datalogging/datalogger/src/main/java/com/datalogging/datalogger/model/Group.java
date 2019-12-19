package com.datalogging.datalogger.model;

import com.datalogging.datalogger.HTTP;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.api.client.http.GenericUrl;

import java.util.Arrays;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Group {
    private Long id;
    private Boolean root;
    private String rootName;
    private String name;
    private String description;
    private Boolean visible;
    private Boolean passwordRequired;
    private String password;
    private Boolean permissionRequired;
    private String user;

    public Group() {

    }

    public Group(String name, String description, Boolean visible, Boolean passwordRequired, String password, Boolean permissionRequired) {
        this.name = name;
        this.description = description;
        this.visible = visible;
        this.passwordRequired = passwordRequired;
        this.password = password;
        this.permissionRequired = permissionRequired;
    }

    public static Group create(User user, Group group) {
        String pathURL = "/authorized/group/" + user.getUsername() +
                "?name=" + group.getName() +
                "&description=" + group.getDescription() +
                "&visible=" + group.getVisible() +
                "&password-required=" + group.getPasswordRequired() +
                "&password=" + group.getPassword() +
                "&permission-required=" + group.getPermissionRequired() +
                "&user-token=" + user.getToken();

        try {
            String response = HTTP.httpRequestFactory.buildPostRequest(new GenericUrl(HTTP.apiURL + pathURL), null).execute().parseAsString();

            return HTTP.objectMapper.readValue(response, Group.class);
        } catch (Exception e) {
            return null;
        }
    }

    public static Group getRootFromUser(User user) {
        String pathURL = "/authorized/group/root/" + user.getUsername() + "?user-token=" + user.getToken();

        try {
            String response = HTTP.httpRequestFactory.buildGetRequest(new GenericUrl(HTTP.apiURL + pathURL)).execute().parseAsString();

            return HTTP.objectMapper.readValue(response, Group.class);
        } catch (Exception e) {
            return null;
        }
    }

    public static List<Group> getAllFromUser(User user) {
        String pathURL = "/authorized/group/" + user.getUsername() + "?user-token=" + user.getToken();

        try {
            String response = HTTP.httpRequestFactory.buildGetRequest(new GenericUrl(HTTP.apiURL + pathURL)).execute().parseAsString();

            return Arrays.asList(HTTP.objectMapper.readValue(response, Group[].class));
        } catch (Exception e) {
            return null;
        }
    }

    public static Group update(User user, Group currentGroup, Group group) {
        String pathURL = "/authorized/group/" + user.getUsername() + "/" + currentGroup.getID() +
                "?name=" + group.getName() +
                "&description=" + group.getDescription() +
                "&visible=" + group.getVisible() +
                "&password-required=" + group.getPasswordRequired() +
                "&password=" + group.getPassword() +
                "&permission-required=" + group.getPermissionRequired() +
                "&user-token=" + user.getToken();

        try {
            String response = HTTP.httpRequestFactory.buildPutRequest(new GenericUrl(HTTP.apiURL + pathURL), null).execute().parseAsString();

            return HTTP.objectMapper.readValue(response, Group.class);
        } catch (Exception e) {
            return null;
        }
    }

    public static String delete(User user, Group group) {
        String pathURL = "/authorized/group/" + user.getUsername() + "/" + group.getID() + "?user-token=" + user.getToken();

        try {
            HTTP.httpRequestFactory.buildDeleteRequest(new GenericUrl(HTTP.apiURL + pathURL)).execute();
            return "success";
        } catch (Exception e) {
            return null;
        }
    }

    public static GroupAuthentication authorize(User user, Group group, String password) {
        String pathURL = "/authorized/group/" + group.getID() + "/" + user.getUsername() + "/authorize?password=" + password + "&user-token=" + user.getToken();

        try {
            String response = HTTP.httpRequestFactory.buildGetRequest(new GenericUrl(HTTP.apiURL + pathURL)).execute().parseAsString();

            return HTTP.objectMapper.readValue(response, GroupAuthentication.class);
        } catch (Exception e) {
            return null;
        }
    }

    public static List<Group> getAllVisibleRoot() {
        String pathURL = "/unauthorized/group/root";

        try {
            String response = HTTP.httpRequestFactory.buildGetRequest(new GenericUrl(HTTP.apiURL + pathURL)).execute().parseAsString();

            return Arrays.asList(HTTP.objectMapper.readValue(response, Group[].class));
        } catch (Exception e) {
            return null;
        }
    }

    public static List<Group> getAllVisible(GroupAuthentication groupAuthentication) {
        String pathURL = "/unauthorized/group/" + groupAuthentication.getGroup() +
                "?token=" + groupAuthentication.getToken();

        try {
            String response = HTTP.httpRequestFactory.buildGetRequest(new GenericUrl(HTTP.apiURL + pathURL)).execute().parseAsString();

            return Arrays.asList(HTTP.objectMapper.readValue(response, Group[].class));
        } catch (Exception e) {
            return null;
        }
    }

    public static GroupAuthentication authorize(Group group, String password) {
        String pathURL = "/unauthorized/group/" + group.getID() + "/authorize?password=" + password;

        try {
            String response = HTTP.httpRequestFactory.buildGetRequest(new GenericUrl(HTTP.apiURL + pathURL)).execute().parseAsString();

            return HTTP.objectMapper.readValue(response, GroupAuthentication.class);
        } catch (Exception e) {
            return null;
        }
    }

    public static GroupAuthentication authenticate(GroupAuthentication groupAuthentication) {
        String pathURL = "/unauthorized/group/" + groupAuthentication.getGroup() + "/authenticate?token=" + groupAuthentication.getToken();

        try {
            String response = HTTP.httpRequestFactory.buildGetRequest(new GenericUrl(HTTP.apiURL + pathURL)).execute().parseAsString();

            return HTTP.objectMapper.readValue(response, GroupAuthentication.class);
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

    public Boolean getRoot() {
        return root;
    }

    public void setRoot(Boolean root) {
        this.root = root;
    }

    public String getRootName() {
        return rootName;
    }

    public void setRootName(String rootName) {
        this.rootName = rootName;
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

    public Boolean getPasswordRequired() {
        return passwordRequired;
    }

    public void setPasswordRequired(Boolean passwordRequired) {
        this.passwordRequired = passwordRequired;
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
