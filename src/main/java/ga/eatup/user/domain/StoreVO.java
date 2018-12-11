package ga.eatup.user.domain;

import java.util.Date;
import lombok.Data;

@Data
public class StoreVO {

	private int sno, mno;
	private String sname, saddress, b_field;
	private String qr_uuid, qu_fname;
	private double lat, lng;
	private Date regdate, updatedate;
}
