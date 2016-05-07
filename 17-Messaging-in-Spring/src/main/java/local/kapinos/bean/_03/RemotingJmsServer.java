package local.kapinos.bean._03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import local.kapinos.common.MessageHolder;
import local.kapinos.common.RemotingJmsService;

public class RemotingJmsServer implements RemotingJmsService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void printMessage(MessageHolder message) {
		logger.info("GOT A MESSAGE: {}", message.getText());	
	}

}
