package local.kapinos;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import local.kapinos.config.ClientSpringConfiguration;

public class Main20Client {

	public static void main(String[] args) {
		
		try(ConfigurableApplicationContext ctx = 
				new AnnotationConfigApplicationContext(ClientSpringConfiguration.class)) {
			
		}
	}
}
