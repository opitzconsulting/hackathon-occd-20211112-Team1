package com.opitzconsulting.hackathon.service;

import com.opitzconsulting.hackathon.persistence.ChargingSession;
import com.opitzconsulting.hackathon.persistence.ChargingSessionRepositoy;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Singleton
@RequiredArgsConstructor
public class GuiService {
    private final ChargingSessionRepositoy chargingSessionRepositoy;

    public List<ChargingSession> findAllChargingSessions() {
        List<ChargingSession> result = chargingSessionRepositoy.findAll();
        result.forEach(cs -> {
            if (cs.getStopMeter() == null) {
                cs.setStopMeter(0);
                cs.setConsumption(0);
            } else {
                cs.setConsumption(cs.getStopMeter() - cs.getStartMeter());
            }
        });
        return result;
    }
}
