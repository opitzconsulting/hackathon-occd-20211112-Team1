package com.opitzconsulting.hackathon.ocpp.messages.payload;

import com.opitzconsulting.hackathon.ocpp.messages.CallResultPayload;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StartTransactionConf implements CallResultPayload {
	
	Integer transactionId;
	IdTagInfo idTagInfo;
	
}
