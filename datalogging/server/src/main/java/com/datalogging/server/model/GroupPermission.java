package com.datalogging.server.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

class PermissionID implements Serializable {
    public Long permittedUser;
    public Long permissibleGroup;
    public Long user;
}

@Table(name = "dl_group_permission")
@Entity
@IdClass(PermissionID.class)
public class GroupPermission {
    @JoinColumn(name = "permitted_user")
    @Id
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "username")
    @JsonIdentityReference(alwaysAsId = true)
    private User permittedUser;

    @JoinColumn(name = "permissible_group")
    @Id
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Group permissibleGroup;

    @JoinColumn(name = "user")
    @Id
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "username")
    @JsonIdentityReference(alwaysAsId = true)
    private User user;

    public GroupPermission() {

    }

    public GroupPermission(User permittedUser, Group permissibleGroup, User user) {
        this.permittedUser = permittedUser;
        this.permissibleGroup = permissibleGroup;
        this.user = user;
    }

    public User getPermittedUser() {
        return permittedUser;
    }

    public void setPermittedUser(User permittedUser) {
        this.permittedUser = permittedUser;
    }

    public Group getPermissibleGroup() {
        return permissibleGroup;
    }

    public void setPermissibleGroup(Group permissibleGroup) {
        this.permissibleGroup = permissibleGroup;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
