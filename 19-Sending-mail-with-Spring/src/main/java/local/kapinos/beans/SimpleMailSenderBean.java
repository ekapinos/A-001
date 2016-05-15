package local.kapinos.beans;

import java.time.LocalDateTime;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class SimpleMailSenderBean {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private JavaMailSender mailSender;
	 
	@Value("${sendTo}")
	private String toAddress;
	
	@PostConstruct
	public void start()  {
		logger.info("Simple Mail Sender start");
		
		logger.info("Send new message to " + toAddress);
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(toAddress);
		message.setSubject("New Simple message from Spring " + LocalDateTime.now());
		message.setText("Simple Text");
		
		mailSender.send(message);
		
		logger.info("Simple Mail Sender stop");
	}
}
