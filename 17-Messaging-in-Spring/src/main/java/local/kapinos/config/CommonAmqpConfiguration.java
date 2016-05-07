package local.kapinos.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonAmqpConfiguration {
		
	@Bean
	public ConnectionFactory amqpConnectionFactory(){
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost"); // Default host:port user:pass
		return connectionFactory;
	}
	
	@Bean
	public Queue amqpQueue(){
		return new Queue("amqpQueue");
	}	
	@Bean
	public Queue amqpQueue1(){
		return new Queue("amqpQueue1");
	}	
	
	@Bean
	public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory, 
			@Qualifier("amqpQueue") Queue amqpQueue, 
			@Qualifier("amqpQueue1") Queue amqpQueue1){
		
		AmqpAdmin amqpAdmin = new RabbitAdmin(connectionFactory);
		amqpAdmin.declareQueue(amqpQueue);
		amqpAdmin.declareQueue(amqpQueue1);
		return amqpAdmin;
	}
	
	@Bean
	public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory,
			@Qualifier("amqpQueue")  Queue amqpQueue){
		RabbitTemplate rabbitTemplate =  new RabbitTemplate(connectionFactory);
		rabbitTemplate.setQueue(amqpQueue.getName());
		rabbitTemplate.setRoutingKey(amqpQueue.getName()); // by default we have direct-exchange, all queues bounded by name
		return rabbitTemplate;
	}
}
