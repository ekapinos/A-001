package local.kapinos.beans.client;

import javax.annotation.PostConstruct;
import javax.management.Attribute;
import javax.management.MBeanServerConnection;
import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.ObjectName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class _03_JmxListenerClient implements NotificationListener {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MBeanServerConnection mbeanServerConnection;
	
	@PostConstruct
	public void start() throws Exception {
		logger.info("JMX Listener Client start");
		
		ObjectName objectName = new ObjectName("myFolder:name=_03_ExposingByAnnotationsBean");
		
		mbeanServerConnection.addNotificationListener(objectName, this, null, null);
		
		mbeanServerConnection.setAttribute(objectName, new Attribute("IntValue", 5));
		
		Thread.sleep(1000);
		
		mbeanServerConnection.removeNotificationListener(objectName, this);
		
		logger.info("JMX Listener Client stop");
	}

	@Override
	public void handleNotification(Notification notification, Object handback) {
		logger.info(" - notification: "  + notification);
	}
}
