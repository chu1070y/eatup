package ga.eatup.user.domain;

import java.util.Date;

import lombok.Data;

@Data
public class MenuVO {

	private Integer sno, mno;
	private String mname, mcat, fname;
	private Integer mprice;
	private Date regdate;
	private String introduction;
	
	private String uuid;
	private String uploadPath;
	
	private Integer count;
	
	private boolean image = false;
	
}
