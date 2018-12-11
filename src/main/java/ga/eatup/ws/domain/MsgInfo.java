package ga.eatup.ws.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="sessionid")
public class MsgInfo {

	private String fromDevice;
	private String myDevice;	
	
}
