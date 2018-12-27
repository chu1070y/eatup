package ga.eatup.user.domain;

import lombok.Data;

@Data
public class OrderInfoVO {

	private String tid;
	private int mno;
	private int quantity;
	
	private MenuVO menu;
	private OrderVO order;
	
	
}
