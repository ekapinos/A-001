package local.kapinos.beans.client;

import javax.annotation.PostConstruct;
import javax.management.Attribute;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class _01_RawRemoteJmxClient {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MBeanServerConnection mbeanServerConnection;

	@PostConstruct
	public void start() throws Exception {
		logger.info("Raw Remote JMX Client start");

		mbeanServerConnection.queryNames(null, null)
		                     .forEach(name -> logger.info(" - " + name));
		logger.info("");
		
		int intValue = (Integer) mbeanServerConnection.getAttribute(
				new ObjectName("myFolder:name=_03_ExposingByAnnotationsBean"), "IntValue");
		logger.info("GET _03_ExposingByAnnotationsBean.intValue=" + intValue);
				
		mbeanServerConnection.setAttribute(
				new ObjectName("myFolder:name=_03_ExposingByAnnotationsBean"), 
				new Attribute("IntValue", intValue));
		logger.info("SET _03_ExposingByAnnotationsBean.intValue=" + ++intValue);
		
		logger.info("Raw Remote JMX Client stop");
	}
}
