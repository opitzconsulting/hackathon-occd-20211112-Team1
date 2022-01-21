package com.opitzconsulting.hackathon.persistence;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface ChargingSessionRepositoy extends JpaRepository<ChargingSession, Long> {

    List<ChargingSession> findByIdTag(String idTag);

    ChargingSession findByIdTagAndTransactionId(String idTag, Integer transactionId);

    List<ChargingSession> findByTransactionId(Integer transactionId);

    @Query(value = "SELECT * FROM CHARGING_SESSION WHERE ID_TAG = :idTag ORDER BY CHARGING_END DESC LIMIT 1",
            nativeQuery = true)
    ChargingSession findLatestChargingSessionByIdTag(String idTag);
}
