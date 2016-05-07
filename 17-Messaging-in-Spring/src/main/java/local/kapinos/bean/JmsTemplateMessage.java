package local.kapinos.bean;

import java.io.Serializable;

public class JmsTemplateMessage implements Serializable{
	
	private static final long serialVersionUID = 4892147525311196514L;
	
	private String text;
	
	JmsTemplateMessage(String text){
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
}
