package local.kapinos.bean._02;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.jms.JMSException;
import javax.jms.Topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import local.kapinos.common.MessageHolder;

public class JmsTemplateServer {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private JmsTemplate jmsTemplate;
	@Autowired
	private Topic testTopic;

	private Thread workThread;

	@PostConstruct
	public void start() throws JMSException {
		logger.info("JmsTemplate Server start");
		
		workThread = new Thread(() -> {
			while (!workThread.isInterrupted()) {
				try {
					MessageHolder message = (MessageHolder)jmsTemplate.receiveAndConvert(testTopic);

					logger.info("GOT A MESSAGE: {}", message.getText());

				} catch (Exception e) {
					if (e.getCause() instanceof JMSException && 
						e.getCause().getCause() instanceof InterruptedException) {
						break;
					}
					throw new RuntimeException(e);
				}
			}
		}, "JmsTemplate Server work thread");
		workThread.start();

	}

	@PreDestroy
	public void stop() throws JMSException {
		logger.info("JmsTemplate Server stop");
		
		workThread.interrupt();
	}
}
