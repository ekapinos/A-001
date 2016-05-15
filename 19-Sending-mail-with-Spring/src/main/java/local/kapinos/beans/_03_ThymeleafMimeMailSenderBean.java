package local.kapinos.beans;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

@Component
public class _03_ThymeleafMimeMailSenderBean {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private JavaMailSender mailSender;
	 
	@Value("${sendTo}")
	private String toAddress;
	
	@Autowired
	private SpringTemplateEngine thymeleaf;
	
	@PostConstruct
	public void start() throws MessagingException, IOException  {
		logger.info("Thymeleaf Mime Mail Sender start");
		
		logger.info("Send new message to " + toAddress);
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setTo(toAddress);
		helper.setSubject("New Thymeleaf Mime message from Spring " + LocalDateTime.now());
		
		Map<String, Object> model = new HashMap<>();
		model.put("header", "Text start");
		model.put("inlineimage", "coupon.jpg");
		model.put("footer", "Text end");
		
		Context ctx = new Context();
		ctx.setVariable("header", "Text start");
		ctx.setVariable("inlineimage", "coupon.jpg");
		ctx.setVariable("footer", "Text end");
		String emailText = thymeleaf.process("ThymeleafEmailTemplate.html", ctx);

		helper.setText(emailText, true);
		
		InputStreamSource couponImage = new ClassPathResource("coupon.jpg");
		helper.addInline("coupon.jpg", couponImage, "image/jpg");	
		
		InputStreamSource attachmentImage = new ClassPathResource("attachment.jpg");
		helper.addAttachment("attachment.png", attachmentImage);	
		
		mailSender.send(message);
		
		logger.info("Thymeleaf Mime Mail Sender stop");
	}
}
