package local.kapinos;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import local.kapinos.server.ServerConfiguration;

public class Server {

	public static void main(String[] args) {
		
		try(ConfigurableApplicationContext applicationContext = 
				new AnnotationConfigApplicationContext(ServerConfiguration.class)) {
			
		}
	}
}
