package local.kapinos.common;

import java.io.IOException;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

public class WaitAnyKeyBean {

    @EventListener({ContextRefreshedEvent.class})
    void contextRefreshedEvent() throws IOException {
		System.out.print("Press any key ...");
		System.in.read();
	}
}
