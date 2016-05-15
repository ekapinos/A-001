package local.kapinos;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import local.kapinos.config.SpringConfiguration;

public class Main20 {

	public static void main(String[] args) {
		
		try(ConfigurableApplicationContext ctx = 
				new AnnotationConfigApplicationContext(SpringConfiguration.class)) {
			
		}
	}
}
