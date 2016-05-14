package local.kapinos.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import local.kapinos.model.Shout;

@Controller
public class StompController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	SimpMessagingTemplate template;
	
	@MessageMapping("/marco") // call with /app/marco. See prefix in config
	//@SendTo("/topic/marco") // default, so me can avoid it
	public Shout handleShout(/*@Payload - annotation can be skipped */Shout incoming) {
		logger.info("Received STOMP message: " + incoming.getMessage());
		
		Shout outgoing = new Shout();
		outgoing.setMessage("SimpMessagingTemplate.convertAndSend");
		template.convertAndSend("/queue/marco", outgoing);
		
		outgoing = new Shout();
		outgoing.setMessage("@MessageMapping return value");
		return outgoing;
	}

	@SubscribeMapping("/marco")
	public Shout handleSubscription() {	
		logger.info("Received STOMP subscription");
		
		Shout outgoing = new Shout();
		outgoing.setMessage("@SubscribeMapping return value");
		return outgoing;
	}
	
	@MessageMapping("/this-user-only")
	@SendToUser("/queue/secured") // WARN should have valid destination prefix '/queue' or '/topic'. See prefix in config
	public Shout handleThisUserOnly(Shout incoming) {
		logger.info("Received STOMP message: " + incoming.getMessage());
		
		Shout outgoing = new Shout();
		outgoing.setMessage("@SendToUser return value");
		return outgoing;
	}
	
	@MessageMapping("/generate-remote-exception")
	public void handleGenerateException() {
		logger.info("Received STOMP generate remote exception request");
		
		throw new IllegalArgumentException("Dummy Remote exception");
	}
	
	@MessageExceptionHandler(IllegalArgumentException.class)
	@SendTo("/queue/remote-exceptions")
	public Object handleExceptions(Throwable t) {		
		logger.error("Error handling message: " + t.getMessage());
		return t.getMessage(); // or template.convertAndSend("/queue/remote-exceptions", t.getMessage());
	}
}
