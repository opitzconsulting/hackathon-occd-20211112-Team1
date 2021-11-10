package com.opitzconsulting.hackathon.ws;

import com.opitzconsulting.hackathon.ocpp.messages.OcppMessage;
import com.opitzconsulting.hackathon.ocpp.messages.OcppMessageFactory;
import io.micronaut.websocket.WebSocketSession;
import io.micronaut.websocket.annotation.OnClose;
import io.micronaut.websocket.annotation.OnMessage;
import io.micronaut.websocket.annotation.OnOpen;
import io.micronaut.websocket.annotation.ServerWebSocket;
import lombok.extern.slf4j.Slf4j;

@ServerWebSocket(value = "/ws/CentralSystemService/{chargeBoxId}", subprotocols = "ocpp1.6")
@Slf4j
public class OcppServerWebSocket {
	
	@OnOpen
	public void onOpen(String chargeBoxId) {
		log.info("Opening session for charge box {}", chargeBoxId);
	}
	
	@OnMessage
	public void onMessage(WebSocketSession session, OcppMessage ocppCall) {
		log.info("Receiving message {}", ocppCall);
		handleOcppCall(session, ocppCall);
	}
	
	private void handleOcppCall(WebSocketSession session, OcppMessage ocppMessage) {
		if (ocppMessage.getAction().equals("BootNotification")) {
			handleBootNotification(session, ocppMessage);
		}
	}
	
	private void handleBootNotification(WebSocketSession session, OcppMessage ocppMessage) {
		// reply with BootNotificationConf
		session.sendSync(OcppMessageFactory.createBootNotificationConf(ocppMessage.getUniqueId()));
	}
	
	@OnClose
	public void onClose(WebSocketSession session) {
		log.info("Closing session {}", session);
	}
	
}
