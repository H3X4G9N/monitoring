package com.datalogging.client.model;

import com.datalogging.client.HTTP;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.GenericUrl;

import java.util.Arrays;
import java.util.List;

public class GroupPermission {
    private String permittedUser;
    private String permissibleGroup;
    private String user;

    public GroupPermission() {
    }

    public GroupPermission(String permittedUser, String permissibleGroup) {
        this.permissibleGroup = permissibleGroup;
        this.permittedUser = permittedUser;
    }

    public static GroupPermission create(User user, GroupPermission permission) {
        String pathURL = "/authorized/group-permission/" + user.getUsername() +
                "?permitted-user-username=" + permission.getPermittedUser() +
                "&permissible-group-name=" + permission.getPermissibleGroup() +
                "&user-token=" + user.getToken();

        try {
            String response = HTTP.httpRequestFactory.buildPostRequest(new GenericUrl(HTTP.apiURL + pathURL), null).execute().parseAsString();

            return new ObjectMapper().readValue(response, GroupPermission.class);
        } catch (Exception e) {
            return null;
        }
    }

    public static List<GroupPermission> getAllFromGroup(User user, String groupName) {
        String pathURL = "/authorized/group-permission/" + user.getUsername() + "/" + groupName +
                "?user-token=" + user.getToken();

        try {
            String response = HTTP.httpRequestFactory.buildGetRequest(new GenericUrl(HTTP.apiURL + pathURL)).execute().parseAsString();


            return Arrays.asList(new ObjectMapper().readValue(response, GroupPermission[].class));
        } catch (Exception e) {
            return null;
        }


    }

    public static GroupPermission update(User user, String permittedUserUsername, String permissibleGroupName, GroupPermission permission) {
        String pathURL = "/authorized/group-permission/" + user.getUsername() + "/" + permittedUserUsername + "/" + permissibleGroupName +
                "?permitted-user-username=" + permission.getPermittedUser() +
                "&permissible-group-name=" + permission.getPermissibleGroup() +
                "&user-token=" + user.getToken();

        try {
            String response = HTTP.httpRequestFactory.buildPutRequest(new GenericUrl(HTTP.apiURL + pathURL), null).execute().parseAsString();

            return new ObjectMapper().readValue(response, GroupPermission.class);
        } catch (Exception e) {
            return null;
        }
    }

    public static String delete(User user, String permittedUserUsername, String permissibleGroupName) {
        String pathURL = "/authorized/group-permission/" + user.getUsername() + "/" + permittedUserUsername + "/" + permissibleGroupName +
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

    public String getPermissibleGroup() {
        return permissibleGroup;
    }

    public void setPermissibleGroup(String permissibleGroup) {
        this.permissibleGroup = permissibleGroup;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
