package com.opitzconsulting.hackathon.ocpp.messages.payload;

import java.time.Instant;
import com.opitzconsulting.hackathon.ocpp.messages.CallResultPayload;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BootNotificationConf implements CallResultPayload {
	
	Status status;
	Instant currentTime;
	int interval;
	
	public enum Status {
		Accepted,
		Pending,
		Rejected
	}
	
}
