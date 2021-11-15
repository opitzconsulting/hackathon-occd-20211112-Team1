package com.opitzconsulting.hackathon.ocpp.messages.payload;

import com.opitzconsulting.hackathon.ocpp.messages.CallResultPayload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StopTransactionConf implements CallResultPayload {
	
	IdTagInfo idTagInfo;
}
