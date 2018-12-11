package ga.eatup.partner.domain;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class PartnerVO {
	private int pno;
	private String pid;
	private String ppw;
	private String pmail;
	private String owner_name;
	private String owner_num;
	private Date regdate;
	private Date updateDate;
	
	private List<AuthVO> authList;

}