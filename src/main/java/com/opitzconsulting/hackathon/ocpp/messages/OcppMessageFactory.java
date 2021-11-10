package com.opitzconsulting.hackathon.ocpp.messages;

import java.time.Instant;
import com.opitzconsulting.hackathon.ocpp.messages.payload.BootNotificationConf;
import com.opitzconsulting.hackathon.ocpp.messages.payload.BootNotificationConf.Status;
import jakarta.inject.Singleton;

@Singleton
public abstract class OcppMessageFactory {
	
	public static final int CALL_RESULT = 3;
	
	public static OcppMessage createBootNotificationConf(String uniqueId) {
		return new OcppMessage(CALL_RESULT, uniqueId, "BootNotification", new BootNotificationConf(Status.ACCEPTED, Instant.now(), 300));
	}
	
}
