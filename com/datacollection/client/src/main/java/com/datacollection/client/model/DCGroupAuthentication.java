package com.datacollection.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DCGroupAuthentication {
    private String token;
    @JsonProperty(value = "dcGroup")
    private String dcGroup;

    public DCGroupAuthentication() {

    }

    public DCGroupAuthentication(String token, String dcGroup) {
        this.token = token;
        this.dcGroup = dcGroup;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @JsonProperty(value = "dcGroup")
    public String getDcGroup() {
        return dcGroup;
    }

    @JsonProperty(value = "dcGroup")
    public void setDcGroup(String dcGroup) {
        this.dcGroup = dcGroup;
    }
}
