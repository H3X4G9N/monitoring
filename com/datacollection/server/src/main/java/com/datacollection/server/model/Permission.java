package com.datacollection.server.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

class PermissionID implements Serializable {
    public Long permittedUser;
    public Long permissibleDCGroup;
    public Long user;
}

@Entity
@Table(name = "permission")
@IdClass(PermissionID.class)
public class Permission {
    @JoinColumn(name = "permitted_user")
    @Id
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "username")
    @JsonIdentityReference(alwaysAsId = true)
    private User permittedUser;

    @JoinColumn(name = "permissible_dc_group")
    @Id
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    @JsonIdentityReference(alwaysAsId = true)
    private DCGroup permissibleDCGroup;

    @JoinColumn(name = "user")
    @Id
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "username")
    @JsonIdentityReference(alwaysAsId = true)
    private User user;

    public Permission() {

    }

    public Permission(User permittedUser, DCGroup permissibleGroup, User user) {
        this.permittedUser = permittedUser;
        this.permissibleDCGroup = permissibleGroup;
        this.user = user;
    }

    public User getPermittedUser() {
        return permittedUser;
    }

    public void setPermittedUser(User permittedUser) {
        this.permittedUser = permittedUser;
    }

    public DCGroup getPermissibleDCGroup() {
        return permissibleDCGroup;
    }

    public void setPermissibleDCGroup(DCGroup permissibleDCGroup) {
        this.permissibleDCGroup = permissibleDCGroup;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
