package ga.eatup.partner.domain;

import lombok.Data;

@Data
public class SalesVO {

	private String orderdate;
	private int month;
	private String start;
	private String end;
	private String week;
	private int total;
	
	private String mname;
	private int mprice;
	private int quantity;
	
	
}
