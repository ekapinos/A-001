package local.kapinos.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import local.kapinos.controller.SockJsHandler;

@Configuration
@EnableWebSocket
public class SockJsConfig implements WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {	
		registry.addHandler(new SockJsHandler(), "/sockjs/marco").withSockJS();		
	}
}
