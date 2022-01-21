package com.opitzconsulting.hackathon.endpoint;

import com.opitzconsulting.hackathon.endpoint.dto.RfidTagDto;
import com.opitzconsulting.hackathon.endpoint.dto.RfidTagMapper;
import com.opitzconsulting.hackathon.persistence.ChargingSession;
import com.opitzconsulting.hackathon.persistence.RfidTag;
import com.opitzconsulting.hackathon.service.GuiService;
import com.opitzconsulting.hackathon.service.NoRfidTagFoundException;
import io.micronaut.data.exceptions.EmptyResultException;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@Controller("/gui/api/v1/consumption")
@RequiredArgsConstructor
public class GuiController {
    private final GuiService guiService;

    @Get(value = "/chargingsessions", produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Returning all charging sessions without any limit.",
            description = "Returns a list of ChargingSession objects.")
    @Tag(name = "chargingsession")
    public HttpResponse<List<ChargingSession>> findAllChargingSessions() {
        return HttpResponse.ok(guiService.findAllChargingSessions());
    }

    @Get(value = "/chargingsessions/transaction/{transactionId}", produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Returning all charging sessions within the specified transaction ID.",
            description = "Returns a list of ChargingSession with the specified transaction ID.")
    @Tag(name = "chargingsession")
    public HttpResponse<List<ChargingSession>> findChargingSessionsByTransaction(@PathVariable Integer transactionId) {
        return HttpResponse.ok(guiService.findChargingSessionsByTransactionId(transactionId));
    }

    @Get(value = "/chargingsessions/tag/{idTag}", produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Returning all charging sessions with the specified ID-Tag.",
            description = "Returns a list of ChargingSession with the specified tag ID of the RFID card.")
    @Tag(name = "chargingsession")
    public HttpResponse<List<ChargingSession>> findChargingSessionsByIdTag(@PathVariable String idTag) {
        return HttpResponse.ok(guiService.findChargingSessionsByIdTag(idTag));
    }

    @Get(value = "/chargingsessions/latestSessionByTagId/", produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Returning all charging sessions by specified ID-Tag and latest chrging end date.",
            description = "Returns a map with idTag as key and ChargingSession as value. The value is the session with the latest charging end date.")
    @Tag(name = "chargingsession")
    public HttpResponse<Map<String, ChargingSession>> findChargingSessionsByIdTag() {
        return HttpResponse.ok(guiService.findNewestChargingSessionsPerTagId());
    }

    @Get(value = "/rfidtag", produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Returning all RFID tags.",
            description = "Returns a list of all RFID tag objects.")
    @Tag(name = "rfidtag")
    public HttpResponse<List<RfidTag>> findAllRfidTags() {
        return HttpResponse.ok(guiService.findAllRfidTags());
    }

    @Get(value = "/rfidtag/{idTag}", produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Returning 1 RFID tag.",
            description = "Returning the RFID tag object with the given tag ID.")
    @Tag(name = "rfidtag")
    public HttpResponse<RfidTag> findAllRfidTags(@PathVariable @RequestBody String idTag) {
        try {
            return HttpResponse.ok(guiService.findRfidTagByIdTag(idTag));
        } catch (EmptyResultException e) {
            return HttpResponse.notFound();
        }
    }

    @Post(value = "/rfidtag", produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new RFID tag.",
            description = "Creates a new RFID tag object and persist it. ID will be set to null!")
    @Tag(name = "rfidtag")
    public HttpResponse<RfidTagDto> createRfidTag(@Body @RequestBody RfidTagDto rfidTagDto) {
        return HttpResponse.ok(RfidTagMapper.mapToDto(guiService.createRfidTag(RfidTagMapper.mapToEntity(rfidTagDto))));
    }

    @Put(value = "/rfidtag", produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Updates an existing RFID tag.",
            description = "Updates an existing RFID tag object identified by ID.")
    @ApiResponse(responseCode = "404", description = "Not found - There was no RFID-Tag with the given ID to update.",
            content = @Content(mediaType = "application/json", schema = @Schema(example = """
                    {
                      "id": 0,
                      "idTag": "string",
                      "comment": "string"
                    }""")))
    @Tag(name = "rfidtag")
    public HttpResponse<RfidTag> updateRfidTag(@Body @RequestBody RfidTag rfidTag) {
        try {
            return HttpResponse.ok(guiService.updateRfidTag(rfidTag));
        } catch (NoRfidTagFoundException e) {
            return HttpResponse.notFound(rfidTag);
        }
    }

    @Delete(value = "/rfidtag/{id}", produces = MediaType.TEXT_PLAIN)
    @Operation(summary = "Deletes an RFID tag.", description = "Deletes an existing RFID tag object by ID.")
    @Tag(name = "rfidtag")
    public HttpResponse<Long> deleteRfidTag(@PathVariable @RequestBody Long id) {
        guiService.deleteRfidTag(id);
        return HttpResponse.ok(id);
    }
}