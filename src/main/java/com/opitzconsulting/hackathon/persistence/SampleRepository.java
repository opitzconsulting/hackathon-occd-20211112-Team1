package com.opitzconsulting.hackathon.persistence;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface SampleRepository extends JpaRepository<SampleEntity, Long>{}
