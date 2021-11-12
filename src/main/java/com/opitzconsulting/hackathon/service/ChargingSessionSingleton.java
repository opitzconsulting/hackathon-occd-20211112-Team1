package com.opitzconsulting.hackathon.service;

import com.opitzconsulting.hackathon.ocpp.messages.payload.AuthorizationStatus;
import com.opitzconsulting.hackathon.ocpp.messages.payload.IdTagInfo;
import com.opitzconsulting.hackathon.ocpp.messages.payload.StartTransaction;
import com.opitzconsulting.hackathon.ocpp.messages.payload.StartTransactionConf;
import com.opitzconsulting.hackathon.ocpp.messages.payload.StopTransaction;
import com.opitzconsulting.hackathon.persistence.ChargingSession;
import com.opitzconsulting.hackathon.persistence.ChargingSessionRepositoy;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.OffsetDateTime;

@Singleton
@RequiredArgsConstructor
public class ChargingSessionSingleton {

    private final ChargingSessionRepositoy chargingSessionRepositoy;

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

        chargingSessionRepositoy.save(chargingSession);

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
    public void sessionEnd(StopTransaction stopTransaction) {
        // Update session
    }

}
