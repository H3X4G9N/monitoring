package com.datacollection.server.model;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "dc")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = "dcgroup")
public class DC {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "password", nullable = false)
    @JsonIgnore
    private String password;

    @Column(name = "activated", nullable = false)
    @JsonIgnore
    private Boolean activated;

    @Column(name = "activation_key", unique = true, nullable = false)
    @JsonIgnore
    private String activationKey;

    @Column(name = "token", unique = true)
    @JsonIgnore
    private String token;

    @JoinColumn(name = "user")
    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "username")
    @JsonIdentityReference(alwaysAsId = true)
    private User user;

    @JoinColumn(name = "dcGroup")
    @OneToOne(targetEntity = DCGroup.class, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonProperty(value = "dcGroup")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    @JsonIdentityReference(alwaysAsId = true)
    private DCGroup dcGroup;

    public DC() {

    }

    public DC(String name, String description, String password, Boolean activated, String activationKey) {
        this.name = name;
        this.description = description;
        this.password = password;
        this.activated = activated;
        this.activationKey = activationKey;
    }

    public Long getID() {
        return id;
    }

    public void setID(Long id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
