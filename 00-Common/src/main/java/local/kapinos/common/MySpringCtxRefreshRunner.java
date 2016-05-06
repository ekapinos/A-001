package local.kapinos.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

public class MySpringCtxRefreshRunner {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	private final MySpringRunnable runnable;
	
	public MySpringCtxRefreshRunner(MySpringRunnable runnable){
		this.runnable = runnable;
	}
	
    @EventListener({ContextRefreshedEvent.class})
    private void contextRefreshedEvent() throws Exception {
    	runnable.run();
	}
}
