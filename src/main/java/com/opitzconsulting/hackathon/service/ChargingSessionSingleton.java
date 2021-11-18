package com.opitzconsulting.hackathon.service;

import com.opitzconsulting.hackathon.ocpp.messages.payload.AuthorizationStatus;
import com.opitzconsulting.hackathon.ocpp.messages.payload.IdTagInfo;
import com.opitzconsulting.hackathon.ocpp.messages.payload.StartTransaction;
import com.opitzconsulting.hackathon.ocpp.messages.payload.StartTransactionConf;
import com.opitzconsulting.hackathon.ocpp.messages.payload.StopTransaction;
import com.opitzconsulting.hackathon.ocpp.messages.payload.StopTransactionConf;
import com.opitzconsulting.hackathon.persistence.ChargingSession;
import com.opitzconsulting.hackathon.persistence.ChargingSessionRepositoy;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;

@Singleton
@RequiredArgsConstructor
public class ChargingSessionSingleton {

    private final ChargingSessionRepositoy chargingSessionRepositoy;

    @Transactional
    public StartTransactionConf createChargingSession(StartTransaction startTransaction) {
        // Create session with transaction id
        Integer transactionId = IdGenerator.generateUniqueId();

        ChargingSession chargingSession = ChargingSession
                .builder()
                .chargingStart(startTransaction.getTimestamp())
                .startMeter(startTransaction.getMeterStart())
                .idTag(startTransaction.getIdTag())
                .transactionId(transactionId)
                .build();

        chargingSessionRepositoy.saveAndFlush(chargingSession);

        return StartTransactionConf
                .builder()
                .transactionId(chargingSession.getTransactionId())
                .idTagInfo(
                        IdTagInfo
                                .builder()
                                .parentIdTag(chargingSession.getIdTag())
                                .expiryDate(OffsetDateTime.now().plusHours(1).toInstant())
                                .status(AuthorizationStatus.Accepted)
                                .build()
                )
                .build();
    }

    @Transactional
    public StopTransactionConf sessionEnd(StopTransaction stopTransaction) {
        String idTag = stopTransaction.getIdTag();
        ChargingSession endingSession = chargingSessionRepositoy.findByIdTag(idTag);
        endingSession.setStopMeter(stopTransaction.getMeterStop());
        endingSession.setChargingEnd(OffsetDateTime.ofInstant(stopTransaction.getTimestamp(), ZoneId.systemDefault()));
        chargingSessionRepositoy.update(endingSession);
        chargingSessionRepositoy.flush();
        return StopTransactionConf.builder()
                .idTagInfo(
                        IdTagInfo.builder()
                                .parentIdTag(idTag)
                                .expiryDate(OffsetDateTime.now().plusHours(1).toInstant())
                                .status(AuthorizationStatus.Accepted)
                                .build())
                .build();
    }
}