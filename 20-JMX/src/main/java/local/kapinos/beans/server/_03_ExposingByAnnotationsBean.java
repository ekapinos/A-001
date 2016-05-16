package local.kapinos.beans.server;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.management.Notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedNotification;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.jmx.export.notification.NotificationPublisher;
import org.springframework.jmx.export.notification.NotificationPublisherAware;
import org.springframework.stereotype.Component;

@Component
@ManagedResource(objectName="myFolder:name=_03_ExposingByAnnotationsBean")
@ManagedNotification(
		notificationTypes="SpittleNotifier.OneMillionSpittles", 
		name = "Some name")
public class _03_ExposingByAnnotationsBean implements NotificationPublisherAware /*allows send notifications*/{

	private Logger logger = LoggerFactory.getLogger(getClass());

	private int intValue = 20;
	
	private NotificationPublisher notificationPublisher;

	@ManagedAttribute
	public int getIntValue() {
		logger.info("getIntValue()");
		return intValue;
	}
	
	@ManagedAttribute
	public void setIntValue(int intValue) {
		logger.info("setIntValue(" + intValue + ")");
		this.intValue = intValue;
		
		notificationPublisher.sendNotification(
			new Notification(
				"SpittleNotifier.OneMillionSpittles", 
				this, 
				0,
				"message new value " + intValue));
	}

	@PostConstruct
	public void start()  {
		logger.info("Exposing by Annotations Bean started");
	}
	
	@PreDestroy
	public void stop()  {
		logger.info("Exposing by Annotations Bean stopped");
	}

	@Override
	public void setNotificationPublisher(NotificationPublisher notificationPublisher) {
		this.notificationPublisher = notificationPublisher;
	}	
}
