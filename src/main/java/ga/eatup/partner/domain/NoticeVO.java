package ga.eatup.partner.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class NoticeVO {
	
	private Integer nno;
	private String title;
	private String writer;
	private String content;
	
	private Date regdate, updatedate;
	
	private String status;
	
	private List<NoticeUploadVO> uploadList;


}
