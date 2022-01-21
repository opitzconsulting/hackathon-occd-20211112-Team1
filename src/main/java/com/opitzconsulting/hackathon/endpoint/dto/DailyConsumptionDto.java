package com.opitzconsulting.hackathon.endpoint.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Schema(name="DailyConsumptionDto", description="Daily consumption per tag ID.")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DailyConsumptionDto {
    @JsonIgnore
    private Integer day;
    @JsonIgnore
    private Integer month;
    @JsonIgnore
    private Integer year;
    @Schema(description="idTag",type = "string")
    private String idTag;
    @Schema(description="chargingStart date/time of the transaction",type = "string")
    private OffsetDateTime chargingStart;
    @Schema(description="chargingEnd date/time of the transaction",type = "string")
    private OffsetDateTime chargingEnd;
    @Schema(description="daily summed up consumption.",type = "integer")
    private Integer dailyConsumption;
}
