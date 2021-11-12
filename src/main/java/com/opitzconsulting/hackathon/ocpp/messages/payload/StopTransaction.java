package com.opitzconsulting.hackathon.ocpp.messages.payload;

import java.time.Instant;
import java.util.List;
import com.opitzconsulting.hackathon.ocpp.messages.CallPayload;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StopTransaction implements CallPayload {
	
	String idTag;
	Integer meterStop; // meter reading at time of stop
	Instant timestamp;
	Integer transactionId;
	Reason reason;
	List<MeterValue> transactionData;
	

	
}
