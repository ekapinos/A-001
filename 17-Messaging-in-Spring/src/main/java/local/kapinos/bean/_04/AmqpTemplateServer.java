package local.kapinos.bean._04;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import local.kapinos.common.MessageHolder;

public class AmqpTemplateServer {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AmqpTemplate amqpTemplate;
	
	private Thread workThread;

	@PostConstruct
	public void start() throws JMSException {
		logger.info("AmqpTemplate Server start");
		
		workThread = new Thread(() -> {
			while (!workThread.isInterrupted()) {
				try {
					MessageHolder message = (MessageHolder)amqpTemplate.receiveAndConvert();
					if (message != null){
						logger.info("GOT A MESSAGE: {}", message.getText());
					} else {
						Thread.sleep(1000); // 1 sec
					}

				} catch (Exception e) {
					if (e instanceof InterruptedException) {
						break;
					}
					throw new RuntimeException(e);
				}
			}
		}, "amqpTemplate Server work thread");
		workThread.start();

	}

	@PreDestroy
	public void stop() throws JMSException {
		logger.info("AmqpTemplate Server stop");
		
		workThread.interrupt();
	}
}
