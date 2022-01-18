package com.opitzconsulting.hackathon.persistence;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.OffsetDateTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name="ChargingSession", description="ChargingSession object with information about one wallbox charging session")
public class ChargingSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description="technical database id of the entity",type = "number")
    private Long id;
    @Schema(description="idTag",type = "string")
    private String idTag;
    @Schema(description="transactionId of the charging session",type = "integer")
    private Integer transactionId;
    @Schema(description="startMeter of the transaction",type = "integer")
    private Integer startMeter;
    @Schema(description="stopMeter of the transaction",type = "integer")
    private Integer stopMeter;
    @Schema(description="consumption of the whole transaction",type = "integer")
    private transient Integer consumption;
    @Schema(description="chargingStart date/time of the transaction",type = "string")
    private OffsetDateTime chargingStart;
    @Schema(description="chargingEnd date/time of the transaction",type = "string")
    private OffsetDateTime chargingEnd;
}
