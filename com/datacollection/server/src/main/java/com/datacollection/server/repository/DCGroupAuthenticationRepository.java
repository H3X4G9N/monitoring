package com.datacollection.server.repository;

import com.datacollection.server.model.DC;
import com.datacollection.server.model.DCGroupAuthentication;
import com.datacollection.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DCGroupAuthenticationRepository extends JpaRepository<DCGroupAuthentication, Long> {
    @Query(value = "SELECT * FROM dc_group_authentication WHERE token = :token AND dc_group = :dc_group", nativeQuery = true)
    DCGroupAuthentication findByTokenAndGroup(@Param("token") String token, @Param("dc_group") Long dcGroup);
}
