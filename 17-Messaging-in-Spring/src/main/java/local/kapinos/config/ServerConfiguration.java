package local.kapinos.config;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

import org.apache.activemq.broker.BrokerService;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
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

import local.kapinos.bean._01.RawJmsServer;
import local.kapinos.bean._02.JmsListenerServer;
import local.kapinos.bean._02.JmsTemplateServer;
import local.kapinos.bean._03.RemotingJmsServer;
import local.kapinos.bean._04.AmpqListenerServer;
import local.kapinos.bean._04.AmqpTemplateServer;
import local.kapinos.common.RemotingJmsService;
import local.kapinos.common.WaitAnyKeyBean;

@Configuration
@EnableJms // for @JmsListener annotation
@EnableRabbit
@Import({CommonJmsConfiguration.class, 
	     CommonAmqpConfiguration.class})
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
	@DependsOn({"embeddedBroker", "connectionFactory"})
	public RawJmsServer rawJmsServer() {
		return new RawJmsServer();
	}

	@Bean(destroyMethod = "stop")
	@DependsOn({"embeddedBroker", "connectionFactory"})
	public JmsTemplateServer jmsTemplateServer() {
		return new JmsTemplateServer();
	}

	@Bean
	@DependsOn("embeddedBroker")
	public JmsListenerServer jmsListenerServer() {
		return new JmsListenerServer();
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
	@DependsOn("amqpConnectionFactory")
	public AmqpTemplateServer amqpTemplateServer(){
		return new AmqpTemplateServer();
	}
	
	@Bean
	@DependsOn("amqpConnectionFactory")
	public AmpqListenerServer ampqListenerServer(){
		return new AmpqListenerServer();
	}
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
    		org.springframework.amqp.rabbit.connection.ConnectionFactory amqpConnectionFactory) {
    	
      SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
      factory.setConnectionFactory(amqpConnectionFactory);
      factory.setMaxConcurrentConsumers(5);
      
      return factory;
    }

	@Bean
	Object waitAnyKeyBean() {
		return new WaitAnyKeyBean();
	}
}
