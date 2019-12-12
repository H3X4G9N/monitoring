package com.datalogging.server.repository;

import com.datalogging.server.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    @Query(value = "SELECT * FROM dl_group WHERE id = :id", nativeQuery = true)
    Group findByID(@Param("id") Long id);

    @Query(value = "SELECT * FROM dl_group WHERE name = :name", nativeQuery = true)
    Group findByName(@Param("name") String name);

    @Query(value = "SELECT * FROM dl_group WHERE visible = true AND id = :id", nativeQuery = true)
    Group findVisibleByID(@Param("id") Long id);

    @Query(value = "SELECT * FROM dl_group WHERE visible = true AND name = :name AND password = :password", nativeQuery = true)
    Group findVisibleByNameAndPassword(@Param("name") String name, @Param("password") String password);

    @Query(value = "SELECT * FROM dl_group WHERE visible = true AND root = true", nativeQuery = true)
    List<Group> findAllVisible();

    @Query(value = "SELECT * FROM dl_group WHERE visible = true AND name = :name", nativeQuery = true)
    Group findVisibleByName(@Param("name") String name);

    @Query(value = "SELECT * FROM dl_group WHERE user = :user", nativeQuery = true)
    List<Group> findAllByUser(@Param("user") Long user);

    @Query(value = "SELECT * FROM dl_group WHERE token = :token", nativeQuery = true)
    Group findByGroupToken(@Param("token") String token);

    @Query(value = "SELECT * FROM dl_group WHERE name = :name AND user = :user ", nativeQuery = true)
    Group findByNameAndUser(@Param("name") String name, @Param("user") Long user);

    @Query(value = "SELECT * FROM dl_group WHERE id = :id AND user = :user ", nativeQuery = true)
    Group findByIDAndUser(@Param("id") Long id, @Param("user") Long user);
}
