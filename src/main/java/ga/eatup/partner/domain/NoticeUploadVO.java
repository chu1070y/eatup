package ga.eatup.partner.domain;

import lombok.Data;

@Data
public class NoticeUploadVO {

	private Integer nno;
	private String fname;
	private String uuid;
	private String uploadPath;
	private Integer filetype = 0;
	
}
