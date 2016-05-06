package local.kapinos;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import local.kapinos.config.ClientConfiguration;

public class Client {

	public static void main(String[] args) {
		
		try(ConfigurableApplicationContext ctx = 
				new AnnotationConfigApplicationContext(ClientConfiguration.class)) {
			
		}
	}
}
