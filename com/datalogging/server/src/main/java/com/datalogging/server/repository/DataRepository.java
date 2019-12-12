package com.datalogging.server.repository;

import com.datalogging.server.model.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataRepository extends JpaRepository<Data, Long> {
    @Query(value = "SELECT * FROM dl_data WHERE data_logger = :data_logger", nativeQuery = true)
    List<Data> findAllByDataLogger(@Param("data_logger") Long dataLogger);

    @Query(value = "SELECT * FROM dl_data WHERE data_logger = :data_logger AND", nativeQuery = true)
    List<Data> findAll(@Param("data_logger") Long dataLogger);
}
