package local.kapinos.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonSpringConfiguration {

	public static final String REMOTE_JMX_SERVICE_URL = 
			"service:jmx:rmi://localhost/jndi/rmi://localhost:1099/spitter";
	        // "service:jmx:jmxmp://localhost:9875" <- ConnectorServerFactoryBean.DEFAULT_SERVICE_URL 
	
}
