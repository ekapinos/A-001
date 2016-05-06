package local.kapinos.config;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import local.kapinos.common.MySpringCtxRefreshRunner;

@Configuration
@Import(CommonConfiguration.class)
public class ClientConfiguration {

	@Bean
	public MySpringCtxRefreshRunner rawJmsClient(ConnectionFactory cf, Queue testQueue) {
		return new MySpringCtxRefreshRunner(() -> {
			System.out.println("Raw JMS Client start");

			Connection conn = null;
			Session session = null;
			try {
				
				System.out.println(" - Send Hello world!");
				conn = cf.createConnection();
				session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
				Destination destination = testQueue;
				MessageProducer producer = session.createProducer(destination);
				TextMessage message = session.createTextMessage();
				message.setText("Hello world!");
				producer.send(message);
			} finally {
				if (session != null) {
					session.close();
				}
				if (conn != null) {
					conn.close();
				}
			}

			System.out.println("Raw JMS Client stop");
		});
	}

}
