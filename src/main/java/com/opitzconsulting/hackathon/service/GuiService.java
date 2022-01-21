package com.opitzconsulting.hackathon.service;

import com.opitzconsulting.hackathon.endpoint.dto.DailyConsumptionDto;
import com.opitzconsulting.hackathon.persistence.ChargingSession;
import com.opitzconsulting.hackathon.persistence.ChargingSessionRepositoy;
import com.opitzconsulting.hackathon.persistence.RfidTag;
import com.opitzconsulting.hackathon.persistence.RfidTagRepository;
import io.micronaut.data.exceptions.EmptyResultException;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Singleton
@RequiredArgsConstructor
public class GuiService {
    private final ChargingSessionRepositoy chargingSessionRepositoy;
    private final RfidTagRepository rfidTagRepository;

    public List<ChargingSession> findAllChargingSessions() {
        List<ChargingSession> results = chargingSessionRepositoy.findAll();
        prepareResultsForGui(results);
        return results;
    }

    public List<ChargingSession> findNewestChargingSessionsPerTagId() {
        List<ChargingSession> results = chargingSessionRepositoy.findAll();
        List<ChargingSession> result = new ArrayList<>();
        Set<String> idTags = results.stream()
                .map(ChargingSession::getIdTag)
                .collect(Collectors.toSet());
        for (String idTag : idTags) {
            ChargingSession latestChargingSession = chargingSessionRepositoy.findLatestChargingSessionByIdTag(idTag);
            if (latestChargingSession.getStopMeter() != null) {
                latestChargingSession.setConsumption(latestChargingSession.getStopMeter() - latestChargingSession
                        .getStartMeter());
            } else {
                latestChargingSession.setConsumption(0);
            }
            result.add(latestChargingSession);
        }
        return result;
    }

    public List<DailyConsumptionDto> calculateDailyConsumption(String tagId) {
        List<Object[]> dailyConsumptionDtos = chargingSessionRepositoy.calculateDailyConsumptionPerTagId(tagId);
        List<DailyConsumptionDto> result = new ArrayList<>();
        dailyConsumptionDtos.forEach( dc -> {
            DailyConsumptionDto dailyConsumptionDto = DailyConsumptionDto.builder()
                    .day((Integer) dc[0])
                    .month((Integer) dc[1])
                    .year((Integer) dc[2])
                    .idTag((String) dc[3])
                    .dailyConsumption((dc[4] == null ? BigDecimal.ZERO : (BigInteger) dc[4]).intValue())
                    .chargingEnd(OffsetDateTime.ofInstant(Instant.ofEpochMilli(((Timestamp) dc[5]).getTime()), ZoneId.of("UTC")))
                    .chargingStart(OffsetDateTime.ofInstant(Instant.ofEpochMilli(((Timestamp) dc[6]).getTime()), ZoneId.of("UTC")))
                    .build();
                result.add(dailyConsumptionDto);
                }
        );
        return result;
    }

    public List<ChargingSession> findChargingSessionsByTransactionId(Integer transactionId) {
        List<ChargingSession> results = chargingSessionRepositoy.findByTransactionId(transactionId);
        prepareResultsForGui(results);
        return results;
    }

    public List<ChargingSession> findChargingSessionsByIdTag(String idTag) {
        List<ChargingSession> results = chargingSessionRepositoy.findByIdTag(idTag);
        prepareResultsForGui(results);
        return results;
    }

    public List<RfidTag> findAllRfidTags() {
        return rfidTagRepository.findAll();
    }

    public RfidTag findRfidTagByIdTag(String idTag) throws EmptyResultException {
        return rfidTagRepository.findByIdTag(idTag);
    }

    public RfidTag createRfidTag(RfidTag rfidTag) {
        return rfidTagRepository.save(rfidTag);
    }

    public void deleteRfidTag(Long rfidTagId) {
        rfidTagRepository.deleteById(rfidTagId);
    }

    public RfidTag updateRfidTag(RfidTag rfidTag) throws NoRfidTagFoundException {
        if (rfidTagRepository.findById(rfidTag.getId()).isEmpty()) {
            throw new NoRfidTagFoundException();
        } else {
            return rfidTagRepository.update(rfidTag);
        }
    }

    private void prepareResultsForGui(List<ChargingSession> result) {
        result.forEach(this::prepareResultsForGui);
    }

    private void prepareResultsForGui(ChargingSession cs) {
        if (cs.getStopMeter() == null) {
            cs.setStopMeter(0);
            cs.setConsumption(0);
        } else {
            cs.setConsumption(cs.getStopMeter() - cs.getStartMeter());
        }
    }
}
