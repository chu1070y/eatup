package ga.eatup.user.domain;

import java.util.Date;

import lombok.Data;

@Data
public class MenuVO {

	private int sno, mno;
	private String mname, mcat, fname;
	private int mprice;
	private Date regdate;
	
}
