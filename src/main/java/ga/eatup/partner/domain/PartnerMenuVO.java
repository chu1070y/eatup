package ga.eatup.partner.domain;

import java.util.Date;

import lombok.Data;

@Data
public class PartnerMenuVO {

	private int sno;
	private int mno;
	private String mname;
	private int mprice;
	private String mcat;
	private String introduction;
	private int max_quantity;
	private String fname;
	private String uuid;
	private String upload_path;
	private Date regdate;
	private Date updatedate;
	private String status;
	private String soldout;
}
