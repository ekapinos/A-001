package local.kapinos.beans.server;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class _01_ExposingByMethodNamesBean {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private int intValue = 20;
	
	public int getIntValue() {
		logger.info("getIntValue()");
		return intValue;
	}

	public void setIntValue(int intValue) {
		logger.info("setIntValue(" + intValue + ")");
		this.intValue = intValue;
	}

	@PostConstruct
	public void start()  {
		logger.info("Exposing by method names Bean started");
	}
	
	@PreDestroy
	public void stop()  {
		logger.info("Exposing by method names Bean stopped");
	}	
}
