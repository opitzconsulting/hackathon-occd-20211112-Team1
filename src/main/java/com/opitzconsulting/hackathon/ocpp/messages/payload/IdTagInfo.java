package com.opitzconsulting.hackathon.ocpp.messages.payload;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IdTagInfo {
	
	Instant expiryDate;
	String parentIdTag;
	AuthorizationStatus status;
	
}