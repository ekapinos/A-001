package local.kapinos.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import local.kapinos.common.JmsMessageHolder;
import local.kapinos.common.RemotingJmsService;

public class RemotingJmsServer implements RemotingJmsService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void printMessage(JmsMessageHolder message) {
		logger.info("GOT A MESSAGE: {}", message.getText());	
	}

}
