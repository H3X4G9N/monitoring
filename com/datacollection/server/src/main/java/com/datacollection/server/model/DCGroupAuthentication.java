package com.datacollection.server.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "dc_group_authentication")
@EntityListeners(AuditingEntityListener.class)
public class DCGroupAuthentication {
    @Column(name = "token", unique = true)
    @Id
    private String token;

    @JoinColumn(name = "dc_group", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    @JsonIdentityReference(alwaysAsId = true)
    private DCGroup dcGroup;

    public DCGroupAuthentication() {

    }

    public DCGroupAuthentication(String token, DCGroup dcGroup) {
        this.token = token;
        this.dcGroup = dcGroup;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public DCGroup getDcGroup() {
        return dcGroup;
    }

    public void setDcGroup(DCGroup dcGroup) {
        this.dcGroup = dcGroup;
    }
}
