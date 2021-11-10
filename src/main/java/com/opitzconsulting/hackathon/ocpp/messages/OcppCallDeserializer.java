package com.opitzconsulting.hackathon.ocpp.messages;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.opitzconsulting.hackathon.ocpp.messages.payload.Authorize;
import com.opitzconsulting.hackathon.ocpp.messages.payload.BootNotification;
import com.opitzconsulting.hackathon.ocpp.messages.payload.StartTransaction;
import com.opitzconsulting.hackathon.ocpp.messages.payload.StopTransaction;

public class OcppCallDeserializer extends StdDeserializer<OcppCall> {
	
	public OcppCallDeserializer() {
		super(OcppCall.class);
	}
	
	protected OcppCallDeserializer(Class<?> vc) {
		super(vc);
	}
	
	@Override
	public OcppCall deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException {
		final OcppCall ocppCall = new OcppCall();
		ArrayNode node = jp.getCodec().readTree(jp);
		final JsonNode messageTypeId = node.get(0);
		ocppCall.setMessageTypeId(messageTypeId.asInt());
		final JsonNode uniqueId = node.get(1);
		ocppCall.setUniqueId(uniqueId.asText());
		final JsonNode action = node.get(2);
		ocppCall.setAction(action.asText());
		final JsonNode payload = node.get(3);
		if ("BootNotification".equals(action.asText())) {
			BootNotification bootNotification = jp.getCodec().treeToValue(payload, BootNotification.class);
			ocppCall.setPayload(bootNotification);
		}
		if ("Authorize".equals(action.asText())) {
			Authorize authorize = jp.getCodec().treeToValue(payload, Authorize.class);
			ocppCall.setPayload(authorize);
		}
		if ("StartTransaction".equals(action.asText())) {
			StartTransaction startTransaction = jp.getCodec().treeToValue(payload, StartTransaction.class);
			ocppCall.setPayload(startTransaction);
		}
		if ("StopTransaction".equals(action.asText())) {
			StopTransaction stop = jp.getCodec().treeToValue(payload, StopTransaction.class);
			ocppCall.setPayload(stop);
		}
		return ocppCall;
	}
	
}
