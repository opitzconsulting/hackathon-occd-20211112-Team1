package com.opitzconsulting.hackathon.ocpp.messages.payload;

import com.opitzconsulting.hackathon.ocpp.messages.CallPayload;

public class MeterValues implements CallPayload {

    int connectorId;
    int transactionId;
    MeterValue meterValue;

}
