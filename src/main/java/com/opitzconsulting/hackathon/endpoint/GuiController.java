package com.opitzconsulting.hackathon.endpoint;

import com.opitzconsulting.hackathon.persistence.ChargingSession;
import com.opitzconsulting.hackathon.service.GuiService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Controller("/gui/api/v1/consumption")
@RequiredArgsConstructor
public class GuiController {

    private final GuiService guiService;

    @Get(value = "/all", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<List<ChargingSession>> findAllChargingSessions() {
        return HttpResponse.ok(guiService.findAllChargingSessions());
    }
}
