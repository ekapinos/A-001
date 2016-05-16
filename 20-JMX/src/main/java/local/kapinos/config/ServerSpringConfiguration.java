package local.kapinos.config;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.jmx.export.annotation.AnnotationJmxAttributeSource;
import org.springframework.jmx.export.assembler.InterfaceBasedMBeanInfoAssembler;
import org.springframework.jmx.export.assembler.MetadataMBeanInfoAssembler;
import org.springframework.jmx.export.assembler.MethodNameBasedMBeanInfoAssembler;
import org.springframework.jmx.support.ConnectorServerFactoryBean;
import org.springframework.jmx.support.MBeanServerConnectionFactoryBean;
import org.springframework.remoting.rmi.RmiRegistryFactoryBean;

import local.kapinos.beans.server._00_ServerBeansMarkerInterface;
import local.kapinos.beans.server._01_ExposingByMethodNamesBean;
import local.kapinos.beans.server._02_ExposingByInterfaceBean;
import local.kapinos.beans.server._03_ExposingByAnnotationsBean;
import local.kapinos.common.WaitAnyKeyBean;

@Configuration
@ComponentScan(basePackageClasses = _00_ServerBeansMarkerInterface.class)
public class ServerSpringConfiguration {

	private Logger logger = LoggerFactory.getLogger(ServerSpringConfiguration.class);

	@Bean
	public MBeanExporter methodNameMbeanExporter(_01_ExposingByMethodNamesBean myBean) {
		MethodNameBasedMBeanInfoAssembler assembler = new MethodNameBasedMBeanInfoAssembler();
		assembler.setManagedMethods(new String[] { "getIntValue", "setIntValue" });

		MBeanExporter exporter = new MBeanExporter();
		Map<String, Object> beans = new HashMap<String, Object>();
		beans.put("myFolder:name=_01_ExposingByMethodNamesBean", myBean);
		exporter.setBeans(beans);
		exporter.setAssembler(assembler);
		return exporter;
	}

	@Bean
	public MBeanExporter interfaceMbeanExporter(_02_ExposingByInterfaceBean myBean) {
		InterfaceBasedMBeanInfoAssembler assembler = new InterfaceBasedMBeanInfoAssembler();
		assembler.setManagedInterfaces(new Class<?>[] { _02_ExposingByInterfaceBean.class });

		MBeanExporter exporter = new MBeanExporter();
		Map<String, Object> beans = new HashMap<String, Object>();
		beans.put("myFolder:name=_02_ExposingByInterfaceBean", myBean);
		exporter.setBeans(beans);
		exporter.setAssembler(assembler);
		return exporter;
	}

	@Bean
	public MBeanExporter annotationMbeanExporter(_03_ExposingByAnnotationsBean myBean) {
		MetadataMBeanInfoAssembler assembler = new MetadataMBeanInfoAssembler();
		assembler.setAttributeSource(new AnnotationJmxAttributeSource());

		MBeanExporter exporter = new MBeanExporter();
		Map<String, Object> beans = new HashMap<String, Object>();
		beans.put("myFolder:name=_03_ExposingByAnnotationsBean", myBean);
		exporter.setBeans(beans);
		exporter.setAssembler(assembler);
		return exporter;
	}

	// Expose Remote JMX 

	@Bean
	public RmiRegistryFactoryBean rmiRegistryFB() {
		RmiRegistryFactoryBean rmiRegistryFB = new RmiRegistryFactoryBean();
		rmiRegistryFB.setPort(1099);
		return rmiRegistryFB;
	}
	@Bean
	@DependsOn("rmiRegistryFB")
	public ConnectorServerFactoryBean connectorServerFactoryBean() {
		ConnectorServerFactoryBean connectorServerFactoryBean = new ConnectorServerFactoryBean();
		connectorServerFactoryBean.setServiceUrl(CommonSpringConfiguration.REMOTE_JMX_SERVICE_URL);
		logger.info(CommonSpringConfiguration.REMOTE_JMX_SERVICE_URL);
		return connectorServerFactoryBean;
	}

	// Connect to Remote JMX 
	
	@Bean
	@DependsOn("connectorServerFactoryBean")
	public MBeanServerConnectionFactoryBean connectionFactoryBean() throws MalformedURLException {
		MBeanServerConnectionFactoryBean mbscfb = new MBeanServerConnectionFactoryBean();
		mbscfb.setServiceUrl(CommonSpringConfiguration.REMOTE_JMX_SERVICE_URL);
		return mbscfb;
	}

	@Bean
	Object waitAnyKeyBean() {
		return new WaitAnyKeyBean();
	}
}
