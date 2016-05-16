package local.kapinos.config;

import java.net.MalformedURLException;

import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.access.MBeanProxyFactoryBean;
import org.springframework.jmx.support.MBeanServerConnectionFactoryBean;

import local.kapinos.beans.client._00_ClientBeansMarkerInterface;
import local.kapinos.beans.server._02_ExposingByInterfaceBean;

@Configuration
@ComponentScan(basePackageClasses = _00_ClientBeansMarkerInterface.class)
public class ClientSpringConfiguration {

	@Bean
	public MBeanServerConnectionFactoryBean connectionFactoryBean() throws MalformedURLException {
		MBeanServerConnectionFactoryBean mbscfb = new MBeanServerConnectionFactoryBean();
		mbscfb.setServiceUrl(CommonSpringConfiguration.REMOTE_JMX_SERVICE_URL);
		
		return mbscfb;
	}

	@Bean
	public MBeanProxyFactoryBean remoteSpittleControllerMBean(MBeanServerConnection mbeanServerClient)
			throws MalformedObjectNameException {		
		MBeanProxyFactoryBean proxy = new MBeanProxyFactoryBean();
		proxy.setObjectName(new ObjectName("myFolder:name=_02_ExposingByInterfaceBean"));
		proxy.setServer(mbeanServerClient);
		proxy.setProxyInterface(_02_ExposingByInterfaceBean.class);
		return proxy;
	}
}
