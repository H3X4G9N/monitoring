package com.datalogging.server.repository;

import com.datalogging.server.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    @Query(value = "SELECT * FROM dl_group WHERE root = true AND visible = true", nativeQuery = true)
    List<Group> findAllByRootAndVisible();

    @Query(value = "SELECT * FROM dl_group WHERE root = true AND user = :user", nativeQuery = true)
    Group findRootBydUser(@Param("user") Long user);

    @Query(value = "SELECT * FROM dl_group WHERE id = :id AND user = :user", nativeQuery = true)
    Group findByIDAndUser(@Param("id") Long id, @Param("user") Long user);

    @Query(value = "SELECT * FROM dl_group WHERE id = :id", nativeQuery = true)
    Group findByID(@Param("id") Long id);

    @Query(value = "SELECT * FROM dl_group WHERE visible = true AND id = :id", nativeQuery = true)
    Group findVisibleByID(@Param("id") Long id);

    @Query(value = "SELECT * FROM dl_group WHERE visible = true AND user = :user AND root = false", nativeQuery = true)
    List<Group> findAllVisibleByUser(@Param("user") Long user);

    @Query(value = "SELECT * FROM dl_group WHERE user = :user AND root = false", nativeQuery = true)
    List<Group> findAllByUser(@Param("user") Long user);
}
