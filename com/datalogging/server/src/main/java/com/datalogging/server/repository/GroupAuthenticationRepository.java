package com.datalogging.server.repository;

import com.datalogging.server.model.GroupAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupAuthenticationRepository extends JpaRepository<GroupAuthentication, Long> {
    @Query(value = "SELECT * FROM dl_group_authentication WHERE token = :token AND `group` = :group", nativeQuery = true)
    GroupAuthentication findByTokenAndGroup(@Param("token") String token, @Param("group") Long group);
}
