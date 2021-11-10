package com.opitzconsulting.hackathon.ocpp.messages;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonFormat(shape = Shape.ARRAY)
@JsonDeserialize(using = OcppCallDeserializer.class)
public class OcppMessage {
	
	int messageTypeId;
	String uniqueId;
	String action;
	Payload payload;
	
}
