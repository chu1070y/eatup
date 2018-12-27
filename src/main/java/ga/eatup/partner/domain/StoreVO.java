package ga.eatup.partner.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class StoreVO {

	private int sno;
	private int pno;
	private String sname;
	private String saddress;
	private String b_field;
	private String businessHours;
	private String telephone;
	private String qr_uuid;
	private String qr_fname;
	private String qr_type;
	private String lat;
	private String lng;
	private String status;
	private String open;
	private Date regdate;
	private Date updatedate;
	private String serialNum;
	private String qr_upload_path;
	
	private List<StoreImageVO> imageList;
	
}
