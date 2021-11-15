package com.opitzconsulting.hackathon.persistence;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface ChargingSessionRepositoy extends JpaRepository<ChargingSession, Long> {

    ChargingSession findByIdTag(String idTag);
}
