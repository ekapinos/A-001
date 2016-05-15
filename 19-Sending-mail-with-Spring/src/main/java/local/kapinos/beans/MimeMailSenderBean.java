package local.kapinos.beans;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MimeMailSenderBean {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private JavaMailSender mailSender;
	 
	@Value("${sendTo}")
	private String toAddress;
	
	@PostConstruct
	public void start() throws MessagingException, IOException  {
		logger.info("Mime Mail Sender start");
		
		logger.info("Send new message to " + toAddress);
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setTo(toAddress);
		helper.setSubject("New Mime message from Spring " + LocalDateTime.now());
		helper.setText("Mime Text");
		
		InputStreamSource couponImage = new ClassPathResource("coupon.jpg");
		helper.addAttachment("Coupon.png", couponImage);	
		
		mailSender.send(message);
		
		logger.info("Mime Mail Sender stop");
	}
}
