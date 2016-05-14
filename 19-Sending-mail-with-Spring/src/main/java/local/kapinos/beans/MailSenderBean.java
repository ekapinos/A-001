package local.kapinos.beans;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class MailSenderBean {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	MailSender mailSender;
	 
	@Value("${sendTo}") // loopback
	String toAddress;
	
	@PostConstruct
	public void start()  {
		logger.info("Raw Mail Client start");
		
		logger.info("Send new message");
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(toAddress);
		message.setSubject("New message from Spring");
		message.setText("Text should be here");
		mailSender.send(message);
		
		logger.info("Raw Mail Client stop");
	}
}
