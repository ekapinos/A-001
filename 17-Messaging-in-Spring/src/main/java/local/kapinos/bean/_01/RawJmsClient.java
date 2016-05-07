package local.kapinos.bean._01;

import javax.annotation.PostConstruct;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class RawJmsClient {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ConnectionFactory cf;
	@Autowired
	private Queue testQueue;

	@PostConstruct
	public void start() throws JMSException {
		logger.info("Raw JMS Client start");

		Connection conn = null;
		Session session = null;
		try {
			
			logger.info(" - Send Hello Queue!");
			
			conn = cf.createConnection();
			session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = testQueue;
			MessageProducer producer = session.createProducer(destination);
			TextMessage message = session.createTextMessage();
			message.setText("Hello Queue!");
			producer.send(message);
		} finally {
			if (session != null) {
				session.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

		logger.info("Raw JMS Client stop");
	}
}
