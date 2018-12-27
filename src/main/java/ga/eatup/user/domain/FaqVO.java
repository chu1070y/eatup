package ga.eatup.user.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class FaqVO {
	
	private Integer fno;
	private String title;
	private String writer;
	private String content;
	
	private Date regdate, updatedate;
	
	private String status;
	
	//private List<NoticeUploadVO> uploadList;

}
