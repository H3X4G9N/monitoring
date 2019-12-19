package com.datalogging.server.repository;

import com.datalogging.server.model.GroupPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupPermissionRepository extends JpaRepository<GroupPermission, Long> {
    @Query(value = "SELECT * FROM dl_group_permission WHERE user = :user AND permissible_group = :permissible_group", nativeQuery = true)
    List<GroupPermission> findAllByUserAndPermissibleGroup(@Param("user") Long user, @Param("permissible_group") Long permissibleGroup);

    @Query(value = "SELECT * FROM dl_group_permission WHERE user = :user AND permissible_group = :permissible_group AND permitted_user = :permitted_user", nativeQuery = true)
    GroupPermission findByUserPermittedUserAndPermissibleGroup(@Param("user") Long user, @Param("permitted_user") Long permittedUser, @Param("permissible_group") Long permissibleGroup);

    @Query(value = "SELECT * FROM dl_group_permission WHERE  permissible_group = :permissible_group AND permitted_user = :permitted_user", nativeQuery = true)
    GroupPermission findByPermittedUserAndPermissibleGroup( @Param("permitted_user") Long permittedUser, @Param("permissible_group") Long permissibleGroup);
}
