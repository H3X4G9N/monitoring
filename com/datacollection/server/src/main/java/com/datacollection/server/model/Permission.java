package com.datacollection.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "permission")
public class Permission {
    @EmbeddedId
    private PermissionId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @ManyToOne
    @MapsId("permitted_user_id")
    @JoinColumn(name = "permitted_user_id")
    User permittedUser;

    @ManyToOne
    @MapsId("dc_group_id")
    @JoinColumn(name = "dc_group_id")
    DCGroup dcGroup;

    public Permission() {

    }

    @Embeddable
    public class PermissionId implements Serializable {
        @Column(name = "permitted_user_id")
        private Long permittedUser;
        @Column(name = "dc_group_id")
        private Long dcGroup;

        public PermissionId() {

        }

        public PermissionId(Long permittedUser, Long dcGroup) {
            this.permittedUser = permittedUser;
            this.dcGroup = dcGroup;
        }

        public Long getPermittedUser() {
            return permittedUser;
        }

        public void setPermittedUser(Long permittedUser) {
            this.permittedUser = permittedUser;
        }

        public Long getDcGroup() {
            return dcGroup;
        }

        public void setDcGroup(Long dcGroup) {
            this.dcGroup = dcGroup;
        }
    }

    public Permission(PermissionId id, User user) {
        this.id = id;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
