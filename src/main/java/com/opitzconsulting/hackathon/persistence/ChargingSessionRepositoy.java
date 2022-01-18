package com.opitzconsulting.hackathon.persistence;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface ChargingSessionRepositoy extends JpaRepository<ChargingSession, Long> {

    List<ChargingSession> findByIdTag(String idTag);

    ChargingSession findByIdTagAndTransactionId(String idTag, Integer transactionId);

    List<ChargingSession> findByTransactionId(Integer transactionId);
}
