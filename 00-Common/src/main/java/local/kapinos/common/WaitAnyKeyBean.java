package local.kapinos.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

public class WaitAnyKeyBean {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
    @EventListener({ContextRefreshedEvent.class})
    private void contextRefreshedEvent() throws Exception {
		logger.info("###############################################");
		logger.info("####     Press any key for shutdown ...    ####");
		logger.info("###############################################");
		System.in.read();		
		logger.info("###############################################");
		logger.info("####              Shutdown ...             ####");
		logger.info("###############################################");
	}

}
