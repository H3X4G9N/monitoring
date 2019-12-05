package com.datacollection.client.model;

public class DCGroup {
    private Long id;
    private String name;
    private String description;
    private Boolean visible;
    private Boolean permissionRequired;
    private String password;
    private String token;

    public DCGroup() {

    }

    public DCGroup(String name, String description, Boolean permissionRequired, Boolean visible, String password, String token) {
        this.name = name;
        this.description = description;
        this.visible = visible;
        this.permissionRequired = permissionRequired;
        this.password = password;
        this.token = token;
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

    public Boolean getPermissionRequired() {
        return permissionRequired;
    }

    public void setPermissionRequired(Boolean permissionRequired) {
        this.permissionRequired = permissionRequired;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
