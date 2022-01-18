package com.opitzconsulting.hackathon.endpoint.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(name="RfidTagDto", description="RfidTag DTO object with information about the accepted RFID cards. " +
        "Doesn't containing any technical information.")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RfidTagDto {
    @Schema(description="idTag",type = "string")
    private String idTag;
    @Schema(description="comment",type = "string")
    private String comment;
}
