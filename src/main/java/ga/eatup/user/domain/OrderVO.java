 package ga.eatup.user.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class OrderVO {

	private String tid, payment_method_type, status, token;
	private int sno,uno,partner_order_id;
	private Date approved_at;
	private List<OrderInfoVO> orderInfo;
	private List<CartDTO> cartList;
	private Integer total;
	
}
   