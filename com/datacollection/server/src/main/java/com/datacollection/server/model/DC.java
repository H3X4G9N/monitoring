package com.datacollection.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "data_collector")
public class DC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String password;
    private String token;
    private Boolean activated;
    private String activationKey;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dcGroup", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private DCGroup dcGroup;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userDC", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User userDC;

    @OneToMany(mappedBy = "dataCollector")
    private List<DCData> dcData = new ArrayList<DCData>();

    public DC() {

    }

    public DC(String name, String description, String password, String token, Boolean activated, String activationKey, DCGroup dcGroup) {
        this.name = name;
        this.description = description;
        this.password = password;
        this.token = token;
        this.activated = activated;
        this.activationKey = activationKey;
        this.dcGroup = dcGroup;
    }

    public Long getId() {
        return id;
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

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public DCGroup getDCGroup() {
        return dcGroup;
    }

    public void setDCGroup(DCGroup dcGroup) {
        this.dcGroup = dcGroup;
    }

    public User getUserDC() {
        return userDC;
    }

    public void setUserDC(User userDC) {
        this.userDC = userDC;
    }
}
