package com.opitzconsulting.hackathon.ocpp.messages.payload;

import java.time.Instant;
import com.fasterxml.jackson.annotation.JsonValue;
import com.opitzconsulting.hackathon.ocpp.messages.CallResultPayload;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizeConf implements CallResultPayload {
	
	IdTagInfo idTagInfo;
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class IdTagInfo {
		
		Instant expiryDate;
		String parentIdTag;
		AuthorizationStatus status;
	
	}
	
	public enum AuthorizationStatus {
		
		Accepted, Blocked, Expired, Invalid, ConcurrentTx;
		
	}
	
}
