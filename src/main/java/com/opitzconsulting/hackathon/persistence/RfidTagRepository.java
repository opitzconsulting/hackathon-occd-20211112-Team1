package com.opitzconsulting.hackathon.persistence;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface RfidTagRepository extends JpaRepository<RfidTag, Long> {

    RfidTag findByIdTag(String idTag);
}
