package com.example.monitoring.model;

import com.example.monitoring.HTTP;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.api.client.http.GenericUrl;

import java.util.Arrays;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class GroupPermission {
    private String permittedUser;
    private Long permissibleGroup;
    private String user;

    public GroupPermission() {
    }

    public GroupPermission(String permittedUser, Long permissibleGroup) {
        this.permissibleGroup = permissibleGroup;
        this.permittedUser = permittedUser;
    }

    public static GroupPermission create(User user, GroupPermission permission) {
        String pathURL = "/authorized/group-permission/" + user.getUsername() +
                "?permitted-user-username=" + permission.getPermittedUser() +
                "&permissible-group-id=" + permission.getPermissibleGroup() +
                "&user-token=" + user.getToken();

        try {
            String response = HTTP.httpRequestFactory.buildPostRequest(new GenericUrl(HTTP.apiURL + pathURL), null).execute().parseAsString();

            return HTTP.objectMapper.readValue(response, GroupPermission.class);
        } catch (Exception e) {
            return null;
        }
    }

    public static List<GroupPermission> getAllFromGroup(User user, Group group) {
        String pathURL = "/authorized/group-permission/" + user.getUsername() + "/" + group.getID() +
                "?user-token=" + user.getToken();

        try {
            String response = HTTP.httpRequestFactory.buildGetRequest(new GenericUrl(HTTP.apiURL + pathURL)).execute().parseAsString();

            return Arrays.asList(HTTP.objectMapper.readValue(response, GroupPermission[].class));
        } catch (Exception e) {
            return null;
        }

    }

    public static GroupPermission update(User user, String permittedUserUsername, Long permissibleGroupID, GroupPermission permission) {
        String pathURL = "/authorized/group-permission/" + user.getUsername() + "/" + permittedUserUsername + "/" + permissibleGroupID +
                "?permitted-user-username=" + permission.getPermittedUser() +
                "&permissible-group-id=" + permission.getPermissibleGroup() +
                "&user-token=" + user.getToken();

        try {
            String response = HTTP.httpRequestFactory.buildPutRequest(new GenericUrl(HTTP.apiURL + pathURL), null).execute().parseAsString();

            return HTTP.objectMapper.readValue(response, GroupPermission.class);
        } catch (Exception e) {
            return null;
        }
    }

    public static String delete(User user, String permittedUserUsername, Long permissibleGroupID) {
        String pathURL = "/authorized/group-permission/" + user.getUsername() + "/" + permittedUserUsername + "/" + permissibleGroupID +
                "?user-token=" + user.getToken();

        try {
            String response = HTTP.httpRequestFactory.buildDeleteRequest(new GenericUrl(HTTP.apiURL + pathURL)).execute().parseAsString();

            return "success";
        } catch (Exception e) {
            return null;
        }
    }

    public String getPermittedUser() {
        return permittedUser;
    }

    public void setPermittedUser(String permittedUser) {
        this.permittedUser = permittedUser;
    }

    public Long getPermissibleGroup() {
        return permissibleGroup;
    }

    public void setPermissibleGroup(Long permissibleGroup) {
        this.permissibleGroup = permissibleGroup;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
