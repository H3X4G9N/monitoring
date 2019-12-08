package com.datacollection.client.model;

import com.datacollection.client.model.DCGroup;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.GenericUrl;
import com.datacollection.client.HTTP;

import java.util.Arrays;
import java.util.List;

public class Permission {
    private String permittedUser;
    private String permissibleDCGroup;
    private String user;

    public String getPermittedUser() {
        return permittedUser;
    }

    public void setPermittedUser(String permittedUser) {
        this.permittedUser = permittedUser;
    }

    public String getPermissibleDCGroup() {
        return permissibleDCGroup;
    }

    public void setPermissibleDCGroup(String permissibleDCGroup) {
        this.permissibleDCGroup = permissibleDCGroup;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Permission() {}

    public Permission(String permittedUser, String permissibleDCGroup) {
        this.permissibleDCGroup = permissibleDCGroup;
        this.permittedUser = permittedUser;
    }

    public static Permission create(User user, Permission permission) {
        String pathURL = "/authorized/permission/" + user.getUsername() +
                "?permitted-user-username=" + permission.getPermittedUser() +
                "&permissible-dc-group-name=" + permission.getPermissibleDCGroup() +
                "&user-token=" + user.getToken();

        try {
            String response = HTTP.httpRequestFactory.buildPostRequest(new GenericUrl(HTTP.apiURL + pathURL), null).execute().parseAsString();

            return new ObjectMapper().readValue(response, Permission.class);
        } catch (Exception e) {
            return null;
        }
    }

    public static List<Permission> getAllFromDCGroup(User user, String dcGroupName) {
        String pathURL = "/authorized/permission/" + user.getUsername() + "/" + dcGroupName +
                "?user-token=" + user.getToken();

        try {
            String response = HTTP.httpRequestFactory.buildGetRequest(new GenericUrl(HTTP.apiURL + pathURL)).execute().parseAsString();

            return Arrays.asList(new ObjectMapper().readValue(response, Permission[].class));
        } catch (Exception e) {
            return null;
        }


    }

    public static Permission update(User user, String permittedUserUsername, String permissibleDCGroupName, Permission permission) {
        String pathURL = "/authorized/permission/" + user.getUsername() + "/" + permittedUserUsername + "/" + permissibleDCGroupName +
                "?permitted-user-username=" + permission.getPermittedUser() +
                "&permissible-dc-group-name=" + permission.getPermissibleDCGroup() +
                "&user-token=" + user.getToken();

        try {
            String response = HTTP.httpRequestFactory.buildPutRequest(new GenericUrl(HTTP.apiURL + pathURL), null).execute().parseAsString();

            return new ObjectMapper().readValue(response, Permission.class);
        } catch (Exception e) {
            return null;
        }
    }

    public static String delete(User user, String permittedUserUsername, String permissibleDCGroupName) {
        String pathURL = "/authorized/permission/" + user.getUsername() + "/" + permittedUserUsername + "/" + permissibleDCGroupName +
                "?user-token=" + user.getToken();

        try {
            String response = HTTP.httpRequestFactory.buildDeleteRequest(new GenericUrl(HTTP.apiURL + pathURL)).execute().parseAsString();

            return "success";
        } catch (Exception e) {
            return null;
        }
    }
}
