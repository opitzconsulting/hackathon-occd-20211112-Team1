package com.opitzconsulting.hackathon.ocpp.messages.payload;

import java.time.Instant;
import com.opitzconsulting.hackathon.ocpp.messages.Payload;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BootNotificationConf implements Payload {
	
	Status status;
	Instant currentTime;
	int heartbeatInterval;
	
	public enum Status { ACCEPTED, PENDING, REJECTED }
	
}
