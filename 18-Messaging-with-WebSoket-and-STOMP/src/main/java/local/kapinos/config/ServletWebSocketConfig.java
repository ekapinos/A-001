package local.kapinos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import local.kapinos.controller.MarcoHandler;

@Configuration
@EnableWebSocket
public class ServletWebSocketConfig implements WebSocketConfigurer {

	@Bean
	public MarcoHandler marcoHandler() {
		return new MarcoHandler();
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(marcoHandler(), "/websocket/marco").withSockJS();		
	}
}
