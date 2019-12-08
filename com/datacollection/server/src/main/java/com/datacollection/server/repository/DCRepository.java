package com.datacollection.server.repository;

import com.datacollection.server.model.DC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DCRepository extends JpaRepository<DC, Long> {
    @Query(value = "SELECT * FROM dc WHERE activated = false AND activation_key = :activation_key", nativeQuery = true)
    DC findUnactivatedByActivationKey(@Param("activation_key") String activationKey);

    @Query(value = "SELECT * FROM dc WHERE id = :id", nativeQuery = true)
    DC findByID(@Param("id") Long id);

    @Query(value = "SELECT * FROM dc WHERE user = :user", nativeQuery = true)
    List<DC> findAllByUser(@Param("user") Long user);

    @Query(value = "SELECT * FROM dc WHERE dc_group = :dc_group", nativeQuery = true)
    List<DC> findAllByDCGroup(@Param("dc_group") Long dcGroup);

    @Query(value = "SELECT * FROM dc WHERE id = :id AND user = :user", nativeQuery = true)
    DC findByIDAndUser(@Param("id") Long group, @Param("user") Long user);
}
