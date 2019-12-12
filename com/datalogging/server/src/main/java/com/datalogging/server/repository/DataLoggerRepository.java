package com.datalogging.server.repository;

import com.datalogging.server.model.DataLogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataLoggerRepository extends JpaRepository<DataLogger, Long> {
    @Query(value = "SELECT * FROM dl_data_logger WHERE activated = false AND activation_key = :activation_key", nativeQuery = true)
    DataLogger findUnactivatedByActivationKey(@Param("activation_key") String activationKey);

    @Query(value = "SELECT * FROM dl_data_logger WHERE id = :id", nativeQuery = true)
    DataLogger findByID(@Param("id") Long id);

    @Query(value = "SELECT * FROM dl_data_logger WHERE user = :user", nativeQuery = true)
    List<DataLogger> findAllByUser(@Param("user") Long user);

    @Query(value = "SELECT * FROM dl_data_logger WHERE `group` = :group", nativeQuery = true)
    List<DataLogger> findAllByGroup(@Param("group") Long group);

    @Query(value = "SELECT * FROM dl_data_logger WHERE id = :id AND user = :user", nativeQuery = true)
    DataLogger findByIDAndUser(@Param("id") Long group, @Param("user") Long user);
}
