package com.opitzconsulting.hackathon.ws;

import com.opitzconsulting.hackathon.ocpp.messages.OcppCall;
import com.opitzconsulting.hackathon.ocpp.messages.OcppMessageFactory;
import com.opitzconsulting.hackathon.ocpp.messages.payload.StartTransaction;
import com.opitzconsulting.hackathon.service.ChargingSessionSingleton;
import io.micronaut.websocket.WebSocketSession;
import io.micronaut.websocket.annotation.OnClose;
import io.micronaut.websocket.annotation.OnMessage;
import io.micronaut.websocket.annotation.OnOpen;
import io.micronaut.websocket.annotation.ServerWebSocket;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ServerWebSocket(value = "/ws/CentralSystemService/{chargeBoxId}", subprotocols = "ocpp1.6")
@Slf4j
@RequiredArgsConstructor
public class OcppServerWebSocket {

	private final ChargingSessionSingleton chargingSessionSingleton;

	@OnOpen
	public void onOpen(String chargeBoxId) {
		log.info("Opening session for charge box {}", chargeBoxId);
	}
	
	@OnMessage
	public void onMessage(WebSocketSession session, OcppCall ocppCall) {
		log.info("Receiving message {}", ocppCall);
		handleOcppCall(session, ocppCall);
	}
	
	private void handleOcppCall(WebSocketSession session, OcppCall ocppCall) {
		if (ocppCall.getAction().equals("BootNotification")) {
			handleBootNotification(session, ocppCall);
		}
		if (ocppCall.getAction().equals("Authorize")) {
			handleAuthorize(session, ocppCall);
		}
		if (ocppCall.getAction().equals("StartTransaction")) {
			handleStartTransaction(session, ocppCall);
		}
		if (ocppCall.getAction().equals("StopTransaction")) {
			handleAuthorize(session, ocppCall);
		}
		if (ocppCall.getAction().equals("MeterValues")) {
			handleMeterValues(session, ocppCall);
		}
	}

	private void handleMeterValues(WebSocketSession session, OcppCall ocppCall) {
		// TODO Implement me
	}

	private void handleStartTransaction(WebSocketSession session, OcppCall ocppCall) {
		session.sendSync(chargingSessionSingleton.createChargingSession((StartTransaction) ocppCall.getPayload()));
	}
	
	private void handleStopTransaction(WebSocketSession session, OcppCall ocppCall) {
		// TODO Implement me
	}
	
	private void handleAuthorize(WebSocketSession session, OcppCall ocppCall) {
		// TODO Implement me
		session.sendSync(OcppMessageFactory.createAuthorizeConf(ocppCall.getUniqueId()));
	}
	
	private void handleBootNotification(WebSocketSession session, OcppCall ocppCall) {
		// reply with BootNotificationConf
		session.sendSync(OcppMessageFactory.createBootNotificationConf(ocppCall.getUniqueId()));
	}
	
	@OnClose
	public void onClose(WebSocketSession session) {
		log.info("Closing session {}", session);
	}
	
}
