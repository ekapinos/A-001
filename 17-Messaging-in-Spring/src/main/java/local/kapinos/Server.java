package local.kapinos;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import local.kapinos.config.ServerConfiguration;

public class Server {

	public static void main(String[] args) {
		
		try(ConfigurableApplicationContext ctx = 
				new AnnotationConfigApplicationContext(ServerConfiguration.class)) {
			
		}
	}
}
