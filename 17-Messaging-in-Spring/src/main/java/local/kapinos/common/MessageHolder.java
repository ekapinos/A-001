package local.kapinos.common;

import java.io.Serializable;

public class MessageHolder implements Serializable{
	
	private static final long serialVersionUID = 4892147525311196514L;
	
	private String text;
	
	public MessageHolder(String text){
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
}
