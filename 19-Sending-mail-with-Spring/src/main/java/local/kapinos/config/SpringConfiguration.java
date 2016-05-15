package local.kapinos.config;

import java.util.Properties;
import java.util.Set;

import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import local.kapinos.beans._00_BeansMarkerInterface;

/**
 * Run with
 * 
 * -Dusername=piter -Dpassword=piter-pass -DsendTo=piter@gmail.com
 */
@Configuration
@ComponentScan(basePackageClasses = _00_BeansMarkerInterface.class)
public class SpringConfiguration {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public JavaMailSender mailSender(@Value("${username}") String username, @Value("${password}") String password) {

		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername(username);
		mailSender.setPassword(password);

		mailSender.setProtocol("smtp");

		Properties properties = new Properties();
		// properties.setProperty("mail.debug", "true");

		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.quitwait", "false");

		mailSender.setJavaMailProperties(properties);

		return mailSender;
	}

	@Bean
	public VelocityEngineFactoryBean velocityEngine() {
		VelocityEngineFactoryBean velocityEngine = new VelocityEngineFactoryBean();
		Properties props = new Properties();
		props.setProperty("resource.loader", "class");
		props.setProperty("class.resource.loader.class", ClasspathResourceLoader.class.getName());
		velocityEngine.setVelocityProperties(props);
		return velocityEngine;
	}

	@Bean
	public ClassLoaderTemplateResolver thymeleafTemplateResolver() {
		ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
		resolver.setPrefix("/");
		resolver.setTemplateMode("HTML");
		resolver.setCharacterEncoding("UTF-8");
		return resolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine(Set<ITemplateResolver> resolvers) {
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setTemplateResolvers(resolvers);
		return engine;
	}
}
