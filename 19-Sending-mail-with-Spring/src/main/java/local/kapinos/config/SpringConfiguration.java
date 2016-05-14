package local.kapinos.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import local.kapinos.beans.MailSenderBean;

/**
 * Run with 
 * 
 * -Dusername=piter -Dpassword=piter-pass -DsendTo=piter@gmail.com
 */
@Configuration
@ComponentScan(basePackageClasses = MailSenderBean.class)
public class SpringConfiguration {
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public MailSender mailSender(
			@Value("${username}") String username,
			@Value("${password}") String password) {
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername(username);
		mailSender.setPassword(password);
		
		mailSender.setProtocol("smtp");
		
		Properties properties = new Properties();
		properties.setProperty("mail.debug", "true");

		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.quitwait", "false");
		
		mailSender.setJavaMailProperties(properties);
		
		return mailSender;
	}

}
