package com.datacollection.server.repository;

import com.datacollection.server.model.DCGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DCGroupRepository extends JpaRepository<DCGroup, Long> {
    @Query(value = "SELECT * FROM dc_group WHERE id = :id", nativeQuery = true)
    DCGroup findByID(@Param("id") Long id);

    @Query(value = "SELECT * FROM dc_group WHERE name = :name", nativeQuery = true)
    DCGroup findByName(@Param("name") String name);

    @Query(value = "SELECT * FROM dc_group WHERE visible = true AND id = :id", nativeQuery = true)
    DCGroup findVisibleByID(@Param("id") Long id);

    @Query(value = "SELECT * FROM dc_group WHERE visible = true AND name = :name AND password = :password", nativeQuery = true)
    DCGroup findVisibleByNameAndPassword(@Param("name") String name, @Param("password") String password);

    @Query(value = "SELECT * FROM dc_group WHERE visible = true", nativeQuery = true)
    List<DCGroup> findAllVisible();

    @Query(value = "SELECT * FROM dc_group WHERE visible = true AND name = :name", nativeQuery = true)
    DCGroup findVisibleByName(@Param("name") String name);

    @Query(value = "SELECT * FROM dc_group WHERE user = :user", nativeQuery = true)
    List<DCGroup> findAllByUser(@Param("user") Long user);

    @Query(value = "SELECT * FROM dc_group WHERE token = :token", nativeQuery = true)
    DCGroup findByDCGroupToken(@Param("token") String token);

    @Query(value = "SELECT * FROM dc_group WHERE name = :name AND user = :user ", nativeQuery = true)
    DCGroup findByNameAndUser(@Param("name") String name, @Param("user") Long user);

    @Query(value = "SELECT * FROM dc_group WHERE id = :id AND user = :user ", nativeQuery = true)
    DCGroup findByIDAndUser(@Param("id") Long id, @Param("user") Long user);
}
