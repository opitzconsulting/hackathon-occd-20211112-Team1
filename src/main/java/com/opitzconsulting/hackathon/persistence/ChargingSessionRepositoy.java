package com.opitzconsulting.hackathon.persistence;

import com.opitzconsulting.hackathon.endpoint.dto.DailyConsumptionDto;
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

    @Query(value = "SELECT EXTRACT(day FROM s.CHARGING_END) as day, EXTRACT(month FROM s.CHARGING_END) as month, "
            + "EXTRACT(year FROM s.CHARGING_END) as year, s.ID_TAG, sum(s.STOP_METER - s.START_METER) as dailyConsumption, "
            + "s.CHARGING_END, s.CHARGING_START FROM CHARGING_SESSION s WHERE CHARGING_END is not null "
            + "AND ID_TAG = :idTag GROUP BY 1,2,3, s.CHARGING_END, s.CHARGING_START, s.ID_TAG, s.ID, s.TRANSACTION_ID;",
            nativeQuery = true)
    List<Object[]> calculateDailyConsumptionPerTagId(String idTag);
}
