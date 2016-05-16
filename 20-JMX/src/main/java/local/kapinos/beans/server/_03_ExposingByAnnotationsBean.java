package local.kapinos.beans.server;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Component
@ManagedResource(objectName="myFolder:name=_03_ExposingByAnnotationsBean")
public class _03_ExposingByAnnotationsBean {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private int intValue = 20;
	
	@ManagedAttribute
	public int getIntValue() {
		logger.info("getIntValue()");
		return intValue;
	}
	
	@ManagedAttribute
	public void setIntValue(int intValue) {
		logger.info("setIntValue(" + intValue + ")");
		this.intValue = intValue;
	}

	@PostConstruct
	public void start()  {
		logger.info("Exposing by Annotations Bean started");
	}
	
	@PreDestroy
	public void stop()  {
		logger.info("Exposing by Annotations Bean stopped");
	}	
}
