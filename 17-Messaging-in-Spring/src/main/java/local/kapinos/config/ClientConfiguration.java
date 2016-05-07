package local.kapinos.config;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.remoting.JmsInvokerProxyFactoryBean;

import local.kapinos.bean.JmsTemplateClient;
import local.kapinos.bean.RawJmsClient;
import local.kapinos.bean.RemotingJmsClient;
import local.kapinos.common.RemotingJmsService;

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
}
