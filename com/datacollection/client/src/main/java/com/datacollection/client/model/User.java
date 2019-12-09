package com.datacollection.client.model;

import com.datacollection.client.HTTP;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.GenericUrl;

public class User  {
    private long  id;
    private String username;
    private String email;
    @JsonIgnore
    private String password;
    private String token;

    public User () {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static String register(String username, String email, String password) {
        String pathURL = "/unauthorized/user/register?username=" + username + "&email=" + email + "&password=" + password;

        try {
            HTTP.httpRequestFactory.buildPostRequest(new GenericUrl(HTTP.apiURL + pathURL), null).execute();
            return "success";
        } catch (Exception e) {
            return null;
        }
    }

    public static User authorize(String username, String password) {
        String pathURL = "/unauthorized/user/authorize?username=" + username + "&password=" + password;

        try {
            String response = HTTP.httpRequestFactory.buildGetRequest(new GenericUrl(HTTP.apiURL + pathURL)).execute().parseAsString();
            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(response, User.class);
        } catch (Exception e) {
            return null;
        }
    }

    public static String authenticate(User user) {
        String pathURL = "/authorized/user/authenticate?username=" + user.getUsername() + "&token=" + user.getToken();



        try {
            String response = HTTP.httpRequestFactory.buildGetRequest(new GenericUrl(HTTP.apiURL + pathURL)).execute().parseAsString();

            return "success";
        } catch (Exception e) {
            return null;
        }
    }
}