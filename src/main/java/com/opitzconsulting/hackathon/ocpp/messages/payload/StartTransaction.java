package com.opitzconsulting.hackathon.ocpp.messages.payload;

import com.opitzconsulting.hackathon.ocpp.messages.CallPayload;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StartTransaction implements CallPayload {

	String idTag;
	Integer meterStart;
	Integer reservationId;
	OffsetDateTime timestamp;
	
}
