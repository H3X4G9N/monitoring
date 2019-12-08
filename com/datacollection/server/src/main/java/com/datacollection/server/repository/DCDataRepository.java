package com.datacollection.server.repository;

import com.datacollection.server.model.DCData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DCDataRepository extends JpaRepository<DCData, Long> {
    @Query(value = "SELECT * FROM dc_data WHERE dc = :dc", nativeQuery = true)
    List<DCData> findAllByDC(@Param("dc") Long dc);
}
