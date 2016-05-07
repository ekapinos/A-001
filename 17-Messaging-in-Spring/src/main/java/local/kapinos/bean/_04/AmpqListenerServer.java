package local.kapinos.bean._04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import local.kapinos.common.MessageHolder;

/**
 * @see AmqpTemplateClient
 */
public class AmpqListenerServer {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@RabbitListener(queues="amqpQueue1")
	public void consume(MessageHolder message) {
		logger.info("GOT A MESSAGE: {}", message.getText());
	}
}
