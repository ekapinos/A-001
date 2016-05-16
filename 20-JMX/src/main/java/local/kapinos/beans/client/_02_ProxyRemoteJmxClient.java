package local.kapinos.beans.client;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import local.kapinos.beans.server._02_ExposingByInterfaceBean;

@Component
public class _02_ProxyRemoteJmxClient {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private _02_ExposingByInterfaceBean remoteJmxProxy;

	@PostConstruct
	public void start() throws Exception {
		logger.info("Proxy Remote JMX Client start");

		int intValue = remoteJmxProxy.getIntValue();
		logger.info(" - intValue=" + intValue);
		
		intValue++;
		remoteJmxProxy.setIntValue(intValue);
		logger.info(" - setIntValue("+intValue+")");
		
		intValue = remoteJmxProxy.getIntValue();
		logger.info(" - intValue=" + intValue);
		
		logger.info("Proxy Remote JMX Client stop");
	}
}
