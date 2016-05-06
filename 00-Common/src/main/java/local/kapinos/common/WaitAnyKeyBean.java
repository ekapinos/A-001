package local.kapinos.common;

import java.io.IOException;

import javax.annotation.PostConstruct;

public class WaitAnyKeyBean {
	@PostConstruct 
	void waitAnyKey() throws IOException{
		System.out.print("Press any key ...");
		System.in.read();
	}
}
