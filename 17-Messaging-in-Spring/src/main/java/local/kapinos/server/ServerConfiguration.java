package local.kapinos.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import local.kapinos.common.WaitAnyKeyBean;

@Configuration
public class ServerConfiguration {
	@Bean
	Object waitAnyKeyBean(){
		return new WaitAnyKeyBean();
	} 
}
