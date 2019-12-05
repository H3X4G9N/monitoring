package com.datacollection.server.repository;

import com.datacollection.server.model.DCGroup;
import com.datacollection.server.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<DCGroup, Long> {
    @Query(value = "SELECT * FROM permission WHERE user = :user AND dc_group = :dc_group", nativeQuery = true)
    Permission findByUserAndDCGroup(@Param("user") String user, @Param("dc_group") String dcGroup);
}
