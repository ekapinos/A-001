package local.kapinos.beans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class _02_ExposingByInterfaceBeanImpl implements _02_ExposingByInterfaceBean {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private int intValue = 20;
	
	@Override
	public int getIntValue() {
		logger.info("getIntValue()");
		return intValue;
	}

	@Override
	public void setIntValue(int intValue) {
		logger.info("setIntValue(" + intValue + ")");
		this.intValue = intValue;
	}

	@PostConstruct
	public void start()  {
		logger.info("Exposing by Interface Bean started");
	}
	
	@PreDestroy
	public void stop()  {
		logger.info("Exposing by Interface Bean stopped");
	}	
}
