package local.kapinos.bean;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import local.kapinos.common.JmsMessageHolder;
import local.kapinos.common.RemotingJmsService;

public class RemotingJmsClient {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private RemotingJmsService remotingJmsService;
	
	@PostConstruct
	public void start() {
		logger.info("Remoting JMS Service Client start");
		
		logger.info("  - Send Hello Remoting JMS Service!");

		remotingJmsService.printMessage(new JmsMessageHolder("Hello Remoting JMS Service!"));

		logger.info("Remoting JMS Service Client stop");
	}
}
