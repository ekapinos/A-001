package local.kapinos.bean;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.Topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

public class JmsTemplateClient {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private JmsTemplate jmsTemplate;
	@Autowired
	private Topic testTopic;
	
	@PostConstruct
	public void start() throws JMSException {
		logger.info("JmsTemplate JMS Client start");
		
		logger.info("  - Send Hello jmsTemplate!");

		jmsTemplate.send(testTopic, session -> {
			return session.createTextMessage("Hello jmsTemplate!");
		});

		logger.info("JmsTemplate JMS Client stop");
	}

}
