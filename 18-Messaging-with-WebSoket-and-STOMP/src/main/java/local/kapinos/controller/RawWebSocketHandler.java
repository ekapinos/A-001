package local.kapinos.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

public class RawWebSocketHandler extends AbstractWebSocketHandler
{
	private static final Logger logger = LoggerFactory.getLogger(RawWebSocketHandler.class);

	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		logger.info("Received Raw message: " + message.getPayload());
		Thread.sleep(2000);
		session.sendMessage(new TextMessage("Polo!"));
	}

}
