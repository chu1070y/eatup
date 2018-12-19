package ga.eatup.partner.domain;

import lombok.Data;

@Data
public class SalesVO {

	private String orderdate;
	private String ordermonth;
	private int month;
	private String start;
	private String end;
	private String week;
	private int total;
	private int sno;
	private int pno;
	private String pid;
	
	private String mname;
	private int mprice;
	private int quantity;
	
	
}
