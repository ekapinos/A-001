package local.kapinos.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import local.kapinos.controller.RawWebSocketHandler;

@Configuration
@EnableWebSocket
public class RawWebSocketConfig implements WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(new RawWebSocketHandler(), "/raw-web-socket/marco");	
	}
}
