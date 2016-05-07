package local.kapinos.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class RawJmsServer {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ConnectionFactory cf;
	@Autowired
	private Queue testQueue;

	private Connection conn = null;
	private Session session = null;
	private Thread workThread;

	@PostConstruct
	public void start() throws JMSException {
		logger.info("Raw JMS Server start");

		conn = cf.createConnection();
		session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination destination = testQueue;
		MessageConsumer consumer = session.createConsumer(destination);
		conn.start();
		
		workThread = new Thread(() -> {
			while (!workThread.isInterrupted()) {
				try {

					Message message = consumer.receive();

					TextMessage textMessage = (TextMessage) message;
					
					if (message == null){
						logger.info("GOT A NULL MESSAGE"); // Appears on shutdown
					}
					else {
						logger.info("GOT A MESSAGE: {}", textMessage.getText());
					}

				} catch (Exception e) {
					if (e.getCause() instanceof InterruptedException) {
						break;
					}
					throw new RuntimeException(e);
				}
			}
		}, "Raw JMS Server work thread");
		workThread.start();

	}

	@PreDestroy
	public void stop() throws JMSException {
		logger.info("Raw JMS Server stop");

		workThread.interrupt();

		if (session != null) {
			session.close();
		}
		if (conn != null) {
			conn.close();
		}
	}
}
