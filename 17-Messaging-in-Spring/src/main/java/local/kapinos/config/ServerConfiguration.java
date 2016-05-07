package local.kapinos.config;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

import org.apache.activemq.broker.BrokerService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.remoting.JmsInvokerServiceExporter;
import org.springframework.jms.support.destination.BeanFactoryDestinationResolver;

import local.kapinos.bean.JmsListenerServer;
import local.kapinos.bean.JmsTemplateServer;
import local.kapinos.bean.RawJmsServer;
import local.kapinos.bean.RemotingJmsServer;
import local.kapinos.common.RemotingJmsService;
import local.kapinos.common.WaitAnyKeyBean;

@Configuration
@EnableJms // for @JmsListener annotation
@Import(CommonConfiguration.class)
public class ServerConfiguration {

	@Bean(destroyMethod = "stop")
	public BrokerService embeddedBroker() throws Exception {
		BrokerService broker = new BrokerService();
		broker.setPersistent(false);
		broker.addConnector("tcp://localhost:61616");
		broker.start();
		return broker;
	}

	@Bean(destroyMethod = "stop")
	@DependsOn("embeddedBroker")
	public RawJmsServer rawJmsServer() {
		return new RawJmsServer();
	}

	@Bean(destroyMethod = "stop")
	@DependsOn("embeddedBroker")
	public JmsTemplateServer jmsTemplateServer() {
		return new JmsTemplateServer();
	}

	@Bean
	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory cf, BeanFactory bf) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(cf);
		
		// Allows find queues and topics by bean name for @JmsListener annotations
		factory.setDestinationResolver(new BeanFactoryDestinationResolver(bf));

		return factory;
	}
	@Bean
	@DependsOn("embeddedBroker")
	public JmsListenerServer jmsListenerServer() {
		return new JmsListenerServer();
	}
	
	@Bean
	public RemotingJmsServer remotingServerImpl() {
		return new RemotingJmsServer();
	}
	@Bean
	public JmsInvokerServiceExporter jmsInvokerServiceExporter(RemotingJmsServer remotingServer) {
		JmsInvokerServiceExporter exporter = new JmsInvokerServiceExporter();
		exporter.setService(remotingServer);
		exporter.setServiceInterface(RemotingJmsService.class);
		
		return exporter;
	}
	@Bean
	@DependsOn("embeddedBroker")
	public DefaultMessageListenerContainer jmsInvokerServiceExporterContainer(ConnectionFactory connectionFactory,
			@Qualifier("remotingQueue") Queue remotingQueue,
			JmsInvokerServiceExporter jmsInvokerServiceExporter) {
		
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setDestination(remotingQueue);
		container.setMessageListener(jmsInvokerServiceExporter);
		
		return container;
	}
	
	@Bean
	Object waitAnyKeyBean() {
		return new WaitAnyKeyBean();
	}
}
