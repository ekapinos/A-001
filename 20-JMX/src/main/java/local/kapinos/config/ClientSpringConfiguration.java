package local.kapinos.config;

import java.net.MalformedURLException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.support.MBeanServerConnectionFactoryBean;

import local.kapinos.beans.client._00_ClientBeansMarkerInterface;

@Configuration
@ComponentScan(basePackageClasses = _00_ClientBeansMarkerInterface.class)
public class ClientSpringConfiguration {

	@Bean
	public MBeanServerConnectionFactoryBean connectionFactoryBean() throws MalformedURLException {
		MBeanServerConnectionFactoryBean mbscfb = new MBeanServerConnectionFactoryBean();
		mbscfb.setServiceUrl(CommonSpringConfiguration.REMOTE_JMX_SERVICE_URL);
		return mbscfb;
	}
}
