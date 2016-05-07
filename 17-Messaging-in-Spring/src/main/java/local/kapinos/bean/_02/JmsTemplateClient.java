package local.kapinos.bean._02;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.Topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import local.kapinos.common.MessageHolder;

public class JmsTemplateClient {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private JmsTemplate jmsTemplate;
	@Autowired
	private Topic testTopic;
	
	@PostConstruct
	public void start() throws JMSException {
		logger.info("JmsTemplate JMS Client start");
		
		logger.info("  - Send Hello Topic!");

		jmsTemplate.convertAndSend(testTopic, new MessageHolder("Hello Topic!"));

		logger.info("JmsTemplate JMS Client stop");
	}

}
