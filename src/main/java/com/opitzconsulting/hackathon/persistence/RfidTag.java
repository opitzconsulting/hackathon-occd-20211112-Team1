package com.opitzconsulting.hackathon.persistence;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name="RfidTag", description="RfidTag object with information about the accepted RFID cards")
public class RfidTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description="technical database id of the entity", type = "number")
    private Long id;
    @Schema(description="idTag",type = "string")
    @Column(unique = true, nullable = false)
    private String idTag;
    @Schema(description="comment",type = "string")
    private String comment;

}
