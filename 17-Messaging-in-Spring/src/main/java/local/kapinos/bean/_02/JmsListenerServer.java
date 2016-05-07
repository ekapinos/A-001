package local.kapinos.bean._02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;

import local.kapinos.common.MessageHolder;

/**
 * @see JmsTemplateClient
 */
public class JmsListenerServer {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@JmsListener(destination="testTopic")
	public void consume(MessageHolder message) {
		logger.info("GOT A MESSAGE: {}", message.getText());
	}
}
