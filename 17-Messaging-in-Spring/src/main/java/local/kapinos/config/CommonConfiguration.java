package local.kapinos.config;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfiguration {

	@Bean
	public ConnectionFactory connectionFactory() throws Exception{
		ActiveMQConnectionFactory connectionFactory =  new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL("tcp://localhost:61616");
		return connectionFactory;
	}
	
	@Bean
	public Queue testQueue(){
		return new ActiveMQQueue("testQueue");
	}
	
	@Bean
	public Topic testTopic(){
		return new ActiveMQTopic("testTopic");
	}
}
