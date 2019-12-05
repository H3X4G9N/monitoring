package com.datacollection.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dc_group")
public class DCGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String description;
    private Boolean visible;
    private Boolean permissionRequired;
    private String password;
    @Column(unique = true)
    private String token;



    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;
    @OneToMany(mappedBy = "dcGroup")
    private List<DC> dataCollectors = new ArrayList<DC>();

    public DCGroup() {

    }

    public DCGroup(String name, String description, Boolean visible, String password, Boolean permissionRequired, String token, User user) {
        this.name = name;
        this.description = description;
        this.visible = visible;
        this.password = password;
        this.permissionRequired = permissionRequired;
        this.token = token;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
