package com.opitzconsulting.hackathon.ocpp.messages.payload;

import com.opitzconsulting.hackathon.ocpp.messages.CallResultPayload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StartTransactionConf implements CallResultPayload {
	
	Integer transactionId;
	IdTagInfo idTagInfo;
	
}
