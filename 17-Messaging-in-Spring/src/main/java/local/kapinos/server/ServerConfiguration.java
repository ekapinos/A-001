package local.kapinos.server;

import org.apache.activemq.broker.BrokerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import local.kapinos.common.WaitAnyKeyBean;

@Configuration
public class ServerConfiguration {
	
	@Bean(destroyMethod="stop")
	BrokerService embeddedBroker() throws Exception{
		BrokerService broker = new BrokerService();
		broker.setPersistent(false);		 
		broker.addConnector("tcp://localhost:61616");
		broker.start();		
		return broker;
	}
	
	@Bean
	Object waitAnyKeyBean(){
		return new WaitAnyKeyBean();
	}
}
