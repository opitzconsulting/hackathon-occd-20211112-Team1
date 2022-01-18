package com.opitzconsulting.hackathon.endpoint.dto;

import com.opitzconsulting.hackathon.persistence.RfidTag;

public final class RfidTagMapper {

    private RfidTagMapper() {

    }

    public static RfidTagDto mapToDto(RfidTag rfidTag) {
        return RfidTagDto.builder()
                .idTag(rfidTag.getIdTag())
                .comment(rfidTag.getComment())
                .build();
    }

    public static RfidTag mapToEntity(RfidTagDto rfidTagDto) {
        return RfidTag.builder()
                .id(null)
                .idTag(rfidTagDto.getIdTag())
                .comment(rfidTagDto.getComment())
                .build();
    }
}
