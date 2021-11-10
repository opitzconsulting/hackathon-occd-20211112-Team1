package com.opitzconsulting.hackathon.ocpp.messages;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import com.opitzconsulting.hackathon.ocpp.messages.payload.Authorize;
import com.opitzconsulting.hackathon.ocpp.messages.payload.AuthorizeConf;
import com.opitzconsulting.hackathon.ocpp.messages.payload.AuthorizeConf.AuthorizationStatus;
import com.opitzconsulting.hackathon.ocpp.messages.payload.AuthorizeConf.IdTagInfo;
import com.opitzconsulting.hackathon.ocpp.messages.payload.BootNotificationConf;
import com.opitzconsulting.hackathon.ocpp.messages.payload.BootNotificationConf.Status;
import jakarta.inject.Singleton;

@Singleton
public abstract class OcppMessageFactory {
	
	public static final int CALL_RESULT = 3;
	
	public static OcppMessage createBootNotificationConf(String uniqueId) {
		return new OcppMessage(CALL_RESULT, uniqueId, "BootNotification", new BootNotificationConf(Status.ACCEPTED, Instant.now(), 300));
	}
	
	public static OcppMessage createAuthorizeConf(String uniqueId) {
		return new OcppMessage(CALL_RESULT, uniqueId, "Authorize", new AuthorizeConf(new IdTagInfo(Instant.now().plus(30, ChronoUnit.MINUTES), "abcd4711", AuthorizationStatus.ACCEPTED)));
	}
	
}
