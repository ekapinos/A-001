package local.kapinos.config;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.remoting.JmsInvokerProxyFactoryBean;

import local.kapinos.bean._01.RawJmsClient;
import local.kapinos.bean._02.JmsTemplateClient;
import local.kapinos.bean._03.RemotingJmsClient;
import local.kapinos.bean._04.AmqpTemplateClient;
import local.kapinos.common.RemotingJmsService;

@Configuration
@Import({CommonJmsConfiguration.class, 
         CommonAmqpConfiguration.class})
public class ClientConfiguration {

	@Bean
	public RawJmsClient rawJmsClient() {
		return new RawJmsClient();
	}
	
	@Bean
	public JmsTemplateClient jmsTemplateClent() {
		return new JmsTemplateClient();
	}
	
	@Bean
	public JmsInvokerProxyFactoryBean jmsInvokerProxyFactoryBean(ConnectionFactory connectionFactory,
			@Qualifier("remotingQueue") Queue remotingQueue) {
		
		JmsInvokerProxyFactoryBean factory = new JmsInvokerProxyFactoryBean();
		factory.setConnectionFactory(connectionFactory);
		factory.setQueue(remotingQueue);
		factory.setServiceInterface(RemotingJmsService.class);
		return factory;
	}
	@Bean
	public RemotingJmsClient remotingJmsClient() {
		return new RemotingJmsClient();
	}
	
	@Bean
	public AmqpTemplateClient amqpTemplateClient(){
		return new AmqpTemplateClient();
	}
}
