package com.opitzconsulting.hackathon.ocpp.messages;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import com.opitzconsulting.hackathon.ocpp.messages.payload.AuthorizationStatus;
import com.opitzconsulting.hackathon.ocpp.messages.payload.AuthorizeConf;
import com.opitzconsulting.hackathon.ocpp.messages.payload.BootNotificationConf;
import com.opitzconsulting.hackathon.ocpp.messages.payload.BootNotificationConf.Status;
import com.opitzconsulting.hackathon.ocpp.messages.payload.IdTagInfo;
import com.opitzconsulting.hackathon.ocpp.messages.payload.MeterValuesConf;
import jakarta.inject.Singleton;

@Singleton
public abstract class OcppMessageFactory {
	
	public static final int CALL_RESULT = 3;
	
	public static OcppCallResult createBootNotificationConf(String uniqueId) {
		return new OcppCallResult(CALL_RESULT, uniqueId, new BootNotificationConf(Status.Accepted, Instant.now(), 300));
	}
	
	public static OcppCallResult createAuthorizeConf(String uniqueId) {
		return new OcppCallResult(CALL_RESULT, uniqueId, new AuthorizeConf(new IdTagInfo(Instant.now().plus(30, ChronoUnit.MINUTES), "abcd4711", AuthorizationStatus.Accepted)));
	}

	public static OcppCallResult createMeterValuesConf(String uniqueId) {
		return new OcppCallResult(CALL_RESULT, uniqueId, new MeterValuesConf());
	}
	
}
