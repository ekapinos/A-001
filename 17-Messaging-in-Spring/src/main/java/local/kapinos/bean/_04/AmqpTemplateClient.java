package local.kapinos.bean._04;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import local.kapinos.common.MessageHolder;

public class AmqpTemplateClient {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@PostConstruct
	public void start() throws JMSException {
		logger.info("AmqpTemplate JMS Client start");
		
		logger.info("  - Send Hello AmqpTemplate!");

		amqpTemplate.convertAndSend(new MessageHolder("Hello AmqpTemplate!"));
		amqpTemplate.convertAndSend("amqpQueue1" , new MessageHolder("Hello AmqpTemplate1!"));

		logger.info("AmqpTemplate JMS Client stop");
	}
}
