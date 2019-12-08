package com.datacollection.server.repository;

import com.datacollection.server.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    @Query(value = "SELECT * FROM permission WHERE user = :user AND permissible_dc_group = :permissible_dc_group", nativeQuery = true)
    List<Permission> findAllByUserAndPermissibleDCGroup(@Param("user") Long user, @Param("permissible_dc_group") Long permissibleDCGroup);

    @Query(value = "SELECT * FROM permission WHERE user = :user AND permissible_dc_group = :permissible_dc_group AND permitted_user = :permitted_user", nativeQuery = true)
    Permission findByUserPermittedUserAndPermissibleDCGroup(@Param("user") Long user, @Param("permitted_user") Long permittedUser, @Param("permissible_dc_group") Long permissibleDCGroup);
}
