package local.kapinos.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;

import local.kapinos.common.JmsMessageHolder;

public class JmsListenerServer {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@JmsListener(destination="testTopic")
	public void consume(JmsMessageHolder message) {
		logger.info("GOT A MESSAGE: {}", message.getText());
	}
}
