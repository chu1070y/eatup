package ga.eatup.user.domain;

import java.util.Date;

import lombok.Data;

@Data
public class MenuVO {

	private int sno, mno;
	private String mname, mcat, fname, sname;
	private int mprice;
	private Date regdate;
	private Date updatedate;
	private String introduction;
	private int max_quantity;
	
	private String uuid;
	private String upload_path;
	

	private String status;
	private String soldout;
	
	private int count;
	
	private boolean image = false;
	
	private StoreVO storeVO;
	
}
