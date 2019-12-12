package com.datalogging.server.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "dl_group")
public class Group {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "visible", nullable = false)
    private Boolean visible;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    @Column(name = "permission_required", nullable = false)
    private Boolean permissionRequired;

    @JoinColumn(name = "root", nullable = false)
    private Boolean root;

    @JoinColumn(name = "user", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "username")
    @JsonIdentityReference(alwaysAsId = true)
    private User user;

    public Group() {

    }

    public Group(String name, String description, Boolean visible, String password, Boolean permissionRequired, User user) {
        this.name = name;
        this.description = description;
        this.visible = visible;
        this.password = password;
        this.permissionRequired = permissionRequired;
        this.user = user;
    }

    public Long getID() {
        return id;
    }

    public void setID(Long id) {
        this.id = id;
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

    public Boolean getPermissionRequired() {
        return permissionRequired;
    }

    public void setPermissionRequired(Boolean permissionRequired) {
        this.permissionRequired = permissionRequired;
    }

    public Boolean getRoot() {
        return root;
    }

    public void setRoot(Boolean primary) {
        this.root = primary;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
