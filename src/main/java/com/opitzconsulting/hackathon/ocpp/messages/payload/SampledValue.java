package com.opitzconsulting.hackathon.ocpp.messages.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SampledValue {

    String value; // default: raw meter reading in 'Wh'

}