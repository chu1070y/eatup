package ga.eatup.partner.domain;

import lombok.Data;

@Data
public class NoticeUploadVO {

	private Integer nno;
	private String fname;
	private String uuid;
	private String upload_path;
	private Integer filetype = 0;
	
}
