package ga.eatup.ws.domain;

import lombok.Data;

@Data
public class HelloMessage {

	private String from;
    private String text;
    private String time;
    
	public HelloMessage(String from, String text, String time) {
		
		this.from = from;
		this.text = text;
		this.time = time;
	}
	
    
	
	
}