package local.kapinos.config;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

import org.apache.activemq.broker.BrokerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;

import local.kapinos.bean.RawJmsServer;
import local.kapinos.common.WaitAnyKeyBean;

@Configuration
@Import(CommonConfiguration.class)
public class ServerConfiguration {
	
	@Bean(destroyMethod="stop")
	public BrokerService embeddedBroker() throws Exception{
		BrokerService broker = new BrokerService();
		broker.setPersistent(false);		 
		broker.addConnector("tcp://localhost:61616");
		broker.start();		
		return broker;
	}
	
	
	@Bean(destroyMethod="stop")
	@DependsOn("embeddedBroker")
	public RawJmsServer rawJmsClient(ConnectionFactory cf, Queue testQueue) {
		return new RawJmsServer();
	}
	
	@Bean
	Object waitAnyKeyBean(){
		return new WaitAnyKeyBean();
	}

}
