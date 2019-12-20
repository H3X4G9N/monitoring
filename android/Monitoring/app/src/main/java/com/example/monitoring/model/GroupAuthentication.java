package com.example.monitoring.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class GroupAuthentication implements Serializable {
    private String token;
    private Long group;

    public GroupAuthentication() {

    }

    public GroupAuthentication(String token, Long group) {
        this.token = token;
        this.group = group;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getGroup() {
        return group;
    }

    public void setGroup(Long group) {
        this.group = group;
    }
}
