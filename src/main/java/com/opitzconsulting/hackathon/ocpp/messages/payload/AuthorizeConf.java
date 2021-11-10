package com.opitzconsulting.hackathon.ocpp.messages.payload;

import java.time.Instant;
import com.opitzconsulting.hackathon.ocpp.messages.Payload;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizeConf implements Payload {
	
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
		
		ACCEPTED, BLOCKED, EXPIRED, INVALID, CONCURRENTTX;
		
	}
	
}
