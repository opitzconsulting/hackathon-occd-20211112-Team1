package com.opitzconsulting.hackathon;

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
	public void onMessage(WebSocketSession session, String message) {
		log.info("Receiving message {}", message);
		session.sendSync("[3, \"19223201\", {\"status\":\"Accepted\", \"currentTime\":\"2013-02-01T20:53:32.486Z\", \"heartbeatInterval\": 300}]");
	}
	
	@OnClose
	public void onClose(WebSocketSession session) {
		log.info("Closing session {}", session);
	}
	
}
