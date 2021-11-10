package com.opitzconsulting.hackathon.ocpp.messages;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonFormat(shape = Shape.ARRAY)
public class OcppCallResult {
	
	int messageTypeId;
	String uniqueId;
	CallResultPayload payload;
	
}
