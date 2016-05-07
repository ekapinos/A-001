package local.kapinos.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;

public class JmsListenerServer {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@JmsListener(destination="testTopic")
	public void consume(JmsTemplateMessage message) {
		logger.info("GOT A MESSAGE: {}", message.getText());
	}
}
