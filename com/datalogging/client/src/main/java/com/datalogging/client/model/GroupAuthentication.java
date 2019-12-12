package com.datalogging.client.model;

public class GroupAuthentication {
    private String token;
    private String group;

    public GroupAuthentication() {

    }

    public GroupAuthentication(String token, String group) {
        this.token = token;
        this.group = group;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
