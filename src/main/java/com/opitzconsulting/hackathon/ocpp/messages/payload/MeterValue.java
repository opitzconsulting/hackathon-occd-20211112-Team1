package com.opitzconsulting.hackathon.ocpp.messages.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeterValue {

    Instant timestamp; // timestamp of sampled meter reading
    List<SampledValue> sampledValue; // list of samples

}