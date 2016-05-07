package local.kapinos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import local.kapinos.bean.JmsTemplateClient;
import local.kapinos.bean.RawJmsClient;

@Configuration
@Import(CommonConfiguration.class)
public class ClientConfiguration {

	@Bean
	public RawJmsClient rawJmsClient() {
		return new RawJmsClient();
	}
	
	@Bean
	public JmsTemplateClient jmsTemplateClent() {
		return new JmsTemplateClient();
	}
}
