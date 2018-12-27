 package ga.eatup.user.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class OrderVO {

	private String tid, payment_method_type, status, token;
	private int sno,uno, mno, quantity, partner_order_id;
	private Date approved_at;
	
}
   