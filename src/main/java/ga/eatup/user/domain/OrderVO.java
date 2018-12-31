 package ga.eatup.user.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class OrderVO {

	private String tid, payment_method_type, status, mname, sname, token, fname, uuid, upload_path;
	private int sno,uno, mno, quantity, partner_order_id, mprice;
	private Date approved_at;
	
	private List<OrderInfoVO> oinfoList;
	private List<MenuVO> menuList;
	private StoreVO store;
	
}
   